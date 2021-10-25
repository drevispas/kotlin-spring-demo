package com.example.kotlinspringdemo.book.programmingkotlinapplication

const val PI = 3.14

class Person(var firstName: String, val lastName: String) { // primary constructor
    // secondary constructor
    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName){
        this.age = age
    }

    val fullName: String

    var age: Int = 20
        get() = field
        set(value) {
            field = if (value < 0 || value > 150) 0 else value
        }

    lateinit var ssn:String

    init {
        fullName = "$firstName $lastName"
    }

    override fun toString(): String = "$fullName (age:$age, ssn:$ssn)"
}

fun main() {
    val brad = Person("Brad", "Kim")
    brad.firstName = "Bread" // mutator
    println(brad.fullName) // accessor
    println(brad.firstName) // accessor
    val yong = Person("Yong", "kim", 30)
    yong.ssn = "1234"
    println(yong)
    yong.firstName = "Yo"
    yong.age = 200
    println(yong)
}