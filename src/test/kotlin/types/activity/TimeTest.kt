package misc.activity

import org.junit.Assert.*
import org.junit.Test

class TimeTest {

    @Test
    fun secondaryConstructor() {
        assertEquals(Time(10, 45), Time("10:45"))
        assertEquals(Time(7, 9), Time("07:09"))
        assertEquals(Time(7, 9), Time("7:9"))
    }

    @Test
    fun testToString() {
        assertEquals("10:45", Time(10, 45).toString())
        assertEquals("07:45", Time(7, 45).toString())
        assertEquals("07:08", Time(7, 8).toString())
    }

    @Test
    fun compareTo() {
        assertTrue(Time(10, 45) < Time(11, 45))
        assertTrue(Time(10, 45) == Time(10, 45))
        assertTrue(Time(10, 45) > Time(10, 44))
        assertTrue(Time(3, 45) < Time(21, 44))
    }

    @Test
    fun component1_2() {
        val (h, m) = Time(10, 45)
        assertEquals(10 to 45, h to m)
    }

}