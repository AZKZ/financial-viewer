package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlyCostOfSales
import com.azkz.domain.value.Amount
import java.time.YearMonth

class AbstractMonthlyCostOfSalesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlyCostOfSales(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }
}
