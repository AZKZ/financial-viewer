package com.azkz.domain.value

import org.apache.commons.lang3.StringUtils

class Name(value: String) {
    val value: String

    init {
        if (StringUtils.isBlank(value)) throw IllegalArgumentException("名称が設定されていません。")
        this.value = value
    }
}