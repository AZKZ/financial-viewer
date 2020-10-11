package com.azkz.domain

import com.azkz.domain.value.Name
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {

    companion object {
        const val VALUE = "テスト ネーム"
    }

    @Test
    fun `Test to create`() {
        val name = Name(VALUE)
        assertEquals(name.value, VALUE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `Test to create by blank value`(inputValue: String) {
        val exception = assertThrows<IllegalArgumentException> { Name(inputValue) }
        assertEquals(exception.message, "名称が設定されていません。")
    }

}