package hexagonalarchitecture.application.service

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.application.domain.Maturity
import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.dto.CreatePersonDto
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import hexagonalarchitecture.application.service.mapper.PersonDtoMapper
import hexagonalarchitecture.crosscutting.exception.ValidationException
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreatePersonServiceTest {

    @RelaxedMockK
    private lateinit var createPersonPort: CreatePersonPort

    @MockK
    private lateinit var personDtoMapper: PersonDtoMapper

    @InjectMockKs
    private lateinit var createPersonService: CreatePersonService

    private val fixture = Fixture()

    @Test
    fun `should validate and create person with message id`() {
        // arrange
        val dto = fixture.build<CreatePersonDto>()
            .with(CreatePersonDto::age.name, 18)
            .with(CreatePersonDto::maturity.name, Maturity.ADULT.name)
            .create()
        val person = fixture.build<Person>()
            .with {
                it.age = dto.age
                it.maturity = Maturity.valueOf(dto.maturity)
            }
            .create()

        every { personDtoMapper.dtoToDomain(dto) } returns person

        // arrange
        createPersonService.process(dto)

        // assert
        verify(exactly = 1) {
            createPersonPort.save(dto)
        }
    }

    @Test
    fun `should not save invalid person and throw validation exception`() {
        // arrange
        val dto = fixture.build<CreatePersonDto>()
            .with(CreatePersonDto::age.name, 10)
            .with(CreatePersonDto::maturity.name, Maturity.ADULT.name)
            .create()
        val person = fixture.build<Person>()
            .with {
                it.age = dto.age
                it.maturity = Maturity.valueOf(dto.maturity)
            }
            .create()

        every { personDtoMapper.dtoToDomain(dto) } returns person

        // arrange
        assertThrows<ValidationException> { createPersonService.process(dto) }

        // assert
        confirmVerified(createPersonPort)
    }

}