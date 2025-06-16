package votefy.api
import votefy.services.VoterService
import votefy.model.Poll
import votefy.model.UserVote

class VoterApi(private val service: VoterService) {

    suspend fun vote(submission: UserVote): Boolean =
        service.vote(submission).isSuccessful

    suspend fun checkForOpenVote(userID: String): List<Poll> =
        service.getOpenVotes(userID).body() ?: emptyList()
}
