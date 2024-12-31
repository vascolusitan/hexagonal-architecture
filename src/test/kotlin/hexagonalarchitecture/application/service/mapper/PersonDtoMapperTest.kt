package hexagonalarchitecture.application.service.mapper

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.application.domain.Maturity
import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.domain.Sex
import hexagonalarchitecture.application.dto.CreatePersonDto
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class PersonDtoMapperTest {

    private var personDtoMapper: PersonDtoMapper = Mappers.getMapper(PersonDtoMapper::class.java)

    private var fixture = Fixture()

    @Test
    fun `should return Person from CreatePersonDto`() {
        // arrange
        val dto = fixture.build<CreatePersonDto>()
            .with(CreatePersonDto::personId.name, UUID.randomUUID().toString())
            .with(CreatePersonDto::maturity.name, Maturity.ADULT.name)
            .with(CreatePersonDto::sex.name, Sex.FEMININE.name)
            .create()

        val expectedPerson = Person(
            personId = UUID.fromString(dto.personId),
            name = dto.name,
            sex = Sex.valueOf(dto.sex),
            age = dto.age,
            maturity = Maturity.valueOf(dto.maturity)
        )

        // act
        val result = personDtoMapper.dtoToDomain(dto)

        // assert
        assertThat(result).isEqualTo(expectedPerson)
    }

}