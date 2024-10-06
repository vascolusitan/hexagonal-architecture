package hexagonalarchitecture.application.port.inbound

import hexagonalarchitecture.application.domain.model.Person

interface CreatePersonUseCase {

    fun create(messageId: String, messageHash: Int, person: Person)

}
