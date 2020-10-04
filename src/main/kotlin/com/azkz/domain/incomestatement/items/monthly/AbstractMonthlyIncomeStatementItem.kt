package com.azkz.domain.incomestatement.items.monthly

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.incomestatement.items.AbstractIncomeStatementItem
import java.time.YearMonth

abstract class AbstractMonthlyIncomeStatementItem(
    val yearMonth: YearMonth,
    accountSubcategory: AccountSubcategory,
    breakdowns: List<MonthlyAccountBreakdown>
) :
    AbstractIncomeStatementItem(
        accountSubcategory = accountSubcategory,
        // 年月が一致している内訳のみ取り込む
        breakdowns = breakdowns.filter { it.yearMonth == yearMonth }
    ) {
}