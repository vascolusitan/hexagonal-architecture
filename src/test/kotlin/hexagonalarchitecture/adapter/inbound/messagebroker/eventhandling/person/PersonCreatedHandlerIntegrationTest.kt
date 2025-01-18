package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
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
class PersonCreatedHandlerIntegrationTest {

    @Autowired
    private lateinit var personCreatedHandler: PersonCreatedHandler

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    fun `should handle PersonCreatedEvent`() {
        // arrange
        val personId = UUID.randomUUID().toString()
        val messageId = UUID.randomUUID()
        val event = PersonCreatedEvent.newBuilder()
            .setPersonId(personId)
            .setName("Vasco Lusitano")
            .setAge(28)
            .setSex(Sex.MASCULINE.name)
            .setMaturity(Maturity.ADULT.name)
            .build()

        personCreatedHandler.handle(messageId, event)

        // assert
        await.untilAsserted {
            assertThat(personRepository.findById(personId)).isNotNull
        }
    }
}