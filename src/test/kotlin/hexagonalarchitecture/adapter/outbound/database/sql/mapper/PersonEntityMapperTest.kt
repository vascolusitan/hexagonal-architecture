package hexagonalarchitecture.adapter.outbound.database.sql.mapper

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.adapter.outbound.database.sql.model.PersonEntity
import hexagonalarchitecture.application.dto.CreatePersonDto
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class PersonEntityMapperTest {

    private var personDtoMapper: PersonEntityMapper = Mappers.getMapper(PersonEntityMapper::class.java)

    private var fixture = Fixture()

    @Test
    fun `should return PersonEntity from CreatePersonDto`() {
        // arrange
        val dto = fixture.build<CreatePersonDto>()
            .with(PersonEntity::personId.name, UUID.randomUUID().toString())
            .with(PersonEntity::messageId.name, UUID.randomUUID().toString())
            .create()

        val expectedEntity = PersonEntity(
            personId = dto.personId,
            name = dto.name,
            sex = dto.sex,
            age = dto.age,
            maturity = dto.maturity,
            messageId = dto.messageId
        )

        // act
        val result = personDtoMapper.dtoToDbEntity(dto)

        // assert
        assertThat(result).usingRecursiveComparison()
            .ignoringFields(PersonEntity::timeStamp.name)
            .isEqualTo(expectedEntity)
    }

}