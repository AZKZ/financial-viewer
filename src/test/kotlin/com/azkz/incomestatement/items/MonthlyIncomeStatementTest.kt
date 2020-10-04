package com.azkz.incomestatement.items

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.impl.MonthlyIncomeStatementImpl
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.YearMonth

/**
 * 月別損益計算書クラスのテスト
 */
class MonthlyIncomeStatementTest {

    companion object {
        val YEAR_MONTH = YearMonth.parse("2020-05")
        val SALES_BREAKDOWN = MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.SALES, "売上の内訳"))
        val COST_OF_SALES_BREAKDOWN =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.COST_OF_SALES, "売上原価の内訳"))
        val SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN = MockMonthlyAccountBreakdown(
            YEAR_MONTH,
            AccountTitle(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "販売管理費の内訳")
        )
        val NON_OPERATING_INCOME_BREAKDOWN =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_INCOME, "営業外収益の内訳"))
        val NON_OPERATING_EXPENSE_BREAKDOWN =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_EXPENSE, "営業外費用の内訳"))
        val EXTRAORDINARY_INCOME_BREAKDOWN =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_INCOME, "特別損失の内訳"))
        val EXTRAORDINARY_LOSS_BREAKDOWN =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_LOSS, "特別損失の内訳"))

        val BREAKDOWNS =
            setOf<MonthlyAccountBreakdown>(
                SALES_BREAKDOWN,
                COST_OF_SALES_BREAKDOWN,
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN,
                NON_OPERATING_INCOME_BREAKDOWN,
                NON_OPERATING_EXPENSE_BREAKDOWN,
                EXTRAORDINARY_INCOME_BREAKDOWN,
                EXTRAORDINARY_LOSS_BREAKDOWN
            )


    }

    @Test
    fun `Test to create`() {
        val monthlyIncomeStatement = MonthlyIncomeStatementImpl(YEAR_MONTH, BREAKDOWNS)
        assertNotNull(monthlyIncomeStatement)

        assert(monthlyIncomeStatement.sales.breakdowns.contains(SALES_BREAKDOWN))
        assert(monthlyIncomeStatement.costOfSales.breakdowns.contains(COST_OF_SALES_BREAKDOWN))
        assert(
            monthlyIncomeStatement.sellingGeneralAdministrativeExpenses.breakdowns.contains(
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN
            )
        )
        assert(monthlyIncomeStatement.nonOperatingIncome.breakdowns.contains(NON_OPERATING_INCOME_BREAKDOWN))
        assert(monthlyIncomeStatement.nonOperatingExpenses.breakdowns.contains(NON_OPERATING_EXPENSE_BREAKDOWN))
        assert(monthlyIncomeStatement.extraordinaryIncome.breakdowns.contains(EXTRAORDINARY_INCOME_BREAKDOWN))
        assert(monthlyIncomeStatement.extraordinaryLoss.breakdowns.contains(EXTRAORDINARY_LOSS_BREAKDOWN))
    }


    /**
     * モック月別内訳クラス
     * 金額は固定
     */
    class MockMonthlyAccountBreakdown(
        override val yearMonth: YearMonth,
        override val accountTitle: AccountTitle,
    ) : MonthlyAccountBreakdown {
        override val amount: Amount = Amount(100, Currency.JPY)
    }

}