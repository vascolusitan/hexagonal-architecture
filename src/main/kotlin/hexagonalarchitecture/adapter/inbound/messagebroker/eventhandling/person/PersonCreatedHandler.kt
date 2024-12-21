package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import avro.event.PersonCreatedEvent
import avro.header.Entity
import avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandler
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.Handler
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import java.util.UUID

@Handler(entity = Entity.PERSON, operation = Operation.CREATE)
class PersonCreatedHandler(
    private val createPersonUseCase: CreatePersonUseCase,
    override val eventClassType: Class<PersonCreatedEvent> = PersonCreatedEvent::class.java
): EventHandler<PersonCreatedEvent> {

    override fun handle(messageId: UUID, event: Any) {
        val personCreatedEvent = eventClassType.cast(event)
        println("pintou $personCreatedEvent")
    }

}