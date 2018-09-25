package extensions.arrays

/**
 * `IntMatrix` = `Array<IntArray>`
 */
typealias IntMatrix = Array<IntArray>

val IntMatrix.rows: Int get() = size
val IntMatrix.columns: Int get() = if (isEmpty()) 0 else this[0].size

val IntMatrix.lastRow: Int get() = lastIndex
val IntMatrix.lastColumn: Int get() = if (isEmpty()) -1 else this[0].lastIndex

val IntMatrix.rowRange: IntRange get() = 0..lastIndex
val IntMatrix.columnRange: IntRange get() = if (isEmpty()) IntRange.EMPTY else 0..this[0].lastIndex

fun IntMatrix.transpose(): IntMatrix {
    val transposed: IntMatrix = Array(columns) { IntArray(rows) }
    (0..lastRow).forEach { i ->
        (0..lastColumn).forEach { j ->
            transposed[j][i] = this[i][j]
        }
    }
    return transposed
}

fun IntMatrix.dfs(
    visited: Array<BooleanArray> = Array(size) { BooleanArray(this[0].size) },
    visit: (Int, Int) -> Unit
) {
    rowRange.forEach { r ->
        columnRange.forEach { c ->
            visited[r][c] = true
            visit(r, c)
        }
    }
}

/**
 * Creates an [m] x [n] [IntMatrix] where all values are 0.
 * @param m The number of rows
 * @param n The number of columns
 */
fun intMatrixWithSize(m: Int, n: Int): IntMatrix = Array(m) { IntArray(n) { 0 } }

/**
 * Create an [IntMatrix] from the provided lists.
 * @param rows The rows that will be added to the IntMatrix
 * @throws IllegalArgumentException if [rows] is empty.
 * @throws IllegalArgumentException if any of the rows differ in length or are empty.
 */
fun buildIntMatrix(vararg rows: List<Int>): IntMatrix {
    require(rows.isNotEmpty()) { "Cannot create IntMatrix with no elements." }
    require(rows.all { row -> row.size == rows[0].size && row.isNotEmpty() }) {
        "All rows must have the same length, and cannot be empty"
    }

    val m = rows.size
    val n = rows[0].size

    val matrix = intMatrixWithSize(m, n)
    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            matrix[i][j] = value
        }
    }

    return matrix
}

fun IntMatrix.toList(): List<List<Int>> = fold(mutableListOf()) { acc, intArr ->
    acc.apply {
        acc.add(intArr.toList())
    }
}

/**
 * Create a 2D array from a list of lists.
 */
fun List<List<Int>>.toMatrix(): IntMatrix = Array(size = size, init = { i -> this[i].toIntArray() })

fun IntMatrix.debugString(): String = with(StringBuilder()) {
    rowRange.forEach { r ->
        append(this@debugString[r].contentToString())
        if (r < this@debugString.lastIndex)
            append("\n")
    }
    toString()
}