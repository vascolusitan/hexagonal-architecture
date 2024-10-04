package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.model.Maturity
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.domain.model.Sex
import hexagonalarchitecture.application.port.outbound.GetAllPersonsPort
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetAllPersonsServiceTest {

    @MockK
    private lateinit var getAllPersonsPort: GetAllPersonsPort
    @InjectMockKs
    private lateinit var getAllPersonsService: GetAllPersonsService

    @Test
    fun `should get all persons`() {
        val person1 = Person(1L, "Vasco Lusitano", Sex.MASCULINE, 27, Maturity.ADULT)
        val person2 = Person(2L, "Rita Lima", Sex.FEMININE, 26, Maturity.ADULT)
        val expectedPersons = listOf(person1, person2)
        every { getAllPersonsPort.get() } returns expectedPersons

        val returnedPersons = getAllPersonsService.get()

        assertThat(returnedPersons).isEqualTo(expectedPersons)
    }
}