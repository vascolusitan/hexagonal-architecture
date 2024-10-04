package hexagonalarchitecture.adapter.inbound.rest.mappers

import hexagonalarchitecture.adapter.inbound.rest.PersonApi
import hexagonalarchitecture.application.domain.model.Person
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonApiMapper {

    fun domainToApi(person: Person): PersonApi
    fun domainToApi(persons: List<Person>): List<PersonApi>

}