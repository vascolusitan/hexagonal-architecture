package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub

import avro.header.Entity
import avro.header.Operation
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders
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
        .log("Received message: ${'$'}{headers}")
        .log("Message Payload: ${'$'}{payload}")
        .log("Acknowledgeable message: ${'$'}{headers[gcp_pubsub_original_message]}")
        .handle { genericMessage: Message<*> ->
            val entity = Entity.valueOf(genericMessage.headers["eventEntity"].toString())
            val operation = Operation.valueOf(genericMessage.headers["eventOperation"].toString())

            GcpPubSubHeaders.getOriginalMessage(genericMessage).get().ack()

            val handler = eventHandlerRegistry.getHandler(entity,operation)
                ?: throw IllegalArgumentException("No handler found for the received entity and operation types. " +
                        "Entity: $entity, Operation: $operation")

            val event = parseJsonData(genericMessage.payload as ByteArray, handler.eventClassType as Class<*>)

            val messageId : UUID = genericMessage.headers["id"] as UUID

            handler.handle(messageId, handler.castToEvent(event)!!)
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