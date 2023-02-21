package com.raagpc

interface Marshmaler {
    val type: MarshmalerType
    fun getValue(): Any?
    fun setValue(value: Iterator<String>)
}

enum class MarshmalerType() {
    BOOLEAN,
    INTEGER,
    STRING
}