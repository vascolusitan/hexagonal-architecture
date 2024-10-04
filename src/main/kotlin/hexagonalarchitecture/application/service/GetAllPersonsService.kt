package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.inbound.GetAllPersonsUseCase
import org.springframework.stereotype.Service

@Service
class GetAllPersonsService(): GetAllPersonsUseCase {
    override fun get(): List<Person> {
        TODO("Not yet implemented")
    }
}