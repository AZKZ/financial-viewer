package com.azkz.domain.value

import java.time.LocalDate

/**
 * 対象期間クラス
 */
class Term(fromDate: LocalDate, toDate: LocalDate) {
    val fromDate: LocalDate
    val toDate: LocalDate

    init {
        if (fromDate.isAfter(toDate)) throw IllegalArgumentException("日付関係が不正です。")
        this.fromDate = fromDate
        this.toDate = toDate
    }
}