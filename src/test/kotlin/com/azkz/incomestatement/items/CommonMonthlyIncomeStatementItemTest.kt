package com.azkz.incomestatement.items

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.items.monthly.AbstractMonthlyIncomeStatementItem
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.time.YearMonth

/**
 * 月別損益計算項目クラスの共通処理部分をテストする
 */
class CommonMonthlyIncomeStatementItemTest {

    companion object {
        val AMOUNT = Amount(100, Currency.JPY)
        val ACCOUNT_SUBCATEGORY = AccountSubcategory.SALES
        val ACCOUNT_TITLE = AccountTitle(ACCOUNT_SUBCATEGORY, "売上高")


        val YEAR_MONTH = YearMonth.parse("2020-05")
        val ILLEGAL_YEAR_MONTH = YearMonth.parse("2020-10")

        /** 正常な内訳 */
        val BREAKDOWNS = listOf<MonthlyAccountBreakdown>(
            MockMonthlyAccountBreakdown(YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
            MockMonthlyAccountBreakdown(YEAR_MONTH, ACCOUNT_TITLE, AMOUNT)
        )

        /** 部分的に意図しない年月を含む内訳 */
        val PARTIAL_ILLEGAL_YEAR_MONTH_BREAKDOWNS = listOf<MonthlyAccountBreakdown>(
            MockMonthlyAccountBreakdown(YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
            MockMonthlyAccountBreakdown(ILLEGAL_YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
            MockMonthlyAccountBreakdown(YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
        )

        /** 全て意図しない年月の内訳 */
        val ALL_ILLEGAL_YEAR_MONTH_BREAKDOWNS = listOf<MonthlyAccountBreakdown>(
            MockMonthlyAccountBreakdown(ILLEGAL_YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
            MockMonthlyAccountBreakdown(ILLEGAL_YEAR_MONTH, ACCOUNT_TITLE, AMOUNT),
        )

    }

    @Test
    fun `Test to create`() {
        val incomeStatementItem =
            TestAbstractMonthlyIncomeStatementItemImpl(YEAR_MONTH, ACCOUNT_SUBCATEGORY, BREAKDOWNS)

        assertEquals(YEAR_MONTH, incomeStatementItem.yearMonth)
        assertEquals(BREAKDOWNS, incomeStatementItem.breakdowns)
    }

    @Test
    fun `Test to create by partial illegal year month breakdowns`() {
        val incomeStatementItem =
            TestAbstractMonthlyIncomeStatementItemImpl(
                YEAR_MONTH,
                ACCOUNT_SUBCATEGORY,
                PARTIAL_ILLEGAL_YEAR_MONTH_BREAKDOWNS
            )

        assertEquals(YEAR_MONTH, incomeStatementItem.yearMonth)
        // 年月でフィルタされているため、引数と一致しない
        assertNotEquals(PARTIAL_ILLEGAL_YEAR_MONTH_BREAKDOWNS, incomeStatementItem.breakdowns)
        // 正常な内訳は取り込まれているため、空ではない
        assert(incomeStatementItem.breakdowns.isNotEmpty())
    }

    @Test
    fun `Test to create by all illegal year month breakdowns`() {
        val incomeStatementItem =
            TestAbstractMonthlyIncomeStatementItemImpl(
                YEAR_MONTH,
                ACCOUNT_SUBCATEGORY,
                ALL_ILLEGAL_YEAR_MONTH_BREAKDOWNS
            )

        assertEquals(YEAR_MONTH, incomeStatementItem.yearMonth)
        // 年月でフィルタされているため、引数と一致しない
        assertNotEquals(ALL_ILLEGAL_YEAR_MONTH_BREAKDOWNS, incomeStatementItem.breakdowns)
        // 全ての内訳が取り込まれていないため、空になる
        assert(incomeStatementItem.breakdowns.isEmpty())
    }

    class TestAbstractMonthlyIncomeStatementItemImpl(
        yearMonth: YearMonth,
        accountSubcategory: AccountSubcategory,
        breakdowns: List<MonthlyAccountBreakdown>
    ) :
        AbstractMonthlyIncomeStatementItem(
            yearMonth = yearMonth,
            accountSubcategory = accountSubcategory,
            breakdowns = breakdowns
        ) {
        override fun totalAmount(): Amount {
            return AMOUNT
        }
    }

    class MockMonthlyAccountBreakdown(
        override val yearMonth: YearMonth,
        override val accountTitle: AccountTitle,
        override val amount: Amount
    ) : MonthlyAccountBreakdown

}