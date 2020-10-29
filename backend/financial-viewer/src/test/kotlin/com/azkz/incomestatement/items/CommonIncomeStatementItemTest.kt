package com.azkz.incomestatement.items

import com.azkz.domain.accountbreakdown.AccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.items.AbstractIncomeStatementItem
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * 損益計算書項目クラスの共通部分をテストする
 */
class CommonIncomeStatementItemTest {

    companion object {
        val AMOUNT = Amount(100, Currency.JPY)

        val ACCOUNT_SUBCATEGORY = AccountSubcategory.SALES
        val ILLEGAL_ACCOUNT_SUBCATEGORY = AccountSubcategory.EXTRAORDINARY_LOSS

        val ACCOUNT_TITLE = AccountTitle(ACCOUNT_SUBCATEGORY, "正常なサブカテゴリ")
        val ILLEGAL_SUBCATEGORY_ACCOUNT_TITLE = AccountTitle(ILLEGAL_ACCOUNT_SUBCATEGORY, "不正なサブカテゴリ")

        /** 正常な内訳 */
        val BREAKDOWNS = listOf<AccountBreakdown>(
            MockAccountBreakdown(ACCOUNT_TITLE, AMOUNT),
            MockAccountBreakdown(ACCOUNT_TITLE, AMOUNT)
        )

        /** 部分的に意図しないサブカテゴリを含む内訳 */
        val PARTIAL_ILLEGAL_SUBCATEGORY_BREAKDOWNS = listOf<AccountBreakdown>(
            MockAccountBreakdown(ACCOUNT_TITLE, AMOUNT),
            MockAccountBreakdown(ILLEGAL_SUBCATEGORY_ACCOUNT_TITLE, AMOUNT),
            MockAccountBreakdown(ACCOUNT_TITLE, AMOUNT),
        )

        /** 全て意図しないサブカテゴリの内訳 */
        val ALL_ILLEGAL_SUBCATEGORY_BREAKDOWNS = listOf<AccountBreakdown>(
            MockAccountBreakdown(ILLEGAL_SUBCATEGORY_ACCOUNT_TITLE, AMOUNT),
            MockAccountBreakdown(ILLEGAL_SUBCATEGORY_ACCOUNT_TITLE, AMOUNT)
        )
    }

    @Test
    fun `Test to create`() {
        val incomeStatementItem =
            TestIncomeStatementItemImpl(ACCOUNT_SUBCATEGORY, BREAKDOWNS)

        assertEquals(ACCOUNT_SUBCATEGORY, incomeStatementItem.accountSubcategory)
        assertEquals(BREAKDOWNS, incomeStatementItem.breakdowns)
    }

    @Test
    fun `Test to create by partial illegal subcategory breakdowns`() {
        val incomeStatementItem =
            TestIncomeStatementItemImpl(ACCOUNT_SUBCATEGORY, PARTIAL_ILLEGAL_SUBCATEGORY_BREAKDOWNS)

        assertEquals(ACCOUNT_SUBCATEGORY, incomeStatementItem.accountSubcategory)
        // サブカテゴリでフィルタされているため、引数とは違っているはず
        assertNotEquals(PARTIAL_ILLEGAL_SUBCATEGORY_BREAKDOWNS, incomeStatementItem.breakdowns)
        // 正常な内訳は取り込まれているため、空ではない
        assert(incomeStatementItem.breakdowns.isNotEmpty())
    }

    @Test
    fun `Test to create by all illegal subcategory breakdowns`() {
        val incomeStatementItem =
            TestIncomeStatementItemImpl(ACCOUNT_SUBCATEGORY, ALL_ILLEGAL_SUBCATEGORY_BREAKDOWNS)

        assertEquals(ACCOUNT_SUBCATEGORY, incomeStatementItem.accountSubcategory)
        // サブカテゴリでフィルタされているため、引数とは違っているはず
        assertNotEquals(ALL_ILLEGAL_SUBCATEGORY_BREAKDOWNS, incomeStatementItem.breakdowns)
        // 全ての内訳が取り込まれていないため、空になる
        assert(incomeStatementItem.breakdowns.isEmpty())
    }


    class TestIncomeStatementItemImpl(accountSubcategory: AccountSubcategory, breakdowns: List<AccountBreakdown>) :
        AbstractIncomeStatementItem(accountSubcategory, breakdowns) {
        override fun totalAmount(): Amount {
            return AMOUNT
        }
    }

    class MockAccountBreakdown(override val accountTitle: AccountTitle, override val amount: Amount) : AccountBreakdown

}