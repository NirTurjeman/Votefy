package votefy.services

import retrofit2.Response
import retrofit2.http.*
import votefy.model.Poll
import votefy.model.UserVote

interface VoterService {

    /**
     * Submits a vote for a user.
     *
     * @param submission An object containing the vote details (userID, pollID, value).
     * @return HTTP response (Response<Unit>) indicating whether the vote was successfully submitted.
     */
    @POST("/votes")
    suspend fun vote(@Body submission: UserVote): Response<Unit>

    /**
     * Retrieves a list of open polls that the specified user has not voted in yet.
     *
     * @param userId The ID of the user.
     * @return HTTP response (Response<List<Poll>>) containing the list of open polls available for the user.
     */
    @GET("/votes/{userId}/openVotesForUser")
    suspend fun getOpenVotes(@Path("userId") userId: String): Response<List<Poll>>

}
