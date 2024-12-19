package com.example.itm.util.serializer

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapterFactory : TypeAdapterFactory {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        if (type.rawType == Date::class.java) {
            return DateTypeAdapter(dateFormat) as TypeAdapter<T>
        }
        return null
    }

    class DateTypeAdapter(private val dateFormat: SimpleDateFormat) : TypeAdapter<Date>() {
        override fun write(out: com.google.gson.stream.JsonWriter?, value: Date?) {
            out?.value(dateFormat.format(value))
        }

        override fun read(`in`: com.google.gson.stream.JsonReader?): Date? {
            return dateFormat.parse(`in`?.nextString()) ?: Date()
        }
    }
}
