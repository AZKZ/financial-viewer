package com.azkz.domain

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.items.monthly.impl.MonthlyNonOperatingIncomeImpl
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth

class MonthlyNonOperatingIncomeTest {

    companion object {
        val YEAR_MONTH = YearMonth.parse("2020-01")
        val ACCOUNT_SUBCATEGORY = AccountSubcategory.NON_OPERATING_INCOME
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
        val monthlyNonOperatingIncome = MonthlyNonOperatingIncomeImpl(YEAR_MONTH, BREAKDOWNS)
        assertEquals(monthlyNonOperatingIncome.accountSubcategory, ACCOUNT_SUBCATEGORY)
        assertEquals(monthlyNonOperatingIncome.yearMonth, YEAR_MONTH)
        assertEquals(monthlyNonOperatingIncome.breakdowns, BREAKDOWNS)
    }

    @Test
    fun `Test to create by empty breakdowns`() {
        val monthlyNonOperatingIncome = MonthlyNonOperatingIncomeImpl(YEAR_MONTH, EMPTY_BREAKDOWNS)
        assertEquals(monthlyNonOperatingIncome.breakdowns, EMPTY_BREAKDOWNS)
        assertEquals(monthlyNonOperatingIncome.yearMonth, YEAR_MONTH)
        assertEquals(monthlyNonOperatingIncome.breakdowns, EMPTY_BREAKDOWNS)
    }

    @Test
    fun `Test to create by illegal account titles breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyNonOperatingIncomeImpl(
                YEAR_MONTH,
                ILLEGAL_ACCOUNT_TITLES_BREAKDOWN
            )
        }
    }

    @Test
    fun `Test to create by illegal year month breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyNonOperatingIncomeImpl(
                YEAR_MONTH,
                ILLEGAL_YEAR_MONTH_BREAKDOWN
            )
        }
    }

}