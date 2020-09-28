package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlyExtraordinaryIncome
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyExtraordinaryIncomeImpl(
    yearMonth: YearMonth,
    breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyExtraordinaryIncome(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}