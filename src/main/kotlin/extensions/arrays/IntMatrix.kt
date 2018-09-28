package extensions.arrays

/**
 * `IntMatrix` = `Array<IntArray>`
 */
typealias IntMatrix = Array<IntArray>

/** Returns the number of rows in the matrix. */
val IntMatrix.rows: Int get() = size
/** Returns the number of columns in the matrix. */
val IntMatrix.columns: Int get() = if (isEmpty()) 0 else this[0].size

/** Returns the range of valid row indices for this matrix. */
val IntMatrix.rowIndices: IntRange get() = 0..lastIndex
/** Returns the range of valid column indices for this matrix. */
val IntMatrix.columnIndices: IntRange get() = if (isEmpty()) IntRange.EMPTY else 0..this[0].lastIndex

/** Returns the index of the last row in the matrix or -1 if the matrix is empty. */
val IntMatrix.lastRowIndex: Int get() = lastIndex
/** Returns the index of the last column in the matrix or -1 if the matrix is empty. */
val IntMatrix.lastColumnIndex: Int get() = if (isEmpty()) -1 else this[0].lastIndex

/**
 * Returns the transpose of the Matrix.
 */
fun IntMatrix.transpose(): IntMatrix {
    val transposed: IntMatrix = Array(columns) { IntArray(rows) }
    (0..lastRowIndex).forEach { i ->
        (0..lastColumnIndex).forEach { j ->
            transposed[j][i] = this[i][j]
        }
    }
    return transposed
}

/**
 * Performs Depth-First Search on the matrix.
 * @param visited A [BooleanMatrix] used for tracking the visited matrix elements.
 * @param visit The action to perform for each element.
 */
fun IntMatrix.dfs(
    visited: BooleanMatrix = Array(size) { BooleanArray(this[0].size) },
    visit: (Int, Int) -> Unit
) {
    rowIndices.forEach { r ->
        columnIndices.forEach { c ->
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

/**
 * Convert the [IntMatrix] to a 2D [List].
 */
fun IntMatrix.toList(): List<List<Int>> = fold(mutableListOf()) { acc, intArr ->
    acc.apply {
        acc.add(intArr.toList())
    }
}

/**
 * Create an [IntMatrix] from a 2D [List].
 */
fun List<List<Int>>.toMatrix(): IntMatrix = Array(size = size, init = { i -> this[i].toIntArray() })

/**
 * Returns a string representation of the contents of the IntMatrix as if it were a List<List<Int>>.
 */
fun IntMatrix.contentToString(): String = with(StringBuilder()) {
    rowIndices.forEach { r ->
        append(this@contentToString[r].contentToString())
        if (r < this@contentToString.lastIndex)
            append("\n")
    }
    toString()
}
