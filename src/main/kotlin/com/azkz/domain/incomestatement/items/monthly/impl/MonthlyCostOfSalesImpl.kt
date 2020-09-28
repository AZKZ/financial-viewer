package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlyCostOfSales
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyCostOfSalesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlyCostOfSales(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
