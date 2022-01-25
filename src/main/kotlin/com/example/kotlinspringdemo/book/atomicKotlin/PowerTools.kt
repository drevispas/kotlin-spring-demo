package com.example.kotlinspringdemo.book.atomicKotlin

import com.example.kotlinspringdemo.book.atomicKotlin.functionalProgramming.out
import kotlin.reflect.KClass

fun main() {
//    extensionLambdas()
    generics()
}

fun generics() {
    data class Crate<T>(private var item:T){
        var list = emptyList<T>()
        init {
            list += item
        }
        fun get():List<T> = list
        fun put(item:T){list+=item}
    }
    data class Car(val name:String)
    val carCrate=Crate(Car("car1"));
    carCrate.put(Car("car2"))
    carCrate.put(Car("car3"))
    carCrate.get().out()

    fun <T,R> Crate<T>.map(block:(T)->R): List<R> {
        var newList = emptyList<R>()
        list.forEach { newList+=block(it) }
        return newList
    }
    carCrate.map{ it.name.toString().length }.out()

    // Reification
    className(String::class)?.out()
    b<Int>()?.out()

    // Variance
    open class Animal(val name: String)
    class Dog(name: String) : Animal(name)
    class InContainer<in T>(var item: @UnsafeVariance T) // argument로만 쓰이므로 item은 private이어야 함
    class OutContainer<out T>(val item: T) // return 값이므로 item은 public이어야 함

    val animalOutContainer: OutContainer<Animal> = OutContainer(Dog("dog1"))
    val animal: Animal = animalOutContainer.item // 실제 컨테이너는 Dog 객체를 가지고 있지만, 참조 컨테이너의 T가 Animal이므로 Animal 타입으로 반환하고 있음
    animal.name.out()
    val any: Any = animalOutContainer.item

    val dogInContainer: InContainer<Dog> = InContainer(Animal("animal1"))
    dogInContainer.item = Dog("dog3") // 실제 컨테이너는 Animal 객체를 가지고 있었는데, 참조 컨테이너의 T가 Dog이라 Dog으로 입력되고 있음
    dogInContainer.item.name.out()
}
fun <T:Any> className(kclass:KClass<T>) = kclass.qualifiedName
inline fun <reified T:Any> b()=className(T::class)

fun extensionLambdas() {
    class A {
        fun walk() = println("Walk!")
    }
    class B {
        fun run() = println("Run!")
    }
    fun f1(block: A.(B) -> Unit) {
        A().block(B()) // call a lambada with a receiver and an argument
    }
    f1 { walk(); it.run() } // action with a receiver and an argument
    fun f2(block: (A, B) -> Unit){
        block(A(), B()) // call a lambada with two arguments
    }
    f2 { a, b -> a.walk(); b.run() } // action with two arguments

    fun returnUnit(block: A.() -> Unit) = A().block()
    returnUnit { "hahaha" }.out()
    returnUnit { 1 }.out()
    returnUnit { }.out()
    fun returnNonUnit(block:A.()->String): String = A().block()
    returnNonUnit { "haha" }.out()
}
