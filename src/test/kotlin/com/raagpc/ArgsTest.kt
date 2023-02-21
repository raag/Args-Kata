package com.raagpc

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArgsTest {
    @Test
    fun `parse schema`() {
        val args = Args()
        args.parseSchema("l@,p#,d*")
    }

    @Test
    fun `parse schema with boolean`() {
        val args = Args()
        args.parseSchema("l@")
        val schemaInfo = args.getInfo("l")
        assertEquals(MarshmalerType.BOOLEAN, schemaInfo?.type)
    }

    @Test
    fun `parse schema with integer`() {
        val args = Args()
        args.parseSchema("p#")
        val schemaInfo = args.getInfo("p")
        assertEquals(MarshmalerType.INTEGER, schemaInfo?.type)
    }

    @Test
    fun `parse schema with string`() {
        val args = Args()
        args.parseSchema("d*")
        val schemaInfo = args.getInfo("d")
        assertEquals(MarshmalerType.STRING, schemaInfo?.type)
    }

    @Test
    fun `parse schema with multiple types`() {
        val args = Args()
        args.parseSchema("l@,p#,d*")
        val schemaInfo1 = args.getInfo("l")
        assertEquals(MarshmalerType.BOOLEAN, schemaInfo1?.type)
        val schemaInfo2 = args.getInfo("p")
        assertEquals(MarshmalerType.INTEGER, schemaInfo2?.type)
        val schemaInfo3 = args.getInfo("d")
        assertEquals(MarshmalerType.STRING, schemaInfo3?.type)
    }

    @Test
    fun `parse schema with invalid flags`() {
        val args = Args()
        try {
            args.parseSchema("l@,p#,d-")
            assertTrue(false)
        } catch (e: Exception) {
            assertEquals("Invalid flag: d-", e.message)
        }
    }

    @Test
    fun `get boolean value`() {
        val args = Args()
        args.parseSchema("l@")
        args.parseArgs("-l")
        val value = args.getBooleanValue("l")
        assertEquals(true, value)
    }

    @Test
    fun `get integer value`() {
        val args = Args()
        args.parseSchema("i#")
        args.parseArgs("-i 10")
        val value = args.getIntegerValue("i")
        assertEquals(10, value)
    }

    @Test
    fun `get string value`() {
        val args = Args()
        args.parseSchema("s*")
        args.parseArgs("-s hello")
        val value = args.getStringValue("s")
        assertEquals("hello", value)
    }

    @Test
    fun `get multiple values`() {
        val args = Args()
        args.parseSchema("l@,p#,d*")
        args.parseArgs("-l -p 8080 -d /usr/logs")
        val value1 = args.getBooleanValue("l")
        assertEquals(true, value1)
        val value2 = args.getIntegerValue("p")
        assertEquals(8080, value2)
        val value3 = args.getStringValue("d")
        assertEquals("/usr/logs", value3)
    }
}