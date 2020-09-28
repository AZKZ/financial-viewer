package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlyNonOperatingIncome
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyNonOperatingIncomeImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyNonOperatingIncome(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
