package hexagonalarchitecture.adapter.inbound.messagebroker

import com.google.cloud.spring.pubsub.core.PubSubTemplate
import com.google.protobuf.ByteString
import com.google.pubsub.v1.PubsubMessage
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ENTITY_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_ID_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.MessageBrokerConstants.MESSAGE_OPERATION_TYPE_HEADER
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Entity
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Operation
import hexagonalarchitecture.adapter.inbound.messagebroker.configuration.pubsub.PubSubConfiguration.Companion.PUBSUB_TOPIC
import hexagonalarchitecture.adapter.outbound.database.sql.PersonRepository
import hexagonalarchitecture.application.domain.Maturity
import hexagonalarchitecture.application.domain.Sex
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.kotlin.await
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PubSubE2ETest {

    @Autowired
    private lateinit var pubSubTemplate: PubSubTemplate

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    fun `should handle PersonCreatedEvent`() {
        // arrange
        val personId = UUID.randomUUID().toString()
        val event = PersonCreatedEvent.newBuilder()
            .setPersonId(personId)
            .setName("Vasco Lusitano")
            .setAge(27)
            .setSex(Sex.MASCULINE.name)
            .setMaturity(Maturity.ADULT.name)
            .build()
        val messageId = UUID.randomUUID().toString()
        val message: PubsubMessage = PubsubMessage.newBuilder()
            .setMessageId(messageId)
            .putAllAttributes(
                mapOf(
                    MESSAGE_ID_HEADER to messageId,
                    MESSAGE_ENTITY_TYPE_HEADER to Entity.PERSON.name,
                    MESSAGE_OPERATION_TYPE_HEADER to Operation.CREATE.name
                )
            )
            .setData(ByteString.copyFromUtf8(event.toString()))
            .build()

        pubSubTemplate.publish(PUBSUB_TOPIC, message)

        // assert
        await.untilAsserted {
            val result = personRepository.findById(personId)
            assertThat(result).isPresent
            assertThat(result.get().messageId).isEqualTo(messageId)
        }
    }

}