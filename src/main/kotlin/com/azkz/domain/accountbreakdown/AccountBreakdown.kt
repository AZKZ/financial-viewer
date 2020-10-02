package com.azkz.domain.accountbreakdown

import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount

/**
 * 内訳インターフェース
 */
interface AccountBreakdown {
    val accountTitle: AccountTitle
    val amount: Amount
}