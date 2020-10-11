package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlyExtraordinaryLoss
import com.azkz.domain.value.Amount
import java.time.YearMonth

class AbstractMonthlyExtraordinaryLossImpl(
    yearMonth: YearMonth,
    breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlyExtraordinaryLoss(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}