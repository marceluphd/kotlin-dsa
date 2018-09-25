package extensions.arrays

/**
 * `BooleanMatrix` = `Array<BooleanArray>`
 */
typealias BooleanMatrix = Array<BooleanArray>

val BooleanMatrix.rows: Int get() = size
val BooleanMatrix.columns: Int get() = if (isEmpty()) 0 else this[0].size

val BooleanMatrix.lastRow: Int get() = lastIndex
val BooleanMatrix.lastColumn: Int get() = if (isEmpty()) -1 else this[0].lastIndex

val BooleanMatrix.rowRange: IntRange get() = 0..lastIndex
val BooleanMatrix.columnRange: IntRange get() = if (isEmpty()) IntRange.EMPTY else 0..this[0].lastIndex

/**
 * Creates an [m] x [n] [BooleanMatrix] where all values are `false`.
 * @param m The number of rows
 * @param n The number of columns
 */
fun booleanMatrixWithSize(m: Int, n: Int): BooleanMatrix = Array(m) { BooleanArray(n) { false } }

/**
 * Create an BooleanMatrix from the provided lists.
 * @param rows The rows that will be added to the BooleanMatrix
 * @throws IllegalArgumentException if [rows] is empty.
 * @throws IllegalArgumentException if any of the rows differ in length, or are empty.
 */
fun buildBooleanMatrix(vararg rows: List<Boolean>): BooleanMatrix {
    require(rows.isNotEmpty()) { "Cannot create BooleanMatrix with no elements." }
    require(rows.all { row -> row.size == rows[0].size && row.isNotEmpty() }) {
        "All rows must have the same length, and cannot be empty"
    }
    val m = rows.size
    val n = rows[0].size

    val matrix = booleanMatrixWithSize(m, n)
    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            matrix[i][j] = value
        }
    }

    return matrix
}

fun BooleanMatrix.toList(): List<List<Boolean>> = fold(mutableListOf()) { acc, intArr ->
    acc.apply {
        acc.add(intArr.toList())
    }
}

/**
 * Create a 2D array from a list of lists.
 */
fun List<List<Boolean>>.toMatrix(): BooleanMatrix = Array(size = size, init = { i -> this[i].toBooleanArray() })

fun BooleanMatrix.debugString(): String = with(StringBuilder()) {
    rowRange.forEach { r ->
        append(this@debugString[r].contentToString())
        if (r < this@debugString.lastIndex)
            append("\n")
    }
    toString()
}
