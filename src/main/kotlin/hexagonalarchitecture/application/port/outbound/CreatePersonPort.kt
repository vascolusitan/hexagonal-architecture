package hexagonalarchitecture.application.port.outbound

import hexagonalarchitecture.application.domain.model.Person

interface CreatePersonPort {

    fun create(person: Person)

    fun audit(message: String)

}