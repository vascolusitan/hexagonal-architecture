package hexagonalarchitecture.application.dto

import java.util.UUID

data class CreatePersonDto(
    val personId: String,
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String,
    val messageId: UUID?
)