package hexagonalarchitecture.adapter.outbound.database.mapper

import hexagonalarchitecture.adapter.outbound.database.model.PersonEntity
import hexagonalarchitecture.application.domain.model.Person
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface PersonEntityMapper {

    @Mappings(
        Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    )
    fun domainToDbEntity(person: Person): PersonEntity

    fun dbEntityToDomain(personsEntities: List<PersonEntity>): List<Person>

}