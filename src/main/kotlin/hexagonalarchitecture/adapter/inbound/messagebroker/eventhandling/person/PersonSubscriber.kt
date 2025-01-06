package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ENTITY_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ID_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_OPERATION_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Entity
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandlerRegistry
import hexagonalarchitecture.crosscutting.utils.ParserUtils
import org.springframework.context.annotation.Bean
import org.springframework.integration.dsl.IntegrationFlow
import java.util.UUID
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class PersonSubscriber(
    private val eventHandlerRegistry: EventHandlerRegistry
) {

    @Bean
    fun personFlow() = IntegrationFlow.from("personChannel")
        .handle { genericMessage: Message<*> ->
            handleMessage(genericMessage)
        }
        .get()

    private fun handleMessage(genericMessage: Message<*>): Message<*> {
        val entity = Entity.valueOf(genericMessage.headers[MESSAGE_ENTITY_TYPE_HEADER].toString())
        val operation = Operation.valueOf(genericMessage.headers[MESSAGE_OPERATION_TYPE_HEADER].toString())

        //TODO: Arrange dynamic way of manually acknowledging message
        //GcpPubSubHeaders.getOriginalMessage(genericMessage).get().ack()

        val handler = eventHandlerRegistry.getHandler(entity,operation)
            ?: throw IllegalArgumentException("No handler found for the received entity and operation types. " +
                    "Entity: $entity, Operation: $operation")

        val eventClassType = handler.eventClassType as Class<*>
        val event = ParserUtils.parseJsonData(genericMessage.payload as ByteArray, eventClassType)

        val messageId : UUID = genericMessage.headers[MESSAGE_ID_HEADER] as UUID

        handler.handle(messageId, eventClassType.cast(event))

        return genericMessage
    }
}