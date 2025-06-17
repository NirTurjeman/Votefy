package votefy

import votefy.api.AdminApi
import votefy.services.AdminService
import votefy.api.VoterApi
import votefy.services.VoterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VotefyClient(baseUrl: String = "") {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl.ensureEndsWithSlash())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val admin = AdminApi(retrofit.create(AdminService::class.java))
    val voter = VoterApi(retrofit.create(VoterService::class.java))
}
fun String.ensureEndsWithSlash(): String =
    if (endsWith("/")) this else "$this/"
