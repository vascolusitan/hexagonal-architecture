package hexagonalarchitecture.application.port.inbound

import hexagonalarchitecture.application.domain.Person

interface GetAllPersonsUseCase {

    fun get(): List<Person>

}
