package com.azkz.domain.incomestatement.items.monthly.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlySellingGeneralAdministrativeExpenses
import com.azkz.domain.value.Amount
import java.time.YearMonth

class AbstractMonthlySellingGeneralAdministrativeExpensesImpl(
    yearMonth: YearMonth, breakdowns: List<MonthlyAccountBreakdown>
) : AbstractMonthlySellingGeneralAdministrativeExpenses(yearMonth, breakdowns) {
    override fun totalAmount(): Amount {
        TODO("Not yet implemented")
    }

}
