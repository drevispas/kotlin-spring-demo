package com.example.kotlinspringdemo.book.programmingkotlinapplication

//open class Employee(val firstName: String, val lastName:String) {
//    var id:String=""
//
//    constructor(firstName:String, lastName:String, id:String) : this(firstName, lastName){
//        this.id=id
//    }
//
//    override fun toString(): String="(id=$id) $firstName $lastName"
//}

open class Employee {
    var firstName: String
    var lastName: String
    var id: String

    init {
        firstName = "I"
        lastName = "I"
        id = "I"
    }

    constructor() {
        firstName = "C"
        lastName = "C"
    }

    constructor(fName: String, lName: String) {
        firstName = fName
        lastName = lName
    }

    override fun toString() = "Employee[Id=$id: firstName=$firstName,lastName=$lastName"
}

class PermanentEmployee : Employee {
    var salary: Int = 0

    constructor(firstName: String, lastName: String) {
        this.id = id
    }

    constructor(firstName: String, lastName: String, id: String) : super(firstName, lastName) {
        this.id = id
    }

    constructor(firstName: String, lastName: String, id: String, salary: Int) : this(firstName, lastName, id) {
        this.salary = salary
    }

    override fun toString(): String = super.toString() + " (salary=$salary)"
}

fun main() {
    var emp1 = PermanentEmployee("brad", "kim")
    println(emp1)
    var emp2 = PermanentEmployee("brad", "kim", "1")
    println(emp2)
    var emp3 = PermanentEmployee("yong", "kim", "2", 100)
    println(emp3)
}