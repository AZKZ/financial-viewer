package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

class AnnualIncomeStatementCSVReaderTest {

    companion object {
        const val CSV_PATH = "src/test/resources/csv/損益計算書（年間推移）/正常.csv"
        const val NOT_EXISTS_PATH = "notExists/正常.csv"
        const val ILLEGAL_EXTENSION_PATH = "src/test/resources/csv/損益計算書（年間推移）/拡張子違い.txt"
        const val HEADER_NOTHING_CSV_PATH = "src/test/resources/csv/損益計算書（年間推移）/ヘッダー行なし.csv"
        const val MULTIPLE_YEARS_CSV_PATH = "src/test/resources/csv/損益計算書（年間推移）/複数年度.csv"
        const val TERM_NOTHING_CSV_PATH = "src/test/resources/csv/損益計算書（年間推移）/集計期間なし.csv"
        const val ILLEGAL_TERM_DATE_PATH = "src/test/resources/csv/損益計算書（年間推移）/集計期間の日付不正.csv"
        const val FUTURE_MONTHS_CSV_PATH = "src/test/resources/csv/損益計算書（年間推移）/未来データ.csv"
    }

    @Test
    fun `Test to create`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(CSV_PATH)
        assertEquals(incomeStatementCSVReader.filePath, CSV_PATH)
    }

    @Test
    fun `Test to create by not exists path`() {
        val exception = assertThrows<FileNotFoundException> { AnnualIncomeStatementCSVReaderImpl(NOT_EXISTS_PATH) }
    }

    @Test
    fun `Test to create by illegal extension path`() {
        val exception =
            assertThrows<IllegalArgumentException> { AnnualIncomeStatementCSVReaderImpl(ILLEGAL_EXTENSION_PATH) }
    }

    @Test
    fun `Test to parseToBreakdowns`() {
        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(CSV_PATH)
        val breakdowns: Set<MonthlyAccountBreakdown> = incomeStatementCSVReader.parseToBreakdowns()
        assert(breakdowns.isNotEmpty())
    }

//    @Test
//    fun `Test to parseToBreakdowns by header nothing csv`() {
//        val incomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(HEADER_NOTHING_CSV_PATH)
//        val exception = assertThrows<IllegalArgumentException> { incomeStatementCSVReader.parseToBreakdowns() }
//        assertEquals(exception.message,"")
//    }


}