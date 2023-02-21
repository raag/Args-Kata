package com.raagpc

class IntegerMarshmaler: Marshmaler {
    override val type: MarshmalerType
        get() = MarshmalerType.INTEGER

    private var value: Int = 0

    override fun getValue(): Int {
        return value
    }

    override fun setValue(value: Iterator<String>) {
        try {
            this.value = value.next().toInt()
        } catch (e: Exception) {
            throw Exception("Invalid value for integer")
        }
    }

}