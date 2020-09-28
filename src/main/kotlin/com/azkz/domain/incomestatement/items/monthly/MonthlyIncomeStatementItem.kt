package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.incomestatement.items.AbstractIncomeStatementItem
import java.time.YearMonth

abstract class MonthlyIncomeStatementItem(
    val yearMonth: YearMonth,
    accountSubcategory: AccountSubcategory,
    breakdowns: List<MonthlyAccountBreakdown>
) :
    AbstractIncomeStatementItem(
        accountSubcategory, breakdowns
    ) {

    init {
        val illegalYearMonthBreakdowns = breakdowns.filterNot { it.yearMonth.equals(yearMonth) }
        if (illegalYearMonthBreakdowns.isNotEmpty()) throw IllegalArgumentException()
    }
}