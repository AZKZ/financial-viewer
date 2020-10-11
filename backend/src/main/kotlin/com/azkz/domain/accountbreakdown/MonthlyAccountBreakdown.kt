package com.azkz.domain.accountbreakdown

import java.time.YearMonth

/**
 * 月別内訳インターフェース
 */
interface MonthlyAccountBreakdown : AccountBreakdown {
    val yearMonth: YearMonth
}