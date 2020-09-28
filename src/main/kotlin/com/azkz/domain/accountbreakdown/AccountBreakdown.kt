package com.azkz.domain.accountbreakdown

import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount

interface AccountBreakdown {
    val accountTitle: AccountTitle
    val amount: Amount

    fun isSameAccountSubCategory(accountSubcategory: AccountSubcategory): Boolean {
        return accountTitle.subcategory == accountSubcategory
    }
}