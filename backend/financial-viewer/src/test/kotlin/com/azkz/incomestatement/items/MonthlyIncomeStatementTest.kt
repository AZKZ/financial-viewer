package com.azkz.incomestatement.items

import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import com.azkz.domain.incomestatement.impl.MonthlyIncomeStatementImpl
import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Test
import java.time.YearMonth

/**
 * 月別損益計算書クラスのテスト
 */
class MonthlyIncomeStatementTest {

    companion object {
        val YEAR_MONTH = YearMonth.parse("2020-05")

        /** 売上の内訳 */
        val SALES_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.SALES, "売上の内訳1"))
        val SALES_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.SALES, "売上の内訳2"))

        /** 売上原価の内訳 */
        val COST_OF_SALES_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.COST_OF_SALES, "売上原価の内訳1"))
        val COST_OF_SALES_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.COST_OF_SALES, "売上原価の内訳2"))

        /** 販売管理費の内訳 */
        val SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(
                YEAR_MONTH,
                AccountTitle(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "販売管理費の内訳1")
            )
        val SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(
                YEAR_MONTH,
                AccountTitle(AccountSubcategory.SELLING_GENERAL_ADMINISTRATIVE_EXPENSE, "販売管理費の内訳2")
            )

        /** 営業外収益の内訳 */
        val NON_OPERATING_INCOME_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_INCOME, "営業外収益の内訳1"))
        val NON_OPERATING_INCOME_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_INCOME, "営業外収益の内訳2"))

        /** 営業外費用の内訳 */
        val NON_OPERATING_EXPENSE_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_EXPENSE, "営業外費用の内訳1"))
        val NON_OPERATING_EXPENSE_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.NON_OPERATING_EXPENSE, "営業外費用の内訳2"))

        /** 特別利益の内訳 */
        val EXTRAORDINARY_INCOME_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_INCOME, "特別利益の内訳1"))
        val EXTRAORDINARY_INCOME_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_INCOME, "特別利益の内訳2"))

        /** 特別損失の内訳 */
        val EXTRAORDINARY_LOSS_BREAKDOWN1 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_LOSS, "特別損失の内訳1"))
        val EXTRAORDINARY_LOSS_BREAKDOWN2 =
            MockMonthlyAccountBreakdown(YEAR_MONTH, AccountTitle(AccountSubcategory.EXTRAORDINARY_LOSS, "特別損失の内訳2"))

        /** 正常な内訳の集合 */
        val BREAKDOWNS =
            setOf<MonthlyAccountBreakdown>(
                SALES_BREAKDOWN1,
                COST_OF_SALES_BREAKDOWN1,
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN1,
                NON_OPERATING_INCOME_BREAKDOWN1,
                NON_OPERATING_EXPENSE_BREAKDOWN1,
                EXTRAORDINARY_INCOME_BREAKDOWN1,
                EXTRAORDINARY_LOSS_BREAKDOWN1,
                SALES_BREAKDOWN2,
                COST_OF_SALES_BREAKDOWN2,
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN2,
                NON_OPERATING_INCOME_BREAKDOWN2,
                NON_OPERATING_EXPENSE_BREAKDOWN2,
                EXTRAORDINARY_INCOME_BREAKDOWN2,
                EXTRAORDINARY_LOSS_BREAKDOWN2,
            )

        /** 空の内訳の集合 */
        val EMPTY_BREAKDOWNS = emptySet<MonthlyAccountBreakdown>()


    }

    @Test
    fun `Test to create`() {
        val monthlyIncomeStatement = MonthlyIncomeStatementImpl(YEAR_MONTH, BREAKDOWNS)

        // それぞれの内訳に対応する項目に格納されていることを確認
        assert(
            monthlyIncomeStatement.sales.breakdowns.contains(
                SALES_BREAKDOWN1,
            )
        )
        assert(
            monthlyIncomeStatement.sales.breakdowns.contains(
                SALES_BREAKDOWN2,
            )
        )
        assert(
            monthlyIncomeStatement.costOfSales.breakdowns.contains(
                COST_OF_SALES_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.costOfSales.breakdowns.contains(
                COST_OF_SALES_BREAKDOWN2
            )
        )
        assert(
            monthlyIncomeStatement.sellingGeneralAdministrativeExpenses.breakdowns.contains(
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.sellingGeneralAdministrativeExpenses.breakdowns.contains(
                SELLING_GENERAL_ADMINISTRATIVE_EXPENSE_BREAKDOWN2
            )
        )
        assert(
            monthlyIncomeStatement.nonOperatingIncome.breakdowns.contains(
                NON_OPERATING_INCOME_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.nonOperatingIncome.breakdowns.contains(
                NON_OPERATING_INCOME_BREAKDOWN2
            )
        )
        assert(
            monthlyIncomeStatement.nonOperatingExpenses.breakdowns.contains(
                NON_OPERATING_EXPENSE_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.nonOperatingExpenses.breakdowns.contains(
                NON_OPERATING_EXPENSE_BREAKDOWN2
            )
        )
        assert(
            monthlyIncomeStatement.extraordinaryIncome.breakdowns.contains(
                EXTRAORDINARY_INCOME_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.extraordinaryIncome.breakdowns.contains(
                EXTRAORDINARY_INCOME_BREAKDOWN2
            )
        )
        assert(
            monthlyIncomeStatement.extraordinaryLoss.breakdowns.contains(
                EXTRAORDINARY_LOSS_BREAKDOWN1
            )
        )
        assert(
            monthlyIncomeStatement.extraordinaryLoss.breakdowns.contains(
                EXTRAORDINARY_LOSS_BREAKDOWN2
            )
        )
    }

    @Test
    fun `Test to create by empty breakdowns`() {
        val monthlyIncomeStatement = MonthlyIncomeStatementImpl(YEAR_MONTH, EMPTY_BREAKDOWNS)

        /** 各項目の内訳が空でできていることを確認 */
        assert(monthlyIncomeStatement.sales.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.costOfSales.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.sellingGeneralAdministrativeExpenses.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.nonOperatingIncome.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.nonOperatingExpenses.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.extraordinaryIncome.breakdowns.isEmpty())
        assert(monthlyIncomeStatement.extraordinaryLoss.breakdowns.isEmpty())
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