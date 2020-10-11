package com.azkz.domain.incomestatement.items

import com.azkz.domain.accountbreakdown.AccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory

abstract class AbstractIncomeStatementItem(
    override val accountSubcategory: AccountSubcategory,
    breakdowns: List<AccountBreakdown>
) :
    IncomeStatementItem {

    // 勘定科目サブカテゴリが一致している内訳のみ格納
    override val breakdowns: List<AccountBreakdown> =
        breakdowns.filter { it.accountTitle.subcategory == accountSubcategory }
}