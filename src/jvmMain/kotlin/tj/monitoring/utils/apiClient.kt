package tj.monitoring.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.util.Optional
import kotlin.concurrent.thread


data class response(
    var code: Int,
    var message: String
)
class apiClient {

    val client = OkHttpClient()
    val gson = Gson()
    val baseUrl = "https://tj-be.k3s.bangun-kreatif.com"

    fun responseCode(code: Int, message: String = "") : response {
        var msg = ""
        when (code) {
            200 -> msg = "success"
            5000 -> msg = "Bad Request Payload"
            5001 -> msg = "API Log Failed"
            else -> msg = message
        }
        return response(code, msg)
    }
    fun post(payload: Any, URL: String, condition: Int, timeSleep: Long = 2000) : String {
        val json = gson.toJson(payload)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        val req = Request.Builder()
            .url("$baseUrl" + "$URL")
            .post(requestBody)
            .build()

        var res = "";
        var i = 0
        while (condition >= i) {
            try {
                val response: Response = client.newCall(req).execute()
                if (response.isSuccessful) {
                    res = gson.toJson(responseCode(200))
                    break
                }
                if (i == condition) {
                    res = gson.toJson(responseCode(500, response.message))
                    break
                }
                println("Retrying $i for $condition $URL")
                i++
            } catch (e: Exception) {
                res = gson.toJson(responseCode(500, e.message.toString()))
                break
            }
            Thread.sleep(timeSleep)
        }
        return res
    }
}