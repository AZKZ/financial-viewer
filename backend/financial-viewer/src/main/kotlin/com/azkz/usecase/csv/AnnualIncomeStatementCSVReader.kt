package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown

/**
 * 損益計算書（年間推移）読み取りインターフェース
 */
interface AnnualIncomeStatementCSVReader {

    /**
     * CSVから月別内訳に変換する
     */
    fun parseToBreakdowns(): Set<MonthlyAccountBreakdown>

}