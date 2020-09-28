package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import java.time.YearMonth

abstract class MonthlySales(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyIncomeStatementItem(yearMonth, AccountSubcategory.SALES, breakdowns) {
}