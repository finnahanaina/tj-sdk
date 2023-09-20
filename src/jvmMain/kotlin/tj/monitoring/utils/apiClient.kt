package tj.monitoring.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

data class response(
    var code: Int,
    var message: String
)
class apiClient {

    val client = OkHttpClient()
    val gson = Gson()
    val baseUrl = "https://tj-be.k3s.bangun-kreatif.com"

    fun responseCode(code: Int) : response {
        var msg = ""
        when (code) {
            200 -> msg = "success"
            5000 -> msg = "Bad Request Payload"
            5001 -> msg = "API Log Failed"
            else -> msg = "failed"
        }
        return response(code,"msg")
    }
    fun post(payload: Any, URL: String) : response{
        val json = gson.toJson(payload)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        val req = Request.Builder()
            .url("$baseUrl"+"$URL")
            .post(requestBody)
            .build()

        try {
            val response: Response = client.newCall(req).execute()
            if (!response.isSuccessful) {
                return responseCode(5001)
            }
            return responseCode(200)
        } catch (e: Exception) {
            return responseCode(5000)
        }
    }
}