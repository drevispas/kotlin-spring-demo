package com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming

val String.range
    get() = 0 until length

val String.secondOrNull
    get() = if (isEmpty()) null else this[1]

var intAccessCount = 0

fun Int.isEven(): Boolean {
    intAccessCount++
    return this % 2 == 0
}

fun Int.square(): Int {
    intAccessCount++
    return this*this
}

fun Int.lessThanTen(): Boolean {
    intAccessCount++
    return this < 10
}
