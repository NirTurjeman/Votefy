package votefy.api

import votefy.model.Question

class AdminApi(private val baseUrl: String) {
    private val adminUrl = "$baseUrl/admin"

    suspend fun createVote(
        title: String,
        type: String,
        options: List<String> = emptyList(),
    ): Boolean {
        val payload = mapOf(
            "title" to title,
            "type" to type,
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
        HttpHelper.getJson("$baseUrl/votes/$questionId/voters") ?: emptyArray()

    suspend fun getAllDefinitions(): Array<Question> =
        HttpHelper.getJson("$baseUrl/votes/definitions")?: emptyArray()

    suspend fun hasUserVoted(userId: String, questionId: String): Boolean? =
        HttpHelper.getJson("$baseUrl/votes/user/$userId/$questionId")
}
