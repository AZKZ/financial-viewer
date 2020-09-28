package com.azkz.domain.incomestatement

import com.azkz.domain.incomestatement.items.monthly.*
import java.time.YearMonth

interface MonthlyIncomeStatement {
    val yearMonth: YearMonth

    val sales: MonthlySales
    val costOfSales: MonthlyCostOfSales
    val sellingGeneralAdministrativeExpenses: MonthlySellingGeneralAdministrativeExpenses
    val nonOperatingIncome: MonthlyNonOperatingIncome
    val nonOperatingExpenses: MonthlyNonOperatingExpenses
    val extraordinaryIncome: MonthlyExtraordinaryIncome
    val extraordinaryLoss: MonthlyExtraordinaryLoss
}