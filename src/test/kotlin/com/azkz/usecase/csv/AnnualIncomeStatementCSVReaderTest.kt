package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class AnnualIncomeStatementCSVReaderTest {

    companion object {
        val CSV_FILE = File("src/test/resources/csv/損益計算書（年間推移）/正常.csv").inputStream()
        val HEADER_NOTHING_FILE = File("src/test/resources/csv/損益計算書（年間推移）/ヘッダー行なし.csv").inputStream()
        val MULTIPLE_YEARS_FILE = File("src/test/resources/csv/損益計算書（年間推移）/複数年度.csv").inputStream()
        val ILLEGAL_TERM_DATE_FILE = File("src/test/resources/csv/損益計算書（年間推移）/集計期間の日付不正.csv").inputStream()
        val TERM_NOTHING_FILE = File("src/test/resources/csv/損益計算書（年間推移）/集計期間なし.csv").inputStream()
        val FUTURE_MONTHS_FILE = File("src/test/resources/csv/損益計算書（年間推移）/未来データ.csv").inputStream()
        val TARGET_RECORD_NOTHING_FILE = File("src/test/resources/csv/損益計算書（年間推移）/対象レコードなし.csv").inputStream()
    }

    @Test
    fun `Test to create`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(CSV_FILE)
        assertNotNull(incomeStatementCSVReader)
    }

    @Test
    fun `Test to parseToBreakdowns`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(CSV_FILE)
        val breakdowns: Set<MonthlyAccountBreakdown> = incomeStatementCSVReader.parseToBreakdowns()
        assert(breakdowns.isNotEmpty())
    }

    @Test
    fun `Test to parseToBreakdowns by header nothing file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(HEADER_NOTHING_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("ヘッダー行がありません。", exception.message)
    }

    @Test
    fun `Test to parseToBreakdowns by multiple years file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(MULTIPLE_YEARS_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("複数年度は処理はできません。", exception.message)
    }

    @Test
    fun `Test to parseToBreakdowns by illegal term date file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(ILLEGAL_TERM_DATE_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("集計期間から日付を変換できません。", exception.message)
    }

    @Test
    fun `Test to parseToBreakdowns by term nothing file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(TERM_NOTHING_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("集計期間の行がありません。", exception.message)
    }

    @Test
    fun `Test to parseToBreakdowns by future months file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(FUTURE_MONTHS_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("登録対象データがありません。", exception.message)
    }

    @Test
    fun `Test to parseToBreakdowns by target record nothing file`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(TARGET_RECORD_NOTHING_FILE)
        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
        assertEquals("取り込み対象となるレコードがありません。", exception.message)
    }

}