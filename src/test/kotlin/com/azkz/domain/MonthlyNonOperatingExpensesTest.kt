package com.azkz.domain

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.items.monthly.impl.MonthlyNonOperatingExpensesImpl
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth

class MonthlyNonOperatingExpensesTest {

    companion object {
        val YEAR_MONTH = YearMonth.parse("2020-01")
        val ACCOUNT_SUBCATEGORY = AccountSubcategory.NON_OPERATING_EXPENSE
        val ACCOUNT_TITLES = AccountTitle.values().filter { it.subcategory == ACCOUNT_SUBCATEGORY }
        val ILLEGAL_ACCOUNT_TITLES = AccountTitle.values().filterNot { it.subcategory == ACCOUNT_SUBCATEGORY }
        val ILLEGAL_YEAR_MONTH = YEAR_MONTH.minusMonths(1)
        val BREAKDOWNS: List<MonthlyAccountBreakdown> = listOf(
            MonthlyAccountBreakdownImpl(YEAR_MONTH, ACCOUNT_TITLES[0], Amount(100, Currency.JPY)),
            MonthlyAccountBreakdownImpl(YEAR_MONTH, ACCOUNT_TITLES[1], Amount(100, Currency.JPY))
        )
        val ILLEGAL_ACCOUNT_TITLES_BREAKDOWN: List<MonthlyAccountBreakdown> = listOf(
            MonthlyAccountBreakdownImpl(YEAR_MONTH, ACCOUNT_TITLES[0], Amount(100, Currency.JPY)),
            MonthlyAccountBreakdownImpl(YEAR_MONTH, ILLEGAL_ACCOUNT_TITLES[0], Amount(100, Currency.JPY))
        )
        val ILLEGAL_YEAR_MONTH_BREAKDOWN: List<MonthlyAccountBreakdown> = listOf(
            MonthlyAccountBreakdownImpl(YEAR_MONTH, ACCOUNT_TITLES[0], Amount(100, Currency.JPY)),
            MonthlyAccountBreakdownImpl(ILLEGAL_YEAR_MONTH, ACCOUNT_TITLES[1], Amount(100, Currency.JPY))
        )
        val EMPTY_BREAKDOWNS: List<MonthlyAccountBreakdown> = listOf()
    }

    @Test
    fun `Test to create`() {
        val monthlyNonOperatingExpenses = MonthlyNonOperatingExpensesImpl(YEAR_MONTH, BREAKDOWNS)
        assertEquals(monthlyNonOperatingExpenses.accountSubcategory, ACCOUNT_SUBCATEGORY)
        assertEquals(monthlyNonOperatingExpenses.yearMonth, YEAR_MONTH)
        assertEquals(monthlyNonOperatingExpenses.breakdowns, BREAKDOWNS)
    }

    @Test
    fun `Test to create by empty breakdowns`() {
        val monthlyNonOperatingExpenses = MonthlyNonOperatingExpensesImpl(YEAR_MONTH, EMPTY_BREAKDOWNS)
        assertEquals(monthlyNonOperatingExpenses.breakdowns, EMPTY_BREAKDOWNS)
        assertEquals(monthlyNonOperatingExpenses.yearMonth, YEAR_MONTH)
        assertEquals(monthlyNonOperatingExpenses.breakdowns, EMPTY_BREAKDOWNS)
    }

    @Test
    fun `Test to create by illegal account titles breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyNonOperatingExpensesImpl(
                YEAR_MONTH,
                ILLEGAL_ACCOUNT_TITLES_BREAKDOWN
            )
        }
    }

    @Test
    fun `Test to create by illegal year month breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyNonOperatingExpensesImpl(
                YEAR_MONTH,
                ILLEGAL_YEAR_MONTH_BREAKDOWN
            )
        }
    }
}