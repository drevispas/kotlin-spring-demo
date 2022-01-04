package com.example.kotlinspringdemo.book.kotlininaction

import com.example.kotlinspringdemo.book.kotlininaction.jkid.CustomSerializer
import com.example.kotlinspringdemo.book.kotlininaction.jkid.ValueSerializer
import com.example.kotlinspringdemo.book.kotlininaction.jkid.deserialization.deserialize
import com.example.kotlinspringdemo.playground.ktinaction.jkid.serialize
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun main(args: Array<String>) {
    /* Test Jkid serialization */
    serialize(Person("brad",99)).stdout()
    deserialize<Person>("""{"name":"yong","age":1}""").stdout()

    /* Custom serializer */
    serialize(JsonPerson("yong", 3)).stdout()
    deserialize<JsonPerson>("""{"name":"YONG", "age":3}""").stdout()
    println("-----")

    /* KClass */
    val kClass = Person::class
    println("simpleName: ${kClass.simpleName}")
    kClass.memberProperties.forEach { println(it.name) }
    println("-----")

    /* KFunction interfaces */
    fun foo(x:Int) = println(x)
    val kfun1 = ::foo
    kfun1.invoke(32)
    fun sum(x: Int, y: Int) = x + y
    val kfun2: KFunction2<Int, Int, Int> = ::sum
    kfun2.invoke(3, 4).stdout()
    kfun2(5, 6).stdout()
    val person = Person("yong",10)
    val memberProperty = Person::age
    memberProperty.get(person).stdout()
}

data class JsonPerson(@CustomSerializer(NameSerializer::class) val name:String, val age:Int)
class NameSerializer: ValueSerializer<String> {
    override fun toJsonValue(value: String): Any? = value.uppercase()
    override fun fromJsonValue(jsonValue: Any?): String = (jsonValue as String).lowercase()
}
