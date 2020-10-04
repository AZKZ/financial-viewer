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

    /** 売上 */
    override val sales: AbstractMonthlySales =
        AbstractMonthlySalesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.SALES })

    /** 売上原価 */
    override val costOfSales: AbstractMonthlyCostOfSales =
        AbstractMonthlyCostOfSalesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.COST_OF_SALES })

    /** 販売管理費 */
    override val sellingGeneralAdministrativeExpenses: AbstractMonthlySellingGeneralAdministrativeExpenses =
        AbstractMonthlySellingGeneralAdministrativeExpensesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE })

    /** 営業外収益 */
    override val nonOperatingIncome: AbstractMonthlyNonOperatingIncome =
        AbstractMonthlyNonOperatingIncomeImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.NON_OPERATING_INCOME })

    /** 営業外費用 */
    override val nonOperatingExpenses: AbstractMonthlyNonOperatingExpenses =
        AbstractMonthlyNonOperatingExpensesImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.NON_OPERATING_EXPENSE })

    /** 特別収益 */
    override val extraordinaryIncome: AbstractMonthlyExtraordinaryIncome =
        AbstractMonthlyExtraordinaryIncomeImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.EXTRAORDINARY_INCOME })

    /** 特別損失 */
    override val extraordinaryLoss: AbstractMonthlyExtraordinaryLoss =
        AbstractMonthlyExtraordinaryLossImpl(
            yearMonth = yearMonth,
            breakdowns = monthlyAccountBreakdowns.filter { it.accountTitle.subcategory == AccountSubcategory.EXTRAORDINARY_LOSS })
}