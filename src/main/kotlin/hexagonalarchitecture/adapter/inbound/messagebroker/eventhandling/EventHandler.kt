package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling

import java.util.UUID

interface EventHandler<T> {

    val eventClassType: Class<T>

    fun handle(messageId: UUID, event: Any)

}