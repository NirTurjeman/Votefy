package votefy

import votefy.api.AdminApi
import votefy.services.AdminService
import votefy.api.VoterApi
import votefy.services.VoterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VotefyClient() {
    private val baseUrl = "https://cd2f-2a00-a041-e94b-1700-a8a5-1fac-a041-6e24.ngrok-free.app"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val admin = AdminApi(retrofit.create(AdminService::class.java))
    val voter = VoterApi(retrofit.create(VoterService::class.java))
}
