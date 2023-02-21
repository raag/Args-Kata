package com.raagpc

class Args {

    private val marshalers = mutableMapOf<String, Marshaler>()

    fun parseSchema(schema: String) {
        val tokens = schema.split(",")
        for (token in tokens) {
            if (token.endsWith("@")) {
                parseBooleanSchema(token)
            } else if (token.endsWith("#")) {
                parseIntegerSchema(token)
            } else if (token.endsWith("*")) {
                parseStringSchema(token)
            } else {
                throw IllegalArgumentException("Invalid flag: $token")
            }
        }
    }

    fun getInfo(flag: String): Marshaler? {
        return marshalers[flag]
    }

    private fun parseBooleanSchema(token: String) {
        marshalers[token.substring(0, token.length - 1)] = BooleanMarshaler()
    }

    private fun parseIntegerSchema(token: String) {
        marshalers[token.substring(0, token.length - 1)] = IntegerMarshaler()
    }

    private fun parseStringSchema(token: String) {
        marshalers[token.substring(0, token.length - 1)] = StringMarshaler()
    }

    fun parseArgs(args: String) {
        val tokens = args.split(" ")
        val argsIterator = tokens.listIterator()
        while (argsIterator.hasNext()) {
            val arg = argsIterator.next()
            if (arg.startsWith("-").not()) {
                throw IllegalArgumentException("Invalid argument: $args")
            }
            val marshaller = marshalers[arg.substring(1)] ?: throw IllegalArgumentException("Invalid flag: $arg")
            marshaller.setValue(argsIterator)
        }
    }

    fun getBooleanValue(flag: String): Boolean {
        return marshalers[flag]?.getValue() as Boolean
    }

    fun getIntegerValue(flag: String): Int {
        return marshalers[flag]?.getValue() as Int
    }

    fun getStringValue(flag: String): String {
        return marshalers[flag]?.getValue() as String
    }
}