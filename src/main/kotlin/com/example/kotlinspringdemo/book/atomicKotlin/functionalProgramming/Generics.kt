package com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming

class GenericHolder<T>(val value:T){
    fun get(): T = value
}

fun <T>identity(value:T) = value

fun <T>List<T>.secondOrNull() =
    if(isEmpty()) null else this[1]