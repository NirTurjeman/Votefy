package votefy.model

import java.util.Date

data class Poll(
    val id: String,
    val title: String,
    val options: List<String>?,
    val isActive: Boolean,
    val createdAt: Date,
    val type: VoteType,
)

