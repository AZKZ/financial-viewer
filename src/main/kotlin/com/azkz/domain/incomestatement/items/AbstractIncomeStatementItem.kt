package com.azkz.domain.incomestatement.items

import com.azkz.domain.accountbreakdown.AccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory

abstract class AbstractIncomeStatementItem(
    override val accountSubcategory: AccountSubcategory,
    override val breakdowns: List<AccountBreakdown>
) :
    IncomeStatementItem {

    init {
        // 損益計算書項目と内訳の不整合を許さないためのチェック
        val illegalBreakdowns = breakdowns.filterNot { it.isSameAccountSubCategory(accountSubcategory) }
        if (illegalBreakdowns.isNotEmpty()) throw IllegalArgumentException()
    }
}