package tj.monitoring

import kotlin.test.Test
import kotlin.test.assertContains
data class SamData(
    val name: String,
    val status: String,
    val mid: String,
    val tid: String
)

data class ProvisionJliData(
    val name: String,
    val status: String,
    val mid: String,
    val tid: String
)

data class TariffData(
    val type: String,
    val price: Int,
    val time: String,
    val day_id: Int
)

data class BootingData(
    val sent_time: Long,
    val device_type: String,
    val reader_id: String,
    val terminal_code: String,
    val shelter_name: String,
    val terminal_name: String,
    val submitted_transaction_count: Int,
    val pending_transaction_count: Int,
    val gps_status: String,
    val gps_latitude: Double,
    val gps_longitude: Double,
    val qr_status: String,
    val voltage: Double,
    val temperature: Double,
    val software_version: String,
    val hardware_version: String,
    val sim_card_number: String,
    val mac_address: String,
    val cpu_usage: Int,
    val ram_usage: Int,
    val disk_usage: Int,
    val sam: List<SamData>,
    val provision_jli: List<ProvisionJliData>,
    val tariff: List<TariffData>
)

data class HeartbeatData(
    val sent_time: Long,
    val device_type: String,
    val reader_id: String,
    val terminal_code: String,
    val submitted_transaction_count: Int,
    val pending_transaction_count: Int,
    val gps_status: String,
    val gps_latitude: Double,
    val gps_longitude: Double,
    val qr_status: String,
    val voltage: Double,
    val temperature: Double,
    val cpu_usage: Int,
    val ram_usage: Int,
    val disk_usage: Int,
    val sam: List<SamData>,
    val provision_jli: List<SamData>
)

data class TransactionData(
    val sent_time: Long,
    val device_type: String,
    val reader_id: String,
    val terminal_code: String,
    val status: String,
    val transaction_type: String,
    val tap_type: String,
    val card_number: String,
    val card_type: String,
    val qr_source: String,
    val message: String,
    val gps_latitude: Double,
    val gps_longitude: Double
)

class MainTest {

    @Test
    fun testCheck(){
        val check = Main()
        assertContains(check.checkRunning("Finna Hanaina"), "Finna Hanaina")
    }

    @Test
    fun testBooting(){
        val bootingData = BootingData(
            sent_time = 1693201867,
            device_type = "gate",
            reader_id = "32232232",
            terminal_code = "64412",
            shelter_name = "Blok M",
            terminal_name = "012312",
            submitted_transaction_count = 32,
            pending_transaction_count = 2,
            gps_status = "active",
            gps_latitude = -6.0,
            gps_longitude = 107.0,
            qr_status = "active",
            voltage = 3.7,
            temperature = 30.3,
            software_version = "1.7.7",
            hardware_version = "3.2.2",
            sim_card_number = "0811111111",
            mac_address = "00:00:5e:00:53:af",
            cpu_usage = 90,
            ram_usage = 50,
            disk_usage = 20,
            sam = listOf(
                SamData("Bank Mandiri", "active", "", ""),
                SamData("Bank BCA", "active", "", "")
            ),
            provision_jli = listOf(
                ProvisionJliData("Bank Mandiri", "active", "", ""),
                ProvisionJliData("Bank BCA", "active", "", "")
            ),
            tariff = listOf(
                TariffData("flat", 3500, "", -1),
                TariffData("custom", 1, "05:00-17:00", 5)
            )
        )
        val check = Main()
        assertContains(check.booting(bootingData), "Booting Called")
    }

    @Test
    fun testHeartbeat(){
        val heartbeatData = HeartbeatData(
            sent_time = 1693201867,
            device_type = "gate",
            reader_id = "32232232",
            terminal_code = "64412",
            submitted_transaction_count = 32,
            pending_transaction_count = 2,
            gps_status = "active",
            gps_latitude = -6.0,
            gps_longitude = 107.0,
            qr_status = "active",
            voltage = 3.7,
            temperature = 30.3,
            cpu_usage = 90,
            ram_usage = 50,
            disk_usage = 20,
            sam = listOf(
                SamData("Bank Mandiri", "active", "", ""),
                SamData("Bank BCA", "active", "", "")
            ),
            provision_jli = listOf(
                SamData("Bank Mandiri", "active", "", ""),
                SamData("Bank BCA", "active", "", "")
            )
        )
        val check = Main()
        assertContains(check.heartbeat(heartbeatData), "Heartbeat Called")
    }

    @Test
    fun testTransaction(){
        val transactionData = TransactionData(
            sent_time = 1693201867,
            device_type = "gate",
            reader_id = "32232232",
            terminal_code = "64412",
            status = "success",
            transaction_type = "tap",
            tap_type = "in",
            card_number = "4312423412342345",
            card_type = "emoney",
            qr_source = "",
            message = "",
            gps_latitude = -6.0,
            gps_longitude = 107.0
        )
        val check = Main()
        assertContains(check.transaction(transactionData), "Transaction Called")
    }

}
