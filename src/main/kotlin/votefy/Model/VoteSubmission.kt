package votefy.model

data class VoteSubmission(
    val userId: String,
    val value: String,
    val targetId: String,
)
