package votefy.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type

object HttpHelper {
    @PublishedApi internal val client = OkHttpClient()
    @PublishedApi internal val gson = Gson()
    private val JSON = "application/json; charset=utf-8".toMediaType()

    fun <T> getJson(url: String, type: Type): T? {
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            return gson.fromJson(response.body?.string(), type)
        }
    }
    inline fun <reified T> getJsonTyped(url: String): T? {
        val type = object : TypeToken<T>() {}.type
        return getJson(url, type)
    }
    fun <T> postJson(url: String, body: Any, clazz: Class<T>): T {
        val json = gson.toJson(body)
        val requestBody = json.toRequestBody(JSON)
        val request = Request.Builder().url(url).post(requestBody).build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string()

            if (!response.isSuccessful) {
                val errorMsg = try {
                    val errorMap = gson.fromJson(responseBody, Map::class.java)
                    errorMap["message"]?.toString() ?: "Unknown error"
                } catch (e: Exception) {
                    responseBody ?: "Unknown error"
                }
                throw Exception("Request failed: ${response.code} – $errorMsg")
            }

            return gson.fromJson(responseBody, clazz)
        }
    }

    inline fun <reified T> postJson(url: String, body: Any): T {
        return postJson(url, body, T::class.java)
    }

    fun delete(url: String): Boolean {
        val request = Request.Builder().url(url).delete().build()
        client.newCall(request).execute().use { response ->
            return response.isSuccessful
        }
    }

    fun getBoolean(url: String): Boolean? {
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            return gson.fromJson(response.body?.string(), Boolean::class.java)
        }
    }
}
