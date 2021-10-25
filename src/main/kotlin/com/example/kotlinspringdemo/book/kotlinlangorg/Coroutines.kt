package com.example.kotlinspringdemo.book.kotlinlangorg

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking은 CoroutineScope이다.
// runBlocking을 실행하는 thread를 내부코드가 끝날 때까지 block한다.
fun main()= runBlocking {
    // launch는 coutine builder로서 독립적으로 실행될 코루틴을 생성한다.
    // CoroutineScope의 모든 코루틴이 종료해야 CoroutineScope도 종료된다.
    // launch로 worker1에게 넘김
    launch { doWorld() }
    doWorld2()
    // 독립적으로 실행된 그 외 코드
    println("Done") // (5) in main

    val job=launch {
        delay(1000L)
        println("coroutine job")
    }
    println("Starting job")
    job.join()
    println("Finishing job")
}

suspend fun doWorld() {
    println("doWorld...") // (2) in worker1
    delay(2000L)
    println("World!") // (6) in worker2
}

// CoroutineScope을 새로 설정하면서 내부코드가 모두 끝나야 바깥 runBlocking 코드가 실행된다.
suspend fun doWorld2() = coroutineScope {
    // launch로 worker2에게 넘김
    launch {
        println("doWorld2...") // (3) in worker2
        delay(1000L)
        println("World2!") // (4) in worker2; coroutineScope 마지막 코드이므로 빠져나감
    }
    println("Hello") // (1) in main
}

fun nonSuspendFun() {
    println("Non-suspend fun")
}