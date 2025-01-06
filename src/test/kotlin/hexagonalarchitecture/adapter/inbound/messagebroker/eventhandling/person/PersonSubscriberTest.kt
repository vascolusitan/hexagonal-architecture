package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ENTITY_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ID_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_OPERATION_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Entity
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandler
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.EventHandlerRegistry
import hexagonalarchitecture.application.domain.Maturity
import hexagonalarchitecture.application.domain.Sex
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verifyOrder
import java.util.Base64
import java.util.UUID
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder

@ExtendWith(MockKExtension::class)
class PersonSubscriberTest {

    @MockK
    private lateinit var eventHandlerRegistry: EventHandlerRegistry

    @InjectMockKs
    private lateinit var personSubscriber: PersonSubscriber

    @Test
    fun `should subscribe to messages of type person`() {
        // arrange
        val messageId = UUID.randomUUID()
        val entityType = Entity.PERSON
        val operationType = Operation.CREATE
        val expectedPersonCreatedEvent = PersonCreatedEvent.newBuilder()
            .setPersonId("37732e8c-af67-4ff9-a283-3ceb55fcd4f2")
            .setName("Vasco Lusitano")
            .setAge(27)
            .setSex(Sex.MASCULINE.name)
            .setMaturity(Maturity.ADULT.name)
            .build()

        val headers: MutableMap<String, Any> = mutableMapOf(
            MESSAGE_ID_HEADER to messageId,
            MESSAGE_ENTITY_TYPE_HEADER to entityType,
            MESSAGE_OPERATION_TYPE_HEADER to operationType
        )
        val payload = "ewogICJwZXJzb25JZCI6ICIzNzczMmU4Yy1hZjY3LTRmZjktYTI4My0zY2ViNTVmY2Q0ZjIiLAogICJuYW1lIjogIlZhc2" +
                "NvIEx1c2l0YW5vIiwKICAiYWdlIjogMjcsCiAgInNleCI6ICJNQVNDVUxJTkUiLAogICJtYXR1cml0eSI6ICJBRFVMVCIKfQ=="
        val decodedPayload =  Base64.getDecoder().decode(payload)
        val message: Message<*> = MessageBuilder.createMessage(
            decodedPayload,
            MessageHeaders(headers)
        )

        val mockHandler = mockk<EventHandler<PersonCreatedEvent>>(relaxed = true)
        every { eventHandlerRegistry.getHandler(any(), any()) } returns mockHandler
        every { mockHandler.eventClassType } returns PersonCreatedEvent::class.java

        // act
        personSubscriber::class.java
            .declaredMethods
            .first { it.name == "handleMessage" }
            .apply { isAccessible = true }
            .invoke(personSubscriber, message)

        // assert
        verifyOrder {
            eventHandlerRegistry.getHandler(entityType, operationType)
            mockHandler.handle(messageId, expectedPersonCreatedEvent)
        }

    }

}