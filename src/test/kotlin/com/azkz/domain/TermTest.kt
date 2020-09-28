package com.azkz.domain

import com.azkz.domain.value.Term
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class TermTest {

    companion object {
        val FROM_DATE = LocalDate.now()
        val TO_DATE = LocalDate.now().plusMonths(1)
        const val REVERSE_DATE_MESSAGE = "日付関係が不正です。"
    }

    @Test
    fun testCreate() {
        val term = Term(FROM_DATE, TO_DATE)
        assertEquals(term.fromDate, FROM_DATE)
        assertEquals(term.toDate, TO_DATE)
    }

    @Test
    fun testCreateSameDate() {
        val term = Term(FROM_DATE, FROM_DATE)
        assertEquals(term.fromDate, FROM_DATE)
        assertEquals(term.toDate, FROM_DATE)
    }

    @Test
    fun testCreateReverseDate() {
        val exception = assertThrows<IllegalArgumentException> { Term(TO_DATE, FROM_DATE) }
        assertEquals(exception.message, REVERSE_DATE_MESSAGE)
    }


}