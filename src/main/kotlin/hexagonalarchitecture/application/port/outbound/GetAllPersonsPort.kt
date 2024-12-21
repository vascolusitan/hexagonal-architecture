package hexagonalarchitecture.application.port.outbound

import hexagonalarchitecture.application.domain.Person

interface GetAllPersonsPort {

    fun get(): List<Person>

}