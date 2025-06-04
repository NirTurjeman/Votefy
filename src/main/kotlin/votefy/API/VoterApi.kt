package votefy.api
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import votefy.model.VoteDef
import votefy.model.VoteSubmission

class VoterApi(private val baseUrl: String) {
    suspend fun vote(submission: VoteSubmission): Boolean =
        HttpHelper.postJson("$baseUrl/votes", submission)

    suspend fun checkForOpenVote(userId: String): List<VoteDef> {
        val type: Type = object : TypeToken<List<VoteDef>>() {}.type
        return HttpHelper.getJson("$baseUrl/votes/$userId/openVotesForUser", type) ?: emptyList()
    }


}
