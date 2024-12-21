package hexagonalarchitecture.application.dto

data class CreatePersonDto(
    val id: Long,
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String,
    val messageId: String,
    val messageHash: Int,
)