package votefy.model

import java.util.Date

data class Question(
    val id: String,
    val title: String,
    val options: List<String>?,
    val eligibleVoters: List<String>?,
    val cratedAt: Date,
    val isActive: Boolean,
)
