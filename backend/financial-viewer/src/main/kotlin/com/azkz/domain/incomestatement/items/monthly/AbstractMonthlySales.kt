package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import java.time.YearMonth

abstract class AbstractMonthlySales(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlyIncomeStatementItem(yearMonth, AccountSubcategory.SALES, breakdowns) {
}