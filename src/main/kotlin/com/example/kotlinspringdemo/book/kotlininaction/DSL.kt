package com.example.kotlinspringdemo.book.kotlininaction

import java.time.LocalDate
import java.time.Period

fun main(args: Array<String>) {
    /* lambda with a receiver */
    // built-in function
    buildString { append("Hello"); append(", World1") }.stdout()
    // lambda as a argument
    fun buildString1(action: (StringBuilder) -> Unit): String {
        val sb = StringBuilder()
        action(sb)
        return sb.toString()
    }
    buildString1 { it.append("Hello"); it.append(", World2") }.stdout()
    // lambda with a receiver
    fun buildString2(action: StringBuilder.() -> Unit): String {
        val sb = StringBuilder()
        sb.action()
        return sb.toString()
    }
    buildString2 { append("Hello"); append(", World3") }.stdout() // can omit `it`
    fun buildString3(action: StringBuilder.() -> Unit) =
        StringBuilder().apply(action).toString()
    buildString3 { append("Hello"); append(", World6") }.stdout()
    // actually extension function
    val add: StringBuilder.(String) -> StringBuilder = {
        append(it)
        this
    }
    StringBuilder().add("Hello").add(", World4").toString().stdout()
    fun StringBuilder.add2(s: String): StringBuilder {
        append(s)
        return this
    }
    StringBuilder().add2("Hello").add2(", World5").toString().stdout()

    /* invoke */
    class Std<T> {
        fun out(o: T) = println(o)
        operator fun invoke(action: Std<T>.() -> Unit) = action()
    }

    val std = Std<String>()
    std.out("printed out 1")
    std {
        out("printed out 2")
        out("printed out 3")
    }

    /* infix */
    abstract class MyMatcher<T> {
        abstract fun test(target: T)
    }

    class startWith(val prefix: String) : MyMatcher<String>() {
        override fun test(str: String) {
            if (str.startsWith(prefix)) println("PASSED")
            else println("FAILED")
        }
    }

    infix fun <T> T.should(matcher: MyMatcher<T>) = matcher.test(this)
    "Hello, World" should startWith("Her") // String.should(startWith(String))


    infix fun <T> T.must(dummy: start) = this
    infix fun String.with(prefix: String) {
        if (startsWith(prefix)) println("PASSED")
        else println("FAILED")
    }
    "kotlin" must start with "ko" // String.should(object).with(String)

    /* extension property */

    val yesterday = 1.days.ago
    val tomorrow = 1.days.fromNow()
    println("yesterday: $yesterday, tomorrow: $tomorrow")
}

object start

val Int.days: Period
    get() = Period.ofDays(this)
val Period.ago: LocalDate
    get() = LocalDate.now() - this

fun Period.fromNow(): LocalDate = LocalDate.now() + this
