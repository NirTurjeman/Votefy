package votefy.services

import retrofit2.Response
import retrofit2.http.*
import votefy.dto.CreatePollDto
import votefy.model.Poll
import votefy.dto.AdminInfoDto
interface AdminService {

    /**
     * Logs in an admin user.
     *
     * @param body An object containing the admin login credentials (adminID and password).
     * @return HTTP response indicating whether the login was successful.
     */
    @POST("/votes/login")
    suspend fun adminLogin(@Body body: AdminInfoDto): Response<Boolean>

    /**
     * Creates a new poll definition.
     *
     * @param body An object containing the poll details (title, type, options, etc.).
     * @return HTTP response indicating whether the poll was successfully created.
     */
    @POST("/votes/definitions")
    suspend fun createVote(@Body body: CreatePollDto): Response<Unit>

    /**
     * Deletes a poll by its ID.
     *
     * @param voteId The ID of the poll to delete.
     * @return HTTP response indicating whether the poll was successfully deleted.
     */
    @DELETE("/admin/votes/{id}")
    suspend fun deleteVote(@Path("id") voteId: String): Response<Unit>

    /**
     * Activates a poll definition by its ID.
     *
     * @param id The ID of the poll to activate.
     * @return HTTP response indicating whether the poll was successfully activated.
     */
    @POST("/votes/definitions/{id}/activate")
    suspend fun activateVote(@Path("id") id: String): Response<Unit>

    /**
     * Deactivates a poll definition by its ID.
     *
     * @param id The ID of the poll to deactivate.
     * @return HTTP response indicating whether the poll was successfully deactivated.
     */
    @POST("/votes/definitions/{id}/inactivate")
    suspend fun deactivateVote(@Path("id") id: String): Response<Unit>

    /**
     * Checks whether a poll is currently active.
     *
     * @param id The ID of the poll.
     * @return HTTP response indicating whether the poll is active (true or false).
     */
    @GET("/votes/definitions/{id}/active")
    suspend fun isVoteActive(@Path("id") id: String): Response<Boolean>

    /**
     * Retrieves the list of voters for a specific poll.
     *
     * @param questionId The ID of the poll.
     * @return HTTP response containing an array of user IDs who voted in the poll.
     */
    @GET("/votes/{id}/voters")
    suspend fun getVoters(@Path("id") questionId: String): Response<Array<String>>

    /**
     * Retrieves all poll definitions.
     *
     * @return HTTP response containing a list of all defined polls.
     */
    @GET("/votes/definitions")
    suspend fun getAllDefinitions(): Response<List<Poll>>

    /**
     * Checks whether a user has voted in a specific poll.
     *
     * @param userId The ID of the user.
     * @param questionId The ID of the poll.
     * @return HTTP response indicating whether the user has already voted (true or false).
     */
    @GET("/votes/user/{userId}/{questionId}")
    suspend fun hasUserVoted(
        @Path("userId") userId: String,
        @Path("questionId") questionId: String
    ): Response<Boolean>

    /**
     * Retrieves the results of a poll (vote distribution per option).
     *
     * @param questionId The ID of the poll.
     * @return HTTP response containing a map where keys are poll options and values are vote counts.
     */
    @GET("/votes/question/{id}/calculate")
    suspend fun getResults(@Path("id") questionId: String): Response<Map<String, Int>>

}
