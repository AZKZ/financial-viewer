package com.azkz.domain.accountitle

/**
 * 勘定科目クラス
 */
enum class AccountTitle(val subcategory: AccountSubcategory, val japanese: String) {

    // SALES
    SALES(AccountSubcategory.SALES, "売上高"),

    // COST_OF_SALES
    BEGINNING_GOODS(AccountSubcategory.COST_OF_SALES, "期首商品棚卸高"),
    COST_OF_PURCHASED_GOODS(AccountSubcategory.COST_OF_SALES, "当期商品仕入高"),
    ENDING_GOODS(AccountSubcategory.COST_OF_SALES, "期末商品棚卸高"),

    // SELLING_GENERAL_ADMINISTRATIVE_EXPENSE
    SALARIES_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "給料手当"),
    ENTERTAINMENT_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "交際費"),
    MEETING_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "会議費"),
    COMMUTATION_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "旅費交通費"),
    COMMUNICATION_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "通信費"),
    OFFICE_SUPPLIES_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "消耗品費"),
    BOOKS_SUBSCRIPTION_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "新聞図書費"),
    MEMBERSHIP_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "諸会費"),
    COMMISSIONS_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "支払手数料"),
    LEASE_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "賃借料"),
    SUNDRY_TAXES_EXPENSE(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "租税公課"),
    COMPENSATIONS(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "支払報酬料"),

    // NON_OPERATING_INCOME
    GAIN_OF_SALES_GOODS(AccountSubcategory.NON_OPERATING_INCOME, "物品売却益"),
    INTEREST_INCOME(AccountSubcategory.NON_OPERATING_INCOME, "受取利息"),

    // NON_OPERATING_EXPENSE
    INTEREST_EXPENSE(AccountSubcategory.NON_OPERATING_EXPENSE, "支払利息"),
    MISCELLANEOUS_LOSS(AccountSubcategory.NON_OPERATING_EXPENSE, "雑支出"),

    // EXTRAORDINARY_INCOME
    GAIN_ON_SALES_OF_INVESTMENT_SECURITIES(AccountSubcategory.EXTRAORDINARY_INCOME, "投資有価証券売却益"),
    GAIN_ON_TRANSFER_OF_BUSINESS(AccountSubcategory.EXTRAORDINARY_INCOME, "事業譲渡益"),

    // EXTRAORDINARY_LOSS
    LOSS_ON_SALES_OF_INVESTMENT_SECURITIES(AccountSubcategory.EXTRAORDINARY_LOSS, "投資有価証券売却損"),
    LOSS_ON_DISASTER(AccountSubcategory.EXTRAORDINARY_LOSS, "災害による損失"),
    ;

    fun getCategory(): AccountCategory {
        return this.subcategory.category
    }

}