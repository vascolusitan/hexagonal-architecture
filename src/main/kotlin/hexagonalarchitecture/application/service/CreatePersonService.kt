package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import org.springframework.stereotype.Service

@Service
class CreatePersonService(
    private val createPersonPort: CreatePersonPort
): CreatePersonUseCase {

    override fun create(messageId: String, message: String, person: Person) {
        createPersonPort.create(person)
    }

}