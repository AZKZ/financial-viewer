package com.azkz.domain.incomestatement.impl

import com.azkz.domain.incomestatement.MonthlyIncomeStatement
import com.azkz.domain.incomestatement.items.monthly.*
import java.time.YearMonth

class MonthlyIncomeStatementImpl(
    override val yearMonth: YearMonth,
    override val sales: MonthlySales,
    override val costOfSales: MonthlyCostOfSales,
    override val sellingGeneralAdministrativeExpenses: MonthlySellingGeneralAdministrativeExpenses,
    override val nonOperatingIncome: MonthlyNonOperatingIncome,
    override val nonOperatingExpenses: MonthlyNonOperatingExpenses,
    override val extraordinaryIncome: MonthlyExtraordinaryIncome,
    override val extraordinaryLoss: MonthlyExtraordinaryLoss
) : MonthlyIncomeStatement {

}