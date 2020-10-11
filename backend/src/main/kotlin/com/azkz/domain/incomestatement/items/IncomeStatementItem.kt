package com.azkz.domain.incomestatement.items

import com.azkz.domain.accountbreakdown.AccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.value.Amount

interface IncomeStatementItem {
    val accountSubcategory: AccountSubcategory
    val breakdowns: List<AccountBreakdown>

    fun totalAmount(): Amount
}