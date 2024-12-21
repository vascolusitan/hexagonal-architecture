package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub

import avro.event.PersonCreatedEvent
import avro.header.Entity
import avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.annotation.Handler
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import java.util.UUID

@Handler(entity = Entity.PERSON, operation = Operation.CREATE)
class PersonCreatedHandler(
    private val createPersonUseCase: CreatePersonUseCase,
    override val eventClassType: Class<PersonCreatedEvent> = PersonCreatedEvent::class.java
): EventHandler<PersonCreatedEvent> {

    override fun handle(messageId: UUID, event: Any) {
        val personCreatedEvent = castToEvent(event)
        println("pintou $event")
        //message.ack()
    }

//    override fun getEventClass(): Any {
//        return eventClass
//    }
//
//    override fun castToEvent(obj: Any): PersonCreatedEvent {
//        return eventClass.cast(obj)
//    }

//    override fun getEventType(): KClass<T> {
//        return PersonCreatedEvent::class
//    }

}