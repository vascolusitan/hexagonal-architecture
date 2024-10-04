package hexagonalarchitecture.application.port.inbound

import hexagonalarchitecture.application.domain.model.Person

interface GetAllPersonsUseCase {

    fun get(): List<Person>

}
