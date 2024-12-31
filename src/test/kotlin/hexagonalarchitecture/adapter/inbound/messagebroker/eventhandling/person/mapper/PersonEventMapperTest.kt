package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling.person.mapper

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.PersonCreatedEvent
import hexagonalarchitecture.application.dto.CreatePersonDto
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class PersonEventMapperTest {

    private var personEventMapper: PersonEventMapper = Mappers.getMapper(PersonEventMapper::class.java)

    private var fixture = Fixture()

    @Test
    fun `should return CreatePersonDto from PersonCreatedEvent`() {
        // arrange
        val event = fixture.build<PersonCreatedEvent>()
            .with("personId", UUID.randomUUID().toString())
            .create()

        val expectedDto = CreatePersonDto(
            personId = event.personId,
            name = event.name,
            sex = event.sex,
            age = event.age,
            maturity = event.maturity,
            messageId = null
        )

        // act
        val result = personEventMapper.eventToDto(event)

        // assert
        assertThat(result).isEqualTo(expectedDto)

    }

}