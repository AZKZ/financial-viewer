package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlySales
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlySalesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlySales(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }

}
