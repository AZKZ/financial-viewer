package com.azkz.domain

import com.azkz.domain.value.Amount
import com.azkz.domain.value.Currency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AmountTest {

    companion object {
        const val VALUE: Long = 1000
        const val TOO_SMALL_VALUE: Long = -1
        const val TOO_LARGE_VALUE: Long = 10000000000000000
        const val OUT_OF_RANGE_VALUE_MESSAGE: String = "金額の値が不正です。"
        val CURRENCY = Currency.JPY
        const val ZERO_VALUE: Long = 0
    }

    @Test
    fun `Test to create`() {
        val amount = Amount(VALUE, CURRENCY)
        assertEquals(amount.value, VALUE)
        assertEquals(amount.currency, CURRENCY)
    }

    @Test
    fun `Test to create by zero`() {
        val amount = Amount(ZERO_VALUE, CURRENCY)
        assertEquals(amount.value, ZERO_VALUE)
        assertEquals(amount.currency, CURRENCY)
    }

    @ParameterizedTest
    @ValueSource(longs = [TOO_SMALL_VALUE, TOO_LARGE_VALUE])
    fun `Test to create by out of range value`(value: Long) {
        val exception = assertThrows<IllegalArgumentException> { Amount(value, CURRENCY) }
        assertEquals(exception.message, OUT_OF_RANGE_VALUE_MESSAGE)
    }
}