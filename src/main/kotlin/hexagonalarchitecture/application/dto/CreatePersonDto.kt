package hexagonalarchitecture.application.dto

data class CreatePersonDto(
    val personId: String,
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String,
    val messageId: String?
)