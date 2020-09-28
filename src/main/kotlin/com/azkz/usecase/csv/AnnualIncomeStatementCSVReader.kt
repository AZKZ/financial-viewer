package com.azkz.usecase.csv

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown

interface AnnualIncomeStatementCSVReader {

    
    fun parseToBreakdowns(): Set<MonthlyAccountBreakdown>

}