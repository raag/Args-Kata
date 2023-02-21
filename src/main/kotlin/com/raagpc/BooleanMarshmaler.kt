package com.raagpc

class BooleanMarshmaler : Marshmaler {
    override val type: MarshmalerType
        get() = MarshmalerType.BOOLEAN

    private var value: Boolean = false

    override fun getValue(): Boolean {
        return value
    }

    override fun setValue(value: Iterator<String>) {
        try {
            this.value = true
        } catch (e: Exception) {
            throw Exception("Invalid value for boolean")
        }
    }
}