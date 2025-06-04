package votefy.api

import votefy.model.VoteSubmission

class VoterApi(private val baseUrl: String) {
    suspend fun vote(submission: VoteSubmission): Boolean =
        HttpHelper.postJson("$baseUrl/votes", submission)

suspend fun getResults(questionId: String): Map<String, Int>? =
    HttpHelper.getJson("$baseUrl/votes/question/$questionId/calculate")
}
