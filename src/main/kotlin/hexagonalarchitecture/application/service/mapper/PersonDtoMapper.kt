package hexagonalarchitecture.application.service.mapper

import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.dto.CreatePersonDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonDtoMapper {

    fun dtoToDomain(createPersonDto: CreatePersonDto): Person

}