package huffmancoding

import extensions.characterFrequencies
import java.util.*

/**
 * [Huffman Coding](https://en.wikipedia.org/wiki/Huffman_coding)
 * https://github.com/rojiani/kotlin-99/blob/master/src/org/kotlin99/logic/P50.kt
 */
const val INTERNAL_NODE_SYMBOL: Char = '*'

data class HuffmanTreeNode(
    val frequency: Int,
    val symbol: Char,
    val left: HuffmanTreeNode? = null,
    val right: HuffmanTreeNode? = null
) : Comparable<HuffmanTreeNode> {

    val isLeaf = left == null && right == null

    override fun compareTo(other: HuffmanTreeNode): Int {
        return this.frequency.compareTo(other.frequency)
    }

    override fun toString(): String {
        return "(freq: $frequency, symbol: $symbol, L: $left, R: $right)"
    }
}

data class HuffmanEncoding(val tree: HuffmanTreeNode, val codeByChar: Map<Char, String>)

fun String.encode(encoding: HuffmanEncoding): String =
    this.map { char ->
        encoding.codeByChar[char]
    }.joinToString("")

fun String.createEncoding(): HuffmanEncoding =
    createEncoding(characterFrequencies(caseSensitive = true))

fun String.decode(encoding: HuffmanEncoding): String {
    val codes = encoding.codeByChar.values
    val codeToSymbolMap = encoding.codeByChar.map { (symbol, code) ->
        code to symbol
    }.toMap()

    var remainingString = this

    return buildString {
        while (remainingString.isNotBlank()) {
            val code = codes.first { remainingString.startsWith(it) }
            val symbol = codeToSymbolMap[code]
            append(symbol)
            remainingString = remainingString.drop(code.length)
        }
    }
}

/**
 * Create the Huffman Tree
 */
fun createEncoding(frequencyByChar: Map<Char, Int>): HuffmanEncoding {
    val minHeap: PriorityQueue<HuffmanTreeNode> = initializeMinHeap(frequencyByChar)
    val huffmanTree = buildHuffmanTree(minHeap)

    val codes = generateCodes(huffmanTree)
    return HuffmanEncoding(huffmanTree, codes)
}

/**
 * Create Min-Heap of HuffmanTreeNodes (with all leaf nodes)
 */
private fun initializeMinHeap(frequencies: Map<Char, Int>): PriorityQueue<HuffmanTreeNode> {
    val leafNodes = createLeafNodesFromFrequencies(frequencies)
    return PriorityQueue(leafNodes)
}

private fun createLeafNodesFromFrequencies(frequencies: Map<Char, Int>): List<HuffmanTreeNode> =
    frequencies.entries.fold(emptyList()) { acc, (symbol, freq) ->
        acc + HuffmanTreeNode(freq, symbol)
    }

private fun buildHuffmanTree(minHeap: PriorityQueue<HuffmanTreeNode>): HuffmanTreeNode {
    while (minHeap.size > 1) {
        val subtreeRoot = createSubtree(removeTwoLeastFrequent(minHeap))
        minHeap.add(subtreeRoot)
    }
    return minHeap.peek()
}

private fun createSubtree(twoLeastFrequent: Pair<HuffmanTreeNode, HuffmanTreeNode>): HuffmanTreeNode =
    HuffmanTreeNode(
        twoLeastFrequent.toList().sumBy { it.frequency },
        INTERNAL_NODE_SYMBOL,
        twoLeastFrequent.first,
        twoLeastFrequent.second
    )

private fun removeTwoLeastFrequent(minHeap: PriorityQueue<HuffmanTreeNode>): Pair<HuffmanTreeNode, HuffmanTreeNode> =
    (minHeap.poll() to minHeap.poll())

/**
 * Traverse tree to build a map of each symbol's code.
 */
private fun generateCodes(huffmanTree: HuffmanTreeNode): Map<Char, String> =
    generateCodes(huffmanTree, "", mutableMapOf())

private fun generateCodes(
    node: HuffmanTreeNode?,
    pathToNode: String,
    codes: MutableMap<Char, String>
): Map<Char, String> {
    node?.let {
        if (node.isLeaf)
            codes[node.symbol] = pathToNode

        node.left?.let { generateCodes(node.left, pathToNode + "0", codes) }
        node.right?.let { generateCodes(node.right, pathToNode + "1", codes) }
    }

    return codes
}