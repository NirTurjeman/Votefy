package votefy.dto
import java.util.Date
data class AdminInfoDto(
    val id: String,
    val adminID: String,
    val password: String,
    val createdAt: Date,
)