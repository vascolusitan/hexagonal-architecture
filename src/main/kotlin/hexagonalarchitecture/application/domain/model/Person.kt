package hexagonalarchitecture.application.domain.model

data class Person(
    var id: Long,
    var name: String,
    var sex: Sex,
    var age: Int,
    var maturity: Maturity
)
