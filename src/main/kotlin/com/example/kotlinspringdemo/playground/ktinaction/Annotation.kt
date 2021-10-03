package com.example.kotlinspringdemo.playground.ktinaction

import com.example.kotlinspringdemo.playground.ktinaction.jkid.CustomSerializer
import com.example.kotlinspringdemo.playground.ktinaction.jkid.ValueSerializer
import com.example.kotlinspringdemo.playground.ktinaction.jkid.deserialization.deserialize
import com.example.kotlinspringdemo.playground.ktinaction.jkid.serialize
import java.util.*

fun main(args: Array<String>) {
    serialize(Person("brad",99)).stdout()
    deserialize<Person>("""{"name":"yong","age":1}""").stdout()
    serialize(JsonPerson("yong", 3)).stdout()
    deserialize<JsonPerson>("""{"name":"YONG", "age":3}""").stdout()
}
data class JsonPerson(@CustomSerializer(NameSerializer::class) val name:String, val age:Int)
class NameSerializer: ValueSerializer<String> {
    override fun toJsonValue(value: String): Any? = value.uppercase()
    override fun fromJsonValue(jsonValue: Any?): String = (jsonValue as String).lowercase()
}