package com.azkz.domain.accountbreakdown

import java.time.YearMonth

interface MonthlyAccountBreakdown : AccountBreakdown {
    val yearMonth: YearMonth
}