package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlyExtraordinaryLoss
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyExtraordinaryLossImpl(
    yearMonth: YearMonth,
    breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyExtraordinaryLoss(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}