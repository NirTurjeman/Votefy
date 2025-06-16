package votefy

import votefy.api.AdminApi
import votefy.services.AdminService
import votefy.api.VoterApi
import votefy.services.VoterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VotefyClient() {
    private val baseUrl = "https://f543-79-177-145-225.ngrok-free.app/votes/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val admin = AdminApi(retrofit.create(AdminService::class.java))
    val voter = VoterApi(retrofit.create(VoterService::class.java))
}
