package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub

import avro.event.PersonCreatedEvent
import avro.header.Entity
import avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.annotation.Handler
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import java.util.UUID

@Handler(entity = Entity.PERSON, operation = Operation.CREATE)
class PersonCreatedHandler(
    private val createPersonUseCase: CreatePersonUseCase
) {

    //const val HANDLER_EVENT_CLASS = PersonCreatedEvent::class.java

    fun handle(messageId: UUID, event: PersonCreatedEvent) {
        println("pintou $event")
        //message.ack()
    }

}