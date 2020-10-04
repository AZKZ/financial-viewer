package com.azkz.domain.incomestatement.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.incomestatement.MonthlyIncomeStatement
import com.azkz.domain.incomestatement.items.monthly.*
import com.azkz.domain.incomestatement.items.monthly.impl.*
import java.time.YearMonth

class MonthlyIncomeStatementImpl(
    override val yearMonth: YearMonth,
    monthlyAccountBreakdowns: Set<MonthlyAccountBreakdown>
) : MonthlyIncomeStatement {

    override val sales: AbstractMonthlySales =
        AbstractMonthlySalesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.SALES })

    override val costOfSales: AbstractMonthlyCostOfSales =
        AbstractMonthlyCostOfSalesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.COST_OF_SALES })

    override val sellingGeneralAdministrativeExpenses: AbstractMonthlySellingGeneralAdministrativeExpenses =
        AbstractMonthlySellingGeneralAdministrativeExpensesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE })

    override val nonOperatingIncome: AbstractMonthlyNonOperatingIncome =
        AbstractMonthlyNonOperatingIncomeImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.NON_OPERATING_INCOME })

    override val nonOperatingExpenses: AbstractMonthlyNonOperatingExpenses =
        AbstractMonthlyNonOperatingExpensesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.NON_OPERATING_EXPENSE })

    override val extraordinaryIncome: AbstractMonthlyExtraordinaryIncome =
        AbstractMonthlyExtraordinaryIncomeImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.EXTRAORDINARY_INCOME })


    override val extraordinaryLoss: AbstractMonthlyExtraordinaryLoss =
        AbstractMonthlyExtraordinaryLossImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.EXTRAORDINARY_LOSS })
}