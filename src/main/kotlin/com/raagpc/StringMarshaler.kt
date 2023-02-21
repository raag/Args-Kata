package com.raagpc

class StringMarshaler: Marshaler {
    override val type: MarshalerType
        get() = MarshalerType.STRING

    private var value: String = ""


    override fun getValue(): String {
        return value
    }

    override fun setValue(value: Iterator<String>) {
        try {
            this.value = value.next()
        } catch (e: Exception) {
            throw Exception("Invalid value for string")
        }
    }

}