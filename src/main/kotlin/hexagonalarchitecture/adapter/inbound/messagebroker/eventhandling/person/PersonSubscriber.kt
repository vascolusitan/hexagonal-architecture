package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ENTITY_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ID_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_OPERATION_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Entity
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandlerRegistry
import hexagonalarchitecture.crosscutting.exception.NotFoundException
import hexagonalarchitecture.crosscutting.exception.ParseException
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

    companion object {
        inline fun <reified T : Enum<T>> Message<*>.getHeader(headerType: Class<T>, headerName: String): T {
            val headerValue = this.headers[headerName].toString()
            return runCatching {
                enumValueOf<T>(headerValue)
            }.onFailure {
                throw ParseException(
                    objectType = headerType.simpleName,
                    value = headerValue
                )
            }.getOrThrow()
        }
    }

    @Bean
    fun personFlow() = IntegrationFlow.from("personChannel")
        .handle { genericMessage: Message<*> ->
            handleMessage(genericMessage)
        }
        .get()

    private fun handleMessage(genericMessage: Message<*>): Message<*> {
        //TODO: validate headers with spring integration
        val entity = genericMessage.getHeader(Entity::class.java, MESSAGE_ENTITY_TYPE_HEADER)
        val operation = genericMessage.getHeader(Operation::class.java, MESSAGE_OPERATION_TYPE_HEADER)

        //TODO: Arrange dynamic way of manually acknowledging message
        //GcpPubSubHeaders.getOriginalMessage(genericMessage).get().ack()

        val handler = eventHandlerRegistry.getHandler(entity,operation)
            ?: throw NotFoundException(
                source = "handler",
                value = "Entity: $entity, Operation: $operation"
            )

        val eventClassType = handler.eventClassType as Class<*>
        val event = ParserUtils.parseJsonData(genericMessage.payload as ByteArray, eventClassType)

        val messageId : UUID = genericMessage.headers[MESSAGE_ID_HEADER] as UUID

        handler.handle(messageId, eventClassType.cast(event))

        return genericMessage
    }

}