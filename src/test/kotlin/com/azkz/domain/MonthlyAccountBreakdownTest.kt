package com.azkz.domain

import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth

class MonthlyAccountBreakdownTest {

    companion object {
        val YEAR_MONTH: YearMonth = YearMonth.of(2020, 1)
        val ACCOUNT_TITLE: AccountTitle = AccountTitle(AccountSubcategory.SALES, "売上高")
        val AMOUNT: Amount = Amount(10000, Currency.JPY)
        val AFTER_YEAR_MONTH: YearMonth = YearMonth.now().plusMonths(1)
        val CURRENT_YEAR_MONTH: YearMonth = YearMonth.now()
        const val AFTER_CURRENT_MONTH_MESSAGE: String = "当月以降のデータは作成できません"

    }

    @Test
    fun `Test to create`() {
        val monthlyAccountBreakdown = MonthlyAccountBreakdownImpl(YEAR_MONTH, ACCOUNT_TITLE, AMOUNT)
        assertEquals(monthlyAccountBreakdown.yearMonth, YEAR_MONTH)
        assertEquals(monthlyAccountBreakdown.accountTitle, ACCOUNT_TITLE)
        assertEquals(monthlyAccountBreakdown.amount, AMOUNT)
    }

    @Test
    fun `Test to create by after year month`() {
        val exception = assertThrows<IllegalArgumentException> {
            MonthlyAccountBreakdownImpl(
                AFTER_YEAR_MONTH,
                ACCOUNT_TITLE, AMOUNT
            )
        }
        assertEquals(exception.message, AFTER_CURRENT_MONTH_MESSAGE)
    }

    @Test
    fun `Test to create by current year month`() {
        val exception = assertThrows<IllegalArgumentException> {
            MonthlyAccountBreakdownImpl(
                CURRENT_YEAR_MONTH,
                ACCOUNT_TITLE, AMOUNT
            )
        }
        assertEquals(exception.message, AFTER_CURRENT_MONTH_MESSAGE)
    }
}