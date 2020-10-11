package com.azkz.domain.accountbreakdown.impl

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount
import java.time.YearMonth

class MonthlyAccountBreakdownImpl(override val yearMonth: YearMonth, override val accountTitle: AccountTitle,
                                  override val amount: Amount
) :
    MonthlyAccountBreakdown {

    init {
        val currentYearMonth: YearMonth = YearMonth.now()
        if (yearMonth.isAfter(currentYearMonth) || yearMonth == currentYearMonth) throw IllegalArgumentException("当月以降のデータは作成できません")
    }

}
