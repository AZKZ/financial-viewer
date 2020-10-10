package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import com.opencsv.CSVReader
import java.io.InputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Month
import java.time.YearMonth
import java.util.*

/**
 * 損益計算書（年間推移）読み取り実装クラス
 */
class AnnualIncomeStatementCSVReaderImpl(inputStream: InputStream) : AnnualIncomeStatementCSVReader {

    private val reader: CSVReader = CSVReader(inputStream.bufferedReader())
    private val allRecords: List<Array<String>> = reader.readAll()

    override fun parseToBreakdowns(): Set<MonthlyAccountBreakdown> {
        /** CSV集計期間の開始年月 */
        val startYearMonth: YearMonth = readStartYearMonth(allRecords)
        val targetColumnMap: Map<Int, Month> = createTargetColumnMap(allRecords)
        val targetRecordMap: Map<Int, AccountSubcategory> = createTargetRecordMap(allRecords)
        val returnSet: MutableSet<MonthlyAccountBreakdown> = mutableSetOf()

        // 対象カラムを1列(つまり、月)ずつ処理する
        for (targetColumnMapEntry in targetColumnMap) {
            val columnIndex: Int = targetColumnMapEntry.key
            val targetMonth: Month = targetColumnMapEntry.value

            lateinit var yearMonth: YearMonth

            // CSVは年度のため、一部の月は+1年の考慮が必要
            if (targetMonth < startYearMonth.month)
                yearMonth = YearMonth.of(startYearMonth.year + 1, targetMonth)
            else
                yearMonth = YearMonth.of(startYearMonth.year, targetMonth)


            // 過去月のみ処理対象とする(それ以外は処理しない)
            if (!yearMonth.isBefore(YearMonth.now()))
                continue

            // 対象レコードを1行ずつ処理する
            for (targetRecordMapEntry in targetRecordMap) {
                val recordIndex: Int = targetRecordMapEntry.key
                val accountSubcategory = targetRecordMapEntry.value

                val record = allRecords[recordIndex]
                val recordTitle: String = record[0]

                val accountTitle = AccountTitle(subcategory = accountSubcategory, japanese = recordTitle)

                // 金額の[,]を除く
                val amountStr: String = record[columnIndex]
                val amountLong: Long = amountStr.replace(",", "").toLong()

                returnSet.add(MonthlyAccountBreakdownImpl(yearMonth, accountTitle, Amount(amountLong, Currency.JPY)))
            }

        }

        //TODO 適したExceptionクラスを定義する
        if (returnSet.isEmpty()) throw IllegalArgumentException("登録対象データがありません。")

        return returnSet
    }

    /**
     * 集計期間の開始年月を読み取る
     * @return (例) 「集計期間：令和02年06月01日,令和03年05月31日,...」 -> 2020-06
     */
    private fun readStartYearMonth(allRecords: List<Array<String>>): YearMonth {

        // [集計期間：令和02年06月01日,令和03年05月31日,...]の行から開始年月を読み取る
        val termRecord: Array<String> = allRecords.find { it[0].startsWith("集計期間：") }
        //TODO 適したExceptionクラスを定義する
            ?: throw IllegalArgumentException("集計期間の行がありません。")

        // 和暦を文字列で切り出す
        val termString: String = termRecord[0]
        val japaneseDateString = termString.substring(5, 16)

        // 和暦→Date型
        // 失敗したら例外
        val dateFormat = SimpleDateFormat("GGGGyy年MM月dd日", Locale("ja", "JP", "JP"))
        val date = try {
            dateFormat.parse(japaneseDateString)
        } catch (e: ParseException) {
            //TODO 適したExceptionクラスを定義する
            throw IllegalArgumentException("集計期間から日付を変換できません。")
        }

        // Date型から年・月を取得
        val calendar = Calendar.getInstance()
        calendar.time = date
        val yearInt = calendar.get(Calendar.YEAR)
        // Monthは0始まりのため ※Monthクラスの仕様
        val monthInt = calendar.get(Calendar.MONTH) + 1

        return YearMonth.of(yearInt, monthInt)
    }

    /**
     * 取り込み対象とするカラムの列番号(index)と月のマップを作成する。
     *
     * @return (例){1:6月,2:7月...}
     */
    private fun createTargetColumnMap(allRecords: List<Array<String>>): Map<Int, Month> {

        val targetColumnMap: MutableMap<Int, Month> = mutableMapOf()

        // [勘定科目, 6月度, 7月度, 8月度, 9月度,...]の行から各月の列番号を取得する
        val monthRecord: Array<String> = allRecords.find { it[0] == "勘定科目" }
        //TODO 適したExceptionクラスを定義する
            ?: throw IllegalArgumentException("ヘッダー行がありません。")


        // 列のインデックスを使うため、あえてforループにしている
        for (i in monthRecord.indices) {
            // 一桁の月には空白があるため、trimしている
            val str: String = monthRecord[i].trim()

            // 1文字目が数字の場合
            if (str.isNotBlank() && str[0].isDigit()) {
                val monthInt = str.removeSuffix("月度").toInt()
                targetColumnMap.put(i, Month.of(monthInt))
            }
        }

        // 1年度分以上ある場合
        if (targetColumnMap.size > 12) throw IllegalArgumentException("複数年度は処理はできません。")

        return targetColumnMap
    }

    /**
     * 取り込み対象とするレコードの行番号(index)と勘定科目のマップを作成する。
     *
     * @return (例){18:売上高,34:販売管理費,35:販売管理費...}
     */
    private fun createTargetRecordMap(allRecords: List<Array<String>>): Map<Int, AccountSubcategory> {
        val targetRecordMap = mutableMapOf<Int, AccountSubcategory>()
        val accountSubcategoryList = AccountSubcategory.values().toList()
        var accountSubcategory: AccountSubcategory? = null

        // 上から順にレコードを処理
        for (i in allRecords.indices) {

            val recordTitle: String = allRecords[i][0]

            // 空行の場合、次の行へ
            if (recordTitle.isBlank())
                continue


            // レコードの1列目と一致する勘定科目サブカテゴリーを探す
            val tempAccountSubcategory: AccountSubcategory? =
                accountSubcategoryList.find { "[${it.japanese}]" == recordTitle }

            // 取得できた場合、サブカテゴリーに設定(取得できない場合は、変更しないまま)
            if (tempAccountSubcategory != null)
                accountSubcategory = tempAccountSubcategory


            // サブカテゴリが未設定 or 除外項目名に含まれている場合は次の行へ
            if (accountSubcategory == null || excludeTitles.contains(recordTitle))
                continue


            targetRecordMap.put(key = i, value = accountSubcategory)
        }

        //TODO 適したExceptionクラスを定義する
        if (targetRecordMap.isEmpty())
            throw IllegalArgumentException("取り込み対象となるレコードがありません。")

        return targetRecordMap
    }

    /** 除外項目名 */
    private val excludeTitles = setOf<String>(
        "[売上高]",
        "売上高合計",
        "[売上原価]",
        "合計",
        "売上原価",
        "売上総損益金額",
        "[販売管理費]",
        "販売管理費計",
        "営業損益金額",
        "[営業外収益]",
        "営業外収益合計",
        "[営業外費用]",
        "営業外費用合計",
        "経常損益金額",
        "[特別利益]",
        "特別利益合計",
        "[特別損失]",
        "特別損失合計",
        "[当期純損益]",
        "税引前当期純損益金額",
        "当期純損益金額",
    )

}