package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import com.opencsv.CSVReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Month
import java.time.YearMonth
import java.util.*


class AnnualIncomeStatementCSVReaderImpl(val filePath: String) : AnnualIncomeStatementCSVReader {

    private val reader: CSVReader = CSVReader(FileReader(filePath))
    private val allRecords = reader.readAll()

    init {
        val file = File(filePath)
        if (file.exists().not()) throw FileNotFoundException("ファイルが存在しません")
        if (file.extension != "csv") throw IllegalArgumentException("ファイルの拡張子が正しくありません")
    }

    override fun parseToBreakdowns(): Set<MonthlyAccountBreakdown> {
        val startYearMonth: YearMonth = readStartYearMonth(allRecords)
        val monthColumnIndexMap: Map<Month, Int> = createMonthColumnIndexMap(allRecords)
        val returnSet: MutableSet<MonthlyAccountBreakdown> = mutableSetOf()

        // 各月の列ごとに処理する
        for (it in monthColumnIndexMap) {
            val month: Month = it.key
            val columnIndex: Int = it.value

            var yearMonth: YearMonth

            // CSVは年度のため、一部の月は+1年の考慮が必要
            if (startYearMonth.month > month) yearMonth = YearMonth.of(startYearMonth.year + 1, month)
            else yearMonth = YearMonth.of(startYearMonth.year, month)

            // 過去月のみ処理対象とする(それ以外は処理しない)
            if (!yearMonth.isBefore(YearMonth.now())) continue

            // 行ごとの処理
            for (record in allRecords) {

                val accountTitleText: String = record[0]

                // 登録されている勘定科目のみ、処理の対象とする
                val accountTitle: AccountTitle = AccountTitle.values().find { it.japanese == accountTitleText }
                    ?: continue

                // 金額の[,]を除く
                val amountStr: String = record[columnIndex]
                val amountLong: Long = amountStr.replace(",", "").toLong()

                returnSet.add(MonthlyAccountBreakdownImpl(yearMonth, accountTitle, Amount(amountLong, Currency.JPY)))
            }

        }

        //TODO 適したExceptionクラスを定義する
        if (returnSet.isEmpty()) throw IllegalArgumentException("登録対象データがありません")

        return returnSet
    }

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

    private fun createMonthColumnIndexMap(allRecords: List<Array<String>>): Map<Month, Int> {

        val monthColumnIndexMap: MutableMap<Month, Int> = mutableMapOf()

        // [勘定科目, 6月度, 7月度, 8月度, 9月度,...]の行から各月の列番号を取得する
        val monthRecord: Array<String> = allRecords.find { it[0] == "勘定科目" }
        //TODO 適したExceptionクラスを定義する
            ?: throw IllegalArgumentException("ヘッダー行がありません。")


        // 列のインデックスを使うため、あえてforループにしている
        for (i in monthRecord.indices) {
            // 一桁の月には空白があるため、trimしている
            var str: String = monthRecord[i].trim()

            // 1文字目が数字の場合
            if (str.isNotBlank() && str[0].isDigit()) {
                var monthInt = str.removeSuffix("月度").toInt()
                monthColumnIndexMap.put(Month.of(monthInt), i)
            }
        }

        // 1年度分以上ある場合
        if (monthColumnIndexMap.size > 12) throw IllegalArgumentException("複数年度は処理はできません")

        return monthColumnIndexMap
    }


}