package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlyNonOperatingIncome
import com.azkz.domain.value.Amount
import java.time.YearMonth

class AbstractMonthlyNonOperatingIncomeImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlyNonOperatingIncome(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
