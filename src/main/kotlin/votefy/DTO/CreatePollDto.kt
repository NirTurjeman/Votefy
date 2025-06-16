package votefy.dto
import votefy.model.VoteType
data class CreatePollDto(
    val title: String,
    val options: List<String> = emptyList(),
    val isActive: Boolean = true,
    val type: VoteType,
)
