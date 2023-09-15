package tj.monitoring

import kotlin.test.Test
import kotlin.test.assertContains

class MainTest {

    @Test
    fun testCheck(){
        val check = Main("Finna Hanaina")
        assertContains(check.checkRunning(), "Finna Hanaina")
    }

    @Test
    fun testBooting(){
        val check = Main("")
        assertContains(check.booting(), "Booting Called")
    }

    @Test
    fun testHeartbeat(){
        val check = Main("")
        assertContains(check.heartbeat(), "Heartbeat Called")
    }

    @Test
    fun testTransaction(){
        val check = Main("")
        assertContains(check.transaction(), "Transaction Called")
    }

}
