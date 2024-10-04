package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.inbound.GetAllPersonsUseCase
import hexagonalarchitecture.application.port.outbound.GetAllPersonsPort
import org.springframework.stereotype.Service

@Service
class GetAllPersonsService(
    private val getAllPersonsPort: GetAllPersonsPort
): GetAllPersonsUseCase {

    override fun get(): List<Person> {
        return getAllPersonsPort.get()
    }

}