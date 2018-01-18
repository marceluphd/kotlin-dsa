package algorithms.math

/**
 * Prime numbers
 *
 * More: https://github.com/rojiani/kotlin-99
 */


fun primesUpTo(n: Int): List<Int> {
    return sieveOfEratosthenes(n)
}

/**
 * [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
 */
private fun sieveOfEratosthenes(n: Int): List<Int> {
    /* Index is the number, boolean value is whether the number is possibly prime */
    val nums = BooleanArray(n + 1, { i -> i !in 0..1 })   // initially true for all x in 2..n

    var p = 2
    while (p != -1) {
        /* Mark composite numbers at 2p, 3p, 4p, ... */
        ((p * 2)..n step p).forEach { np ->
            nums[np] = false
        }

        /* Find the first number greater than p in the list that is not marked */
        p = nums.withIndex().indexOfFirst { (index, unmarked) ->
            index > p && unmarked
        }
    }

    return nums.withIndex()
        .filter { (index, isPrime) -> isPrime }
        .map { (index, _) -> index }
}