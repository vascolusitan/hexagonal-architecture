package hexagonalarchitecture.application.port.inbound

import hexagonalarchitecture.application.domain.model.Person

interface CreatePersonUseCase {

    fun create(messageId: String, message: String, person: Person)

}
