package votefy

import votefy.api.AdminApi
import votefy.api.VoterApi

class VotefyClient(baseUrl: String = "http://localhost:3000") {
    companion object {
        private const val BASE_URL = "http://10.0.2.2:3000" // או ה־URL שלך
    }
    val admin = AdminApi(baseUrl)
    val voter = VoterApi(baseUrl)
}
