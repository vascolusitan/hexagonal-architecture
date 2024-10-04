package hexagonalarchitecture.adapter.outbound.database.mapper

import hexagonalarchitecture.adapter.inbound.rest.model.PersonApi
import hexagonalarchitecture.adapter.outbound.database.model.PersonEntity
import hexagonalarchitecture.application.domain.model.Person
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonEntityMapper {

    fun dbEntityToDomain(personEntity: List<PersonEntity>): List<Person>

}