package com.raagpc

interface Marshaler {
    val type: MarshalerType
    fun getValue(): Any?
    fun setValue(value: Iterator<String>)
}

enum class MarshalerType() {
    BOOLEAN,
    INTEGER,
    STRING
}