package com.azkz.domain

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountbreakdown.impl.MonthlyAccountBreakdownImpl
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.items.monthly.impl.MonthlyExtraordinaryIncomeImpl
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth

class MonthlyExtraordinaryIncomeTest {

    companion object {
        val YEAR_MONTH = YearMonth.parse("2020-01")
        val ACCOUNT_SUBCATEGORY = AccountSubcategory.EXTRAORDINARY_INCOME
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
        val monthlyExtraordinaryIncome = MonthlyExtraordinaryIncomeImpl(YEAR_MONTH, BREAKDOWNS)
        assertEquals(monthlyExtraordinaryIncome.accountSubcategory, ACCOUNT_SUBCATEGORY)
        assertEquals(monthlyExtraordinaryIncome.yearMonth, YEAR_MONTH)
        assertEquals(monthlyExtraordinaryIncome.breakdowns, BREAKDOWNS)
    }

    @Test
    fun `Test to create by empty breakdowns`() {
        val monthlyExtraordinaryIncome = MonthlyExtraordinaryIncomeImpl(YEAR_MONTH, EMPTY_BREAKDOWNS)
        assertEquals(monthlyExtraordinaryIncome.breakdowns, EMPTY_BREAKDOWNS)
        assertEquals(monthlyExtraordinaryIncome.yearMonth, YEAR_MONTH)
        assertEquals(monthlyExtraordinaryIncome.breakdowns, EMPTY_BREAKDOWNS)
    }

    @Test
    fun `Test to create by illegal account titles breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyExtraordinaryIncomeImpl(
                YEAR_MONTH,
                ILLEGAL_ACCOUNT_TITLES_BREAKDOWN
            )
        }
    }

    @Test
    fun `Test to create by illegal year month breakdowns`() {
        assertThrows<IllegalArgumentException> {
            MonthlyExtraordinaryIncomeImpl(
                YEAR_MONTH,
                ILLEGAL_YEAR_MONTH_BREAKDOWN
            )
        }
    }

}