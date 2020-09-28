package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.MonthlySellingGeneralAdministrativeExpenses
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlySellingGeneralAdministrativeExpensesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : MonthlySellingGeneralAdministrativeExpenses(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }

}
