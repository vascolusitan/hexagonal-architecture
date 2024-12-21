package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub

import java.util.UUID

interface EventHandler<T> {

    val eventClassType: Class<T>

    fun handle(messageId: UUID, event: Any)
    fun castToEvent(obj: Any): T = eventClassType.cast(obj)

}