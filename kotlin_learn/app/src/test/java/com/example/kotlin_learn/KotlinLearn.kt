package com.example.kotlin_learn

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

class KotlinLearn {

    /// launch() and async() functions sample. Parallel coroutines usage.
    @Test
    fun addition_isCorrect() {
        runBlocking {
            val totalTime = measureTimeMillis {
                executeTwoCoroutinesInParallelUsingLaunch()
            }
            println(" - The whole example took $totalTime milliseconds")
        }
    }
}

suspend fun executeTwoCoroutinesInParallelUsingLaunch() {
    coroutineScope {

        /// launch() starts new coroutine without blocking current thread
        launch {
            doSomething()
        }
        launch {
            doSomethingElse()
        }

        /// async() method returns a value
        val value = async {
            doSomethingAndReturnValue()
        }

        println(" - ${value.await()} from doSomethingAndReturnValue")
        println(" - Waiting for coroutines to finish")
    }
}

suspend fun doSomething() {
    val delay = 10000L
    delay(delay)
    println(" - doSomething waited for $delay milliseconds")
}

suspend fun doSomethingElse() {
    val delay = Random.nextInt(100, 1000)
    delay(delay.milliseconds)
    println(" - doSomethingElse waited for $delay milliseconds")
}

suspend fun doSomethingAndReturnValue(): Int {
    val delay = 2000L
    delay(delay)
    println(" - doSomethingAndReturnValue waited for $delay milliseconds")
    return 1
}
