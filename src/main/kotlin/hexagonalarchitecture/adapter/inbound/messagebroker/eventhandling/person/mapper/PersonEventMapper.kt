package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person.mapper

import avro.event.PersonCreatedEvent
import hexagonalarchitecture.application.dto.CreatePersonDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonEventMapper {

    fun eventToDto(event: PersonCreatedEvent): CreatePersonDto

}