package hexagonalarchitecture.application.port.outbound

import hexagonalarchitecture.application.domain.model.Person

interface GetAllPersonsPort {

    fun get(): List<Person>

}