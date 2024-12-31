package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person.mapper

import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
import hexagonalarchitecture.application.dto.CreatePersonDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PersonEventMapper {

    @Mapping(target = "messageId", ignore = true)
    fun eventToDto(event: PersonCreatedEvent): CreatePersonDto

}