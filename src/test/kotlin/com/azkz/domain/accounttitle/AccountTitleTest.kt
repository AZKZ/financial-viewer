package com.azkz.domain.accounttitle

import com.azkz.domain.accountitle.AccountSubcategory
import com.azkz.domain.accountitle.AccountTitle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class AccountTitleTest {

    companion object {
        const val JAPANESE = "売上高"
    }


    @Test
    fun `Test to create`() {
        val accountTitle = AccountTitle(AccountSubcategory.SALES, JAPANESE)
        assertEquals(accountTitle.japanese, JAPANESE)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "   ", "\t", "\n"])
    fun `Test to create by japanese empty`(emptyStr: String) {
        val exception = assertThrows<IllegalArgumentException> { AccountTitle(AccountSubcategory.SALES, emptyStr) }
        assertEquals(exception.message, "勘定科目の日本語名は必須です。")
    }

}