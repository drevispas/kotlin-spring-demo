package com.example.kotlinspringdemo.book.atomicKotlin.usability

import com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming.out

fun main(){
    extensionFunctions()
}

fun extensionFunctions() {
    class Book(val title:String)
    fun Book.categorize(category:String) = "${this.title} in $category"
    val book = Book("The best book")
    book.categorize("Non-Fiction").out()

    fun categorize(book: Book, category:String) = "${book.title} in $category"
    categorize(book, "Fiction").out()
}
