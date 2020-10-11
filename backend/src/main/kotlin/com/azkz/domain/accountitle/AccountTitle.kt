package com.azkz.domain.accountitle

/**
 * 勘定科目クラス
 */
class AccountTitle(val subcategory: AccountSubcategory, val japanese: String) {
    init {
        if (japanese.isBlank()) throw IllegalArgumentException("勘定科目の日本語名は必須です。")
    }

    fun getCategory(): AccountCategory {
        return this.subcategory.category
    }

}