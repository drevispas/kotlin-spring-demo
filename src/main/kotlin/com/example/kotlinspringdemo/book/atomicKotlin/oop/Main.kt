package com.example.kotlinspringdemo.book.atomicKotlin.oop

import com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming.out

fun main() {
    interfaces()
}

fun interface zeroArg {
    fun f(): Int
}
fun interface TwoArgs {
    fun f(i: Int, j: Int): Int
}
fun interfaces() {
    // SAM
    val twoArgs: TwoArgs = TwoArgs {i,j -> i+j}
    twoArgs.f(3,5).out()

    fun doAction(zeroArg: zeroArg) = zeroArg.f()
    doAction { 41 }.out()
}
