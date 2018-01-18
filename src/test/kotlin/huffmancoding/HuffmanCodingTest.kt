package huffmancoding

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class HuffmanCodingTest {
    @Test
    fun `letter to code mapping`() {
        assertThat(
            createEncoding(
                linkedMapOf(
                    Pair('a', 25), Pair('b', 21), Pair('c', 18), Pair('d', 14),
                    Pair('e', 9), Pair('f', 7), Pair('g', 6)
                )
            ).codeByChar,
            equalTo<Map<Char, String>>(linkedMapOf(
                Pair('a', "10"), Pair('b', "00"), Pair('c', "111"), Pair('d', "110"), Pair('e', "010"), Pair('f', "0111"), Pair('g', "0110")
            ))
        )
    }

    @Test
    fun `another encoding test`() {
        assertThat(
            createEncoding(
                linkedMapOf(
                    Pair('a', 5),
                    Pair('b', 7),
                    Pair('c', 10),
                    Pair('d', 15),
                    Pair('e', 20),
                    Pair('f', 45)
                )
            ).codeByChar,
            equalTo<Map<Char, String>>(linkedMapOf(
                Pair('a', "1010"),
                Pair('b', "1011"),
                Pair('c', "100"),
                Pair('d', "110"),
                Pair('e', "111"),
                Pair('f', "0")
            ))
        )
    }

    @Test
    fun `encoding and decoding a string`() {
        val string = "this is a sentence"
        val encoding = string.createEncoding()

        val encodedString = string.encode(encoding)
        // Equivalent Huffman Codes.
        /* assertThat(encodedString, equalTo("00110000101011100101011101001110101111011001111011000111")) */
        assertThat(encodedString, equalTo("01010110100001101000011010101100011101101011101110111111"))

        val decodedString = encodedString.decode(encoding)
        assertThat(decodedString, equalTo("this is a sentence"))
    }
}