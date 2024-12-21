package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import avro.header.Entity
import avro.header.Operation
import com.fasterxml.jackson.databind.ObjectMapper
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandlerRegistry
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
            val entity = Entity.valueOf(genericMessage.headers["eventEntity"].toString())
            val operation = Operation.valueOf(genericMessage.headers["eventOperation"].toString())

            //TODO: Arrange dynamic way of manually acknowledging message
            //GcpPubSubHeaders.getOriginalMessage(genericMessage).get().ack()

            val handler = eventHandlerRegistry.getHandler(entity,operation)
                ?: throw IllegalArgumentException("No handler found for the received entity and operation types. " +
                        "Entity: $entity, Operation: $operation")

            val eventClassType = handler.eventClassType as Class<*>
            val event = parseJsonData(genericMessage.payload as ByteArray, eventClassType)

            val messageId : UUID = genericMessage.headers["id"] as UUID

            handler.handle(messageId, eventClassType.cast(event))
        }
        .get()

    private fun <T> parseJsonData(byteArray: ByteArray, clazz: Class<T>): T {
        val jsonString = String(byteArray)
        return ObjectMapper().readValue(jsonString, clazz)
    }

//    private fun eventOperationRouter() = HeaderValueRouter("eventOperation").apply {
//        setChannelMapping("created", "personCreatedChannel")
//        //defaultOutputChannel = errorChannel()
//    }


//    @ServiceActivator(inputChannel = "personInputChannel")
//    fun messageReceiver(
//        @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) message: BasicAcknowledgeablePubsubMessage,
//        @Payload payload: ByteArray
//    ) {
//        val person = Gson().fromJson(payload, Person::class.java)
//        createPersonUseCase.create(
//            message.pubsubMessage.messageId,
//            message.hashCode(),
//            person
//        )
//        message.ack()
//    }

//    @Bean
//    fun intFinishFlow(personInputChannel: MessageChannel): IntegrationFlow {
//        return integrationFlow {
//            handle { message: Message<*> ->
//                handleMessage(message)
//            }
//        }
//
//    }
//
//
//    fun handleMessage(message: Message<*>): String {
//        return "messageHandler"
//    }


}