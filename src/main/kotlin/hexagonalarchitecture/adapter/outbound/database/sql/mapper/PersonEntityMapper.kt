package hexagonalarchitecture.adapter.outbound.database.sql.mapper

import hexagonalarchitecture.adapter.outbound.database.sql.model.PersonEntity
import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.dto.CreatePersonDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface PersonEntityMapper {

    @Mappings(
        Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    )
    fun dtoToDbEntity(createPersonDto: CreatePersonDto): PersonEntity

    fun dbEntityToDomain(personsEntities: List<PersonEntity>): List<Person>

}