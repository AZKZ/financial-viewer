package com.azkz.domain.accountitle

/**
 * 勘定科目サブカテゴリークラス
 */
enum class AccountSubcategory(val category: AccountCategory, val japanese: String) {
    SALES(AccountCategory.REVENUE, "売上高"),
    NON_OPERATING_INCOME(AccountCategory.REVENUE, "営業外収益"),
    EXTRAORDINARY_INCOME(AccountCategory.REVENUE, "特別利益"),
    COST_OF_SALES(AccountCategory.EXPENSE, "売上原価"),
    SELLING_GENERAL_ADMINISTRATIVE_EXPENSE(AccountCategory.EXPENSE, "販売管理費"),
    NON_OPERATING_EXPENSE(AccountCategory.EXPENSE, "営業外費用"),
    EXTRAORDINARY_LOSS(AccountCategory.EXPENSE, "特別損失"),
}