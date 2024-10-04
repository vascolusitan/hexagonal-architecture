package hexagonalarchitecture.adapter.outbound.database

import hexagonalarchitecture.adapter.outbound.database.mapper.PersonEntityMapper
import hexagonalarchitecture.adapter.outbound.database.model.PersonEntity
import hexagonalarchitecture.application.domain.model.Maturity
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.domain.model.Sex
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class GetAllPersonsAdapterTest {

    @MockK
    private lateinit var personRepository: PersonRepository
    @MockK
    private lateinit var personEntityMapper: PersonEntityMapper
    @InjectMockKs
    private lateinit var getAllPersonsAdapter: GetAllPersonsAdapter

    @Test
    fun `should get all persons`() {
        val person1 = PersonEntity(1L, LocalDateTime.now(), "Vasco Lusitano", "MASCULINE", 27, "ADULT")
        val person2 = PersonEntity(2L, LocalDateTime.now(), "Rita Lima", "FEMININE", 26, "ADULT")
        val dbEntityPersons = listOf(person1, person2)
        every { personRepository.findAll() } returns dbEntityPersons
        val expectedPerson1 = Person(1L, "Vasco Lusitano", Sex.MASCULINE, 27, Maturity.ADULT)
        val expectedPerson2 = Person(2L, "Rita Lima", Sex.FEMININE, 26, Maturity.ADULT)
        val expectedPersons = listOf(expectedPerson1, expectedPerson2)
        every { personEntityMapper.dbEntityToDomain(dbEntityPersons) } returns expectedPersons

        val returnedPersons = getAllPersonsAdapter.get()

        assertThat(returnedPersons).isEqualTo(expectedPersons)
    }
}