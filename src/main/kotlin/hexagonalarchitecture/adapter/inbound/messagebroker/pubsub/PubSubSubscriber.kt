package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders
import com.google.gson.Gson
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PubSubSubscriber(
    private val createPersonUseCase: CreatePersonUseCase
) {

    @ServiceActivator(inputChannel = "pubsubInputChannel")
    fun messageReceiver(
        @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) message: BasicAcknowledgeablePubsubMessage,
        @Payload payload: String
    ) {
        val person = Gson().fromJson(payload, Person::class.java)
        createPersonUseCase.create(
            message.pubsubMessage.messageId,
            message.hashCode(),
            person
        )
        message.ack()
    }

}