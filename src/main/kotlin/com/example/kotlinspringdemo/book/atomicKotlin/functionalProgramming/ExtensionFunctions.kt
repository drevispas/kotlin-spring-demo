package com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming

fun Any.out() {
    println(this)
}
fun String.singleQuote() = "'$this'"
fun String.doubleSingleQuote() = singleQuote().singleQuote()

