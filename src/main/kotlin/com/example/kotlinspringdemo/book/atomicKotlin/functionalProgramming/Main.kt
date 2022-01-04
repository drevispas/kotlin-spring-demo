package com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming

import kotlin.random.Random

fun main() {
//    destructuring()
//    generics()
//    extensionFunctions()
//    lambdas()
//    collections()
//    memberReferences()
//    highOrderFunctions()
//    lists()
//    maps()
//    sequences()
//    localFunctions()
    folding()
}

fun folding() {
    list1to5.fold(0) { sum, i -> sum + i }.out()
    list1to5.foldRight(emptyList<Int>().toMutableList()) { i, acc -> acc.add(i); acc }.out()
    listAtoC.reduce{acc, s -> "$acc $s" }.out()
    listAtoC.reduceRight { s, acc -> "$acc $s" }.out()
    list1to5.runningFold(0){sum, i -> sum+i }.out()
    listAtoC.runningReduce{acc, s -> "$acc $s" }.out()
}

fun localFunctions() {
    var result = ""
    run {
        list1to5.forEach tag@{
            result += "$it"
            if (it == 3) {
                return@run
            }
        }
    }
    result.out()
}

fun sequences() {
    list1to5.filter(Int::isEven).map(Int::square).any(Int::lessThanTen).out()
    println(intAccessCount)
    intAccessCount = 0
    list1to5.asSequence().filter(Int::isEven).map(Int::square).any(Int::lessThanTen).out()
    println(intAccessCount)
    intAccessCount = 0

    // generateSequence()
    generateSequence(6) { n -> (n - 1).takeIf { it > 0 } }.toList().out()

}

fun maps() {
    val people = names.zip(ages) { n, a -> Person(n, a) }
    val ageMap = people.groupBy(Person::age)
    ageMap[21]?.out()
    val firstCharMap = people.groupBy { it.name.first() }
    firstCharMap['F']?.out()
    people.associateWith { it.name }.out()
    people.associateBy { it.name }.out()

    // get(), put()
    val mutableMap = mutableMapOf(1 to "one", 2 to "two")
    mutableMap.getOrPut(0) { "zero" }
    mutableMap.out()
    mutableMap.map { e -> Pair(e.value, e.key) }.toMap().out()
    mutableMap.map { (k, v) -> Pair(k, v) }.out()
}

fun lists() {
    data class Student(val id: Int, val name: String)
    list1to5.zip(names) { id, name -> Student(id, name) }.out()

    // zipWithNext()
    listAtoC.zipWithNext().out()
    listAtoC.zipWithNext { a, b -> "$a$b" }.out()

    // Flattening
    range1to3.map { i -> range1to3.map { j -> i to j } }.flatten().out()
    range1to3.flatMap { i -> range1to3.map { j -> i to j } }.out()

    val rand = Random(26)
    val deck = Suit.values().flatMap { s -> Rank.values().map { r -> Card(r, s) } }
    repeat(7) { deck.random(rand).out() }
}

fun highOrderFunctions() {
    fun rep(times: Int, f: (Int) -> Unit) {
        for (i in 0 until times) {
            f(i)
        }
    }
    rep(3) { n -> println("Hi $n") }

    fun <T> List<T>.ifAny(predicate: (T) -> Boolean): Boolean {
        for (e in this) {
            if (predicate(e)) return true;
        }
        return false
    }
    listAtoC.ifAny { it == "B" }.out()
}

fun Int.f1() = this * 2
fun memberReferences() {
    data class Message(val text: String, val isRead: Boolean)

    val messages = listOf(
        Message("text1", false),
        Message("cat", true),
        Message("text3", true),
        Message("text4", false),
    )
    messages.filterNot(Message::isRead).out()
    messages.sortedWith(compareBy(Message::isRead, Message::text)).out()

    fun Message.isImportant(): Boolean {
        return text.contains("cat")
    }
    messages.any(Message::isImportant).out()

    data class Text(val id: Int, val text: String) {
        constructor(id: Int, message: Message) : this(id, message.text)
    }

    val texts = messages.mapIndexed { index, message -> Text(index, message.text) }
    (messages.mapIndexed(::Text) == texts).out()

    // Reference to extension functions
    fun Int.times11() = times(11)
    fun String.frog() = "$this, Ribbit!"
    fun goTimes(i: Int, g: (Int) -> Int) = g(i)
    fun goFrog(s: String, f: (String) -> String) = f(s)
    goTimes(3, Int::times11).out()
    goFrog("Hello", String::frog).out()
}

fun Int.goTime11() = times(11)

fun destructuring() {
    val person = Person("ace", 99)
    val (name, age) = person
    name.out()
    age.out()

    for ((index, value) in listAtoC.withIndex()) {
        println("[$index] $value")
    }
}

fun generics() {
    val holder1 = GenericHolder("Hi")
    val holder2 = GenericHolder<String>("Hello")
    holder1.get().out()
    holder2.get().out()

    identity("Hi").out()
    identity<String>("Hello").out()

    listAtoC.secondOrNull()?.out()
}

fun extensionFunctions() {
    "123".range.out()
    "123".secondOrNull?.out()

    val list1: List<*> = listOf(1, 2, 3)
    val a1 = list1[0]
    a1?.out()
}

fun lambdas() {
    listAtoC.map { "[$it] " }.out()
    listAtoC.joinToString("/") { "[$it]" }.out()
    listAtoC.mapIndexed { index, c -> "[$index:$c]" }.out()
    run { -> println("HI") }
    "run()".run { println(this) }
    "apply()".apply { println(this) }
    "let()".let { println(it) }
    "also()".also { println(it) }

    list1to5.filter { it % 2 == 0 }.out()
    val greaterThanTwo = { n: Int -> n > 2 }
    list1to5.filter(greaterThanTwo).out()
}

fun collections() {
    val isPositive = { n: Int -> n > 0 }
    val list1 = listOf(-3, -1, 1, 3, 5)
    list1.filterNot(isPositive).out()
    val (pos, neg) = list1.partition(isPositive)
    neg.out()
    pos.out()

    val list2 = listOf('a', 'b', 'x', 'Y', 'Z')
    list2.takeLastWhile { it.isUpperCase() }.out()
    list2.takeWhile { it.isUpperCase() }.out()
}
