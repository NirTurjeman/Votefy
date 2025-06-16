package votefy.api

import votefy.dto.CreatePollDto
import votefy.model.Poll
import votefy.services.AdminService
import votefy.dto.AdminInfoDto
class AdminApi(private val service: AdminService) {
    suspend fun loginAdmin(adminInfo: AdminInfoDto): Boolean {
        return service.adminLogin(adminInfo).isSuccessful
    }
    suspend fun createVote(poll: CreatePollDto): Boolean {
        return service.createVote(poll).isSuccessful
    }

    suspend fun deleteVote(voteId: String): Boolean =
        service.deleteVote(voteId).isSuccessful

    suspend fun setVoteActive(id: String): Boolean =
        service.activateVote(id).isSuccessful

    suspend fun setVoteInactive(id: String): Boolean =
        service.deactivateVote(id).isSuccessful

    suspend fun isVoteActive(id: String): Boolean =
        service.isVoteActive(id).body() ?: false

    suspend fun showWhoVoted(questionId: String): Array<String> =
        service.getVoters(questionId).body() ?: emptyArray()

    suspend fun getAllDefinitions(): List<Poll> =
        service.getAllDefinitions().body() ?: emptyList()

    suspend fun hasUserVoted(userId: String, questionId: String): Boolean? =
        service.hasUserVoted(userId, questionId).body()

    suspend fun getResults(pollID: String): Map<String, Int>? =
        service.getResults(pollID).body()
}
