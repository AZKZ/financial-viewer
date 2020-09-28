package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import java.time.YearMonth

abstract class MonthlyNonOperatingExpenses(yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>) :
    MonthlyIncomeStatementItem(
        yearMonth,
        AccountSubcategory.NON_OPERATING_EXPENSE,
        breakdowns
    ) {

}
