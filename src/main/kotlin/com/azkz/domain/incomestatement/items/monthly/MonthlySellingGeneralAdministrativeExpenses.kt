package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import java.time.YearMonth

abstract class MonthlySellingGeneralAdministrativeExpenses(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyIncomeStatementItem(yearMonth, AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, breakdowns) {
}