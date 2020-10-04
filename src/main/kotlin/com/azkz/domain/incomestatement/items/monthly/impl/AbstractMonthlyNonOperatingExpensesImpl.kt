package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlyNonOperatingExpenses
import com.azkz.domain.value.Amount
import java.time.YearMonth

class AbstractMonthlyNonOperatingExpensesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlyNonOperatingExpenses(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
