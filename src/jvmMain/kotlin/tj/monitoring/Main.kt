package tj.monitoring

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import com.google.gson.Gson

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

class Main() {
    private val client = OkHttpClient()
    private val baseUrl = "https://tj-be.k3s.bangun-kreatif.com"


    fun checkRunning(name : String):String{
        return "Hei $name, It's Working!";
    }

    fun booting(payload: BootingData): String {
        val gson = Gson()
        // Create a JSON request body from the payload
        val json = gson.toJson(payload)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        // Create an HTTP POST request
        val request = Request.Builder()
            .url("$baseUrl/log/booting") // Replace with your actual API endpoint
            .post(requestBody)
            .build()

        // Execute the request and handle the response
        try {
            val response: Response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return "Error: ${response.code}"
            }
            val responseBody = response.body?.string() ?: ""
            return "Booting Called with payload: $json\nResponse: $responseBody"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

    fun heartbeat(payload : HeartbeatData):String{
        val gson = Gson()
        // Create a JSON request body from the payload
        val json = gson.toJson(payload)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        // Create an HTTP POST request
        val request = Request.Builder()
            .url("$baseUrl/log/heartbeat") // Replace with your actual API endpoint
            .post(requestBody)
            .build()

        // Execute the request and handle the response
        try {
            val response: Response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return "Error: ${response.code}"
            }
            val responseBody = response.body?.string() ?: ""
            return "Heartbeat Called with payload: $json\nResponse: $responseBody"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

    fun transaction(payload : TransactionData):String{
        val gson = Gson()
        // Create a JSON request body from the payload
        val json = gson.toJson(payload)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        // Create an HTTP POST request
        val request = Request.Builder()
            .url("$baseUrl/log/transaction") // Replace with your actual API endpoint
            .post(requestBody)
            .build()

        // Execute the request and handle the response
        try {
            val response: Response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return "Error: ${response.code}"
            }
            val responseBody = response.body?.string() ?: ""
            return "Transaction Called with payload: $json\nResponse: $responseBody"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

}
