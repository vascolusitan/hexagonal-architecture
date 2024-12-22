package hexagonalarchitecture.application.dto

import java.util.UUID

data class CreatePersonDto(
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String,
    val messageId: UUID?
)