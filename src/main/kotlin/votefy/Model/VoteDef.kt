package votefy.model

import java.util.Date

data class VoteDef(
    val id: String,
    val title: String,
    val options: List<String>?,
    val createdAt: Date,
    val isActive: Boolean,
)

