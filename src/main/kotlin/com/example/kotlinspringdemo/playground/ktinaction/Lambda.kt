package com.example.kotlinspringdemo.playground.ktinaction

fun Any.stdout(): Any = println(this)

data class Person(val name: String, var age: Int){
    constructor(name: String):this(name,0)
}

val people = listOf(
    Person("ace", 10), Person("sun", 50),
    Person("army", 35), Person("john", 50), Person("john", 10)
)

class Book(val title: String, val authors: List<String>)

val books = listOf(
    Book("b1", listOf("a1", "a2")), Book("b2", listOf("a3")),
    Book("b3", listOf("a2", "a3")), Book("b4", listOf("a1")), Book("b5", listOf("a4"))
)

fun main(args: Array<String>) {
    "".run { }
    run { }

    /* collection functions */
    println(people.maxByOrNull { it.age })
    println(people.maxByOrNull(Person::age))
    println(people.map { it.name })
    println(people.map(Person::name))
    people.filter { it.age == people.maxByOrNull(Person::age)?.age }.stdout() // ineffective
    people.maxByOrNull { it.age }.run { people.filter { it.age == this?.age } }.stdout() // effective
    (!people.all { it.age > 19 }).stdout() // don't use !
    people.any { it.age <= 19 }.stdout() // use instead
    people.find { it.age == 10 }?.stdout()
    people.groupBy(Person::age).stdout()
    people.groupBy { it.name.first() }.stdout()

    /* flatMap */
    books.flatMap { it.authors }.toSet().stdout() // flatMap = map & flatten

    /* sequence */
    people.asSequence().map(Person::age).find { it == 35 }?.stdout() // Lazy evaluation: access at find()
    generateSequence(0) { it + 1 }.takeWhile { it <= 100 }.sum().stdout() // access at sum()

    /* lambdas with receivers: with */
    fun alphabet() = with(StringBuilder()) {
        ('A'..'Z').forEach { append(it) }
        toString()
    }
    alphabet().stdout()

    /* lambdas with receivers: apply */
    fun alphabet2() = StringBuilder().apply { ('A'..'Z').forEach { append(it) } }.toString()
    alphabet2().stdout()
    fun alphabet3()= buildString { ('A'..'Z').forEach { append(it) } }
    alphabet3().stdout()
    // builder
    Person("A").apply {
        age = 15
    }.stdout()
}
