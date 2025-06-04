package votefy.api

import votefy.model.VoteDef

class AdminApi(private val baseUrl: String) {
    private val adminUrl = "$baseUrl/admin"

    suspend fun createVote(
        title: String,
        options: List<String> = emptyList(),
        isActive: Boolean = true,
    ): Boolean {
        val payload = mapOf(
            "title" to title,
            "options" to options,
            "isActive" to true
        )
        return HttpHelper.postJson("$baseUrl/votes/definitions", payload)
    }

    suspend fun deleteVote(voteId: String): Boolean =
        HttpHelper.delete("$adminUrl/votes/$voteId")

    suspend fun setVoteActive(id: String): Boolean =
        HttpHelper.postJson("$adminUrl/votes/$id/activate", Unit)

    suspend fun setVoteInactive(id: String): Boolean =
        HttpHelper.postJson("$adminUrl/votes/$id/deactivate", Unit)

    suspend fun isVoteActive(id: String): Boolean =
        HttpHelper.getBoolean("$adminUrl/votes/$id/is-active") ?: false

    suspend fun showWhoVoted(questionId: String): Array<String> =
        HttpHelper.getJsonTyped<Array<String>>("$baseUrl/votes/$questionId/voters") ?: emptyArray()

    suspend fun getAllDefinitions(): List<VoteDef> =
        HttpHelper.getJsonTyped<List<VoteDef>>("$baseUrl/votes/definitions") ?: emptyList()

    suspend fun hasUserVoted(userId: String, questionId: String): Boolean? =
        HttpHelper.getJsonTyped<Boolean>("$baseUrl/votes/user/$userId/$questionId")

    suspend fun getResults(questionId: String): Map<String, Int>? =
        HttpHelper.getJsonTyped<Map<String, Int>>("$baseUrl/votes/question/$questionId/calculate")
}
