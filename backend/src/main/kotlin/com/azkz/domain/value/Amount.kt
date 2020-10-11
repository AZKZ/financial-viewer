package com.azkz.domain.value

class Amount(val value: Long, val currency: Currency) {

    init {
        if (value < 0 || 9999999999999999 < value) throw IllegalArgumentException("金額の値が不正です。")
    }

}