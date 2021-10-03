package com.example.kotlinspringdemo.playground.ktinaction

import java.io.BufferedReader
import java.io.FileReader

/* Higher-order function: function taking another function as an argument or return value */
data class SiteVisit(val path: String, val duration: Double, val os: OS)
enum class OS { LINUX, WINDOWS, MAC, IOS, ANDROID }

val log = listOf(
    SiteVisit("a.com", 1.0, OS.LINUX), SiteVisit("b.net", 2.0, OS.WINDOWS),
    SiteVisit("c.com", 1.0, OS.LINUX), SiteVisit("d.ac", 5.0, OS.IOS),
    SiteVisit("c.net", 1.0, OS.ANDROID), SiteVisit("a.com", 5.0, OS.WINDOWS),
)


fun main(args: Array<String>) {
    fun twoAndThree(operation: (Int, Int) -> Int) = operation(2, 3)
    twoAndThree { x, y -> x + y }.stdout()

    // extension function
    fun String.filter(predicate: (Char) -> Boolean): String =
        StringBuilder().also { sb -> forEach { if (predicate(it)) sb.append(it) } }.toString()
    "Hello, World!".filter { it in 'a'..'z' }.stdout()

    // default value
    fun <T> Collection<T>.joinToString(
        separator: String = ", ", prefix: String = "", postfix: String = "",
        transform: (T) -> String = { it.toString() }
    ): String =
        StringBuilder().also { sb ->
            sb.append(prefix)
            this.forEachIndexed { index, t ->
                if (index > 0) sb.append(separator)
                sb.append(transform(t))
            }
            sb.append(postfix)
        }.toString()
    listOf("a", 1.0, 3, 'b').joinToString().stdout()
    listOf("a", 1.0, 3, 'b').joinToString { it.toString().uppercase() }.stdout()

    // lambda as a return value
    class SiteFilters(var prefix: String = "", var withoutExtension: Boolean = true) {
        fun getPredicate(): (SiteVisit) -> Boolean =
            if (withoutExtension) {
                { s -> s.path.startsWith(prefix) }
            } else {
                { s -> s.path.split(".")[0].startsWith(prefix) || s.path.split(".")[1].startsWith(prefix) }
            }
    }

    val siteFilters = SiteFilters().apply { prefix = "a" }
    log.filter(siteFilters.getPredicate()).stdout()
    siteFilters.run { withoutExtension = false }
    log.filter(siteFilters.getPredicate()).stdout()

    // deduplication through extension
    fun List<SiteVisit>.averageForOs(os: OS) = filter { it.os == os }.map(SiteVisit::duration).average()
    log.filter { it.os == OS.WINDOWS }.map(SiteVisit::duration).average().stdout()
    log.averageForOs(OS.LINUX).stdout()
    log.averageForOs(OS.IOS).stdout()
    // deduplication through extension with lambda
    fun List<SiteVisit>.averageFor(predicate: (SiteVisit) -> Boolean) =
        filter(predicate).map(SiteVisit::duration).average()
    log.averageFor { it.os in setOf(OS.IOS, OS.ANDROID) }.stdout()
    // deduplication through lambda for resource management
    BufferedReader(FileReader("data/a.txt")).use { it.readLine() }.stdout() // use() closes resource automatically

    // return to a label
    fun lookForPersonName(name: String){ // the closes `fun` from `return`
        people.forEach {
            if(it.name==name) {
                it.stdout()
                return // `return` exits from the closest `fun` keyword
            }
        }
    }
    lookForPersonName("john")
    fun lookForPersonNameWithLabel(name: String) {
        people.forEach mylabel@{
            if (it.name != name) return@mylabel
            it.stdout()
        }
    }
    lookForPersonNameWithLabel("john")
    // anonymous function
    fun lookForPersonWithAnonymousFunction(name: String) {
        people.forEach(fun (p) { // the closes `fun` from `return`
            if(p.name==name){
                p.stdout()
                return // `return` exits from the closest `fun` keyword
            }
        })
    }
    lookForPersonWithAnonymousFunction("john")
}