package com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming

data class Person(val name:String, var age:Int){
    override fun toString(): String = "($name is $age)"
}
val names=listOf("Alice","Bob","Charlie","David","Finley","Francis")
val ages = listOf(21,15,35,21,83,15,52,60)

val listAtoC = listOf("A","B","C")
val list1to5 = listOf(1,2,3,4,5)
val range1to3 = 1..3

data class Book(val title:String, val authors:List<String>)

enum class Suit { Spade, Club, Heart, Diamond }
enum class Rank(val face: Int) {
    Ace(1),Two(2),Three(3),Four(4),Five(5),Six(6),Seven(7),
    Eight(8),Nine(9),Ten(10),Jack(11),Queen(12),King(13)
}
class Card(val rank:Rank,val suit: Suit){
    override fun toString(): String = "$rank of ${suit}s"
}
