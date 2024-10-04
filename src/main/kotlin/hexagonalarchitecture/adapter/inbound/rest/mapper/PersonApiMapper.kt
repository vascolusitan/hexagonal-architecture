package hexagonalarchitecture.adapter.inbound.rest.mapper

import hexagonalarchitecture.adapter.inbound.rest.model.PersonApi
import hexagonalarchitecture.application.domain.model.Person
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonApiMapper {

    fun domainToApi(persons: List<Person>): List<PersonApi>

}