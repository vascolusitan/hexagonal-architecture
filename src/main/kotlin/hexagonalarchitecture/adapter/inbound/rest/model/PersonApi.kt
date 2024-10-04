package hexagonalarchitecture.adapter.inbound.rest.model

data class PersonApi (
    val id: Long,
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String
)