package com.azkz.domain.accountitle

/**
 * 勘定科目サブカテゴリークラス
 */
enum class AccountSubcategory(val category: AccountCategory) {
    SALES(AccountCategory.REVENUE),
    NON_OPERATING_INCOME(AccountCategory.REVENUE),
    EXTRAORDINARY_INCOME(AccountCategory.REVENUE),
    COST_OF_SALES(AccountCategory.EXPENSE),
    SELLING_GENERAL_ADMINISTRATIVE_EXPENSE(AccountCategory.EXPENSE),
    NON_OPERATING_EXPENSE(AccountCategory.EXPENSE),
    EXTRAORDINARY_LOSS(AccountCategory.EXPENSE),
}