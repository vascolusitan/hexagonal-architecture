package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
import hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person.mapper.PersonEventMapper
import hexagonalarchitecture.application.dto.CreatePersonDto
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PersonCreatedHandlerTest {

    @MockK
    private lateinit var personEventMapper: PersonEventMapper

    @RelaxedMockK
    private lateinit var createPersonUseCase: CreatePersonUseCase

    @InjectMockKs
    private lateinit var personCreatedHandler: PersonCreatedHandler

    private val fixture = Fixture()

    @Test
    fun `should handle PersonCreatedEvent`() {
        // arrange
        val messageId = UUID.randomUUID()
        val event = fixture.create<PersonCreatedEvent>()
        val dto = fixture.create<CreatePersonDto>()
        val expectedDto = dto.copy(messageId = messageId.toString())

        every { personEventMapper.eventToDto(event) } returns dto

        // act
        personCreatedHandler.handle(messageId, event)

        // assert
        verify { createPersonUseCase.process(expectedDto) }
    }
}