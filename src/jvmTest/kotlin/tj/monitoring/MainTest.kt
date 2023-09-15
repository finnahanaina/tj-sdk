package tj.monitoring

import kotlin.test.Test
import kotlin.test.assertContains

class MainTest {

    @Test
    fun testCheck(){
        val check = Main("Finna Hanaina")
        assertContains(check.checkRunning(), "Finna Hanaina")
    }
}
