package hexagonalarchitecture.adapter.outbound.database.sql

import com.github.nylle.kotlinfixture.Fixture
import hexagonalarchitecture.adapter.outbound.database.sql.mapper.PersonEntityMapper
import hexagonalarchitecture.adapter.outbound.database.sql.model.PersonEntity
import hexagonalarchitecture.application.dto.CreatePersonDto
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreatePersonAdapterTest {

    @MockK
    private lateinit var createPersonRepository: PersonRepository

    @MockK
    private lateinit var personEntityMapper: PersonEntityMapper

    @InjectMockKs
    private lateinit var createPersonAdapter: CreatePersonAdapter

    private val fixture = Fixture()

    @Test
    fun `should save person`() {
        // arrange
        val dto = fixture.create<CreatePersonDto>()
        val entity = fixture.create<PersonEntity>()

        every { personEntityMapper.dtoToDbEntity(dto) } returns entity
        every { createPersonRepository.save(entity) } returns entity

        // act
        createPersonAdapter.save(dto)

        // assert
        verify {
            createPersonRepository.save(entity)
        }

    }

}