package com.azkz.domain.incomestatement

import com.azkz.domain.incomestatement.items.monthly.*
import java.time.YearMonth

interface MonthlyIncomeStatement {
    val yearMonth: YearMonth

    val sales: AbstractMonthlySales
    val costOfSales: AbstractMonthlyCostOfSales
    val sellingGeneralAdministrativeExpenses: AbstractMonthlySellingGeneralAdministrativeExpenses
    val nonOperatingIncome: AbstractMonthlyNonOperatingIncome
    val nonOperatingExpenses: AbstractMonthlyNonOperatingExpenses
    val extraordinaryIncome: AbstractMonthlyExtraordinaryIncome
    val extraordinaryLoss: AbstractMonthlyExtraordinaryLoss
}