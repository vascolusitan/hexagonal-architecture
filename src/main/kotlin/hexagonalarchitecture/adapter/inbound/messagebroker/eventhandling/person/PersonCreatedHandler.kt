package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import avro.event.PersonCreatedEvent
import avro.header.Entity
import avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandler
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.Handler
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person.mapper.PersonEventMapper
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import java.util.UUID

@Handler(entity = Entity.PERSON, operation = Operation.CREATE)
class PersonCreatedHandler(
    private val createPersonUseCase: CreatePersonUseCase,
    private val personEventMapper: PersonEventMapper,
    override val eventClassType: Class<PersonCreatedEvent> = PersonCreatedEvent::class.java
): EventHandler<PersonCreatedEvent> {

    override fun handle(messageId: UUID, event: Any) {
        val personCreatedEvent = eventClassType.cast(event)!!
        val dto = personEventMapper.eventToDto(personCreatedEvent)
            .copy(messageId = messageId)
        createPersonUseCase.create(dto)
    }

}