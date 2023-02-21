package com.raagpc

class Args {

    private val marshmallers = mutableMapOf<String, Marshmaler>()

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

    fun getInfo(flag: String): Marshmaler? {
        return marshmallers[flag]
    }

    private fun parseBooleanSchema(token: String) {
        marshmallers[token.substring(0, token.length - 1)] = BooleanMarshmaler()
    }

    private fun parseIntegerSchema(token: String) {
        marshmallers[token.substring(0, token.length - 1)] = IntegerMarshmaler()
    }

    private fun parseStringSchema(token: String) {
        marshmallers[token.substring(0, token.length - 1)] = StringMarshmaler()
    }

    fun parseArgs(args: String) {
        val tokens = args.split(" ")
        val argsIterator = tokens.listIterator()
        while (argsIterator.hasNext()) {
            val arg = argsIterator.next()
            if (arg.startsWith("-").not()) {
                throw IllegalArgumentException("Invalid argument: $args")
            }
            val marshaller = marshmallers[arg.substring(1)] ?: throw IllegalArgumentException("Invalid flag: $arg")
            marshaller.setValue(argsIterator)
        }
    }

    fun getBooleanValue(flag: String): Boolean {
        return marshmallers[flag]?.getValue() as Boolean
    }

    fun getIntegerValue(flag: String): Int {
        return marshmallers[flag]?.getValue() as Int
    }

    fun getStringValue(flag: String): String {
        return marshmallers[flag]?.getValue() as String
    }
}