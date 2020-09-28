package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlyNonOperatingExpenses
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyNonOperatingExpensesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyNonOperatingExpenses(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
