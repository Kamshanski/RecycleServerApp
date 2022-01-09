package edu.tpu.ruban.util.kotlin.collections


@Suppress("MemberVisibilityCanBePrivate", "unused")
class ClaimsBundle {
    private val map = mutableMapOf<String, Any>()

    fun forEach(consumer: (key: String, value: Any) -> Unit) {
        map.forEach(consumer)
    }

    // PUT

    fun putBoolean(key: String, value: Boolean) {
        map[key] = value
    }

    fun putInt(key: String, value: Int) {
        map[key] = value
    }

    fun putLong(key: String, value: Long) {
        map[key] = value
    }

    fun putDouble(key: String, value: Double) {
        map[key] = value
    }

    fun putString(key: String, value: String) {
        map[key] = value
    }

    fun putIntegerArrayList(key: String, value: ArrayList<Int>) {
        map[key] = value
    }

    fun putLongArrayList(key: String, value: ArrayList<Long>) {
        map[key] = value
    }

    fun putStringArrayList(key: String, value: ArrayList<String>) {
        map[key] = value
    }


    // GET

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val o = map[key] ?: return defaultValue
        return try {
            o as Boolean
        } catch (e: ClassCastException) {
            typeWarning(key, o, "Boolean", defaultValue, e)
            defaultValue
        }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        val o = map[key] ?: return defaultValue
        return try {
            o as Int
        } catch (e: ClassCastException) {
            typeWarning(key, o, "Integer", defaultValue, e)
            defaultValue
        }
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {
        val o = map[key] ?: return defaultValue
        return try {
            o as Long
        } catch (e: ClassCastException) {
            typeWarning(key, o, "Long", defaultValue, e)
            defaultValue
        }
    }

    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        val o = map[key] ?: return defaultValue
        return try {
            o as Double
        } catch (e: ClassCastException) {
            typeWarning(key, o, "Double", defaultValue, e)
            defaultValue
        }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        val o = map[key] ?: return defaultValue
        return try {
            o as String
        } catch (e: ClassCastException) {
            typeWarning(key, o, "String", e)
            null
        }
    }

    fun getIntegerArrayList(key: String): ArrayList<Int>? {
        val o = map[key] ?: return null
        return try {
            o as ArrayList<Int>
        } catch (e: ClassCastException) {
            typeWarning(key, o, "ArrayList<Integer>", e)
            null
        }
    }

    fun getLongArrayList(key: String): ArrayList<Long>? {
        val o = map[key] ?: return null
        return try {
            o as ArrayList<Long>
        } catch (e: ClassCastException) {
            typeWarning(key, o, "ArrayList<String>", e)
            null
        }
    }

    fun getStringArrayList(key: String): ArrayList<String>? {
        val o = map[key] ?: return null
        return try {
            o as ArrayList<String>
        } catch (e: ClassCastException) {
            typeWarning(key, o, "ArrayList<String>", e)
            null
        }
    }
    
    fun getAny(key: String) : Any? {
        return map[key]
    }

    private fun typeWarning(
        key: String, 
        value: Any, 
        className: String?,
        defaultValue: Any? = "<null>", 
        e: ClassCastException? = null
    ) {
        val sb = StringBuilder()
        sb.append("Key ")
        sb.append(key)
        sb.append(" expected ")
        sb.append(className)
        sb.append(" but value was a ")
        sb.append(value.javaClass.name)
        sb.append(".  The default value ")
        sb.append(defaultValue)
        sb.append(" was returned.")
        println(sb.toString())
        println("Attempt to cast generated internal exception: ${e.toString()}")
    }
}