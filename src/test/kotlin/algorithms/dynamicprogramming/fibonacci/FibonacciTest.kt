package algorithms.dynamicprogramming.fibonacci

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class FibonacciTest {

    private val fibNumbers = mapOf(
        0 to 0L,
        1 to 1L,
        2 to 1L,
        3 to 2L,
        4 to 3L,
        5 to 5L,
        6 to 8L,
        7 to 13L,
        8 to 21L,
        9 to 34L,
        10 to 55L,
        11 to 89L,
        12 to 144L,
        13 to 233L,
        14 to 377L,
        15 to 610L,
        16 to 987L,
        17 to 1597L,
        18 to 2584L,
        19 to 4181L,
        20 to 6765L
    )


    @Test
    fun naiveFibo() {
        fibNumbers.keys.forEach { n ->
            assertEquals(fibNumbers[n]!!, NaiveFibonacci.fibo(n))
        }
        assertEquals(832040L, NaiveFibonacci.fibo(30))
    }

    @Ignore /* Takes about a minute */
    @Test
    fun naiveFiboLargeInput() {
        assertEquals(12586269025L, NaiveFibonacci.fibo(50))
    }

    @Test
    fun memoFibo() {
        fibNumbers.keys.forEach { n ->
            assertEquals(fibNumbers[n]!!, MemoizedFibonacci.fibo(n))
        }
        assertEquals(832040L, MemoizedFibonacci.fibo(30))
    }

    @Test
    fun memoFiboLargeInput() {
        assertEquals(12586269025L, MemoizedFibonacci.fibo(50))
        assertEquals(23416728348467685L, MemoizedFibonacci.fibo(80))
    }

    @Test
    fun tabFibo() {
        fibNumbers.keys.forEach { n ->
            assertEquals(fibNumbers[n]!!, TabulationFibonacci.fibo(n))
        }
        assertEquals(832040L, TabulationFibonacci.fibo(30))
    }

    @Test
    fun tabFiboLargeInput() {
        assertEquals(12586269025L, TabulationFibonacci.fibo(50))
        assertEquals(23416728348467685L, TabulationFibonacci.fibo(80))
    }

    @Test
    fun optimizedFibo() {
        fibNumbers.keys.forEach { n ->
            assertEquals(fibNumbers[n]!!, OptimizedFibonacci.fibo(n))
        }
        assertEquals(832040L, OptimizedFibonacci.fibo(30))
    }

    @Test
    fun optimizedFiboLargeInput() {
        assertEquals(12586269025L, OptimizedFibonacci.fibo(50))
        assertEquals(23416728348467685L, OptimizedFibonacci.fibo(80))
    }
}