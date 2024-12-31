package hexagonalarchitecture.application.domain

import hexagonalarchitecture.crosscutting.exception.ValidationException
import java.util.UUID

data class Person(
    var personId: UUID,
    var name: String,
    var sex: Sex,
    var age: Int,
    var maturity: Maturity
) {

    fun validateMaturity(): Person {
        when {
            age > 66 && maturity != Maturity.SENIOR ->
                throw ValidationException("Invalid maturity for a senior: $maturity")
            age in 18 ..66 && maturity != Maturity.ADULT ->
                throw ValidationException("Invalid maturity for an adult: $maturity")
            age in 13..17 && maturity != Maturity.TEEN ->
                throw ValidationException("Invalid maturity for a teenager: $maturity")
            age < 13 && maturity != Maturity.CHILD ->
                throw ValidationException("Invalid maturity for a child: $maturity")
        }
        return this
    }

}
