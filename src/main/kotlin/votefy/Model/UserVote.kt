package votefy.model

data class UserVote(
    val userID: String,
    val value: String,
    val pollID: String,
)
