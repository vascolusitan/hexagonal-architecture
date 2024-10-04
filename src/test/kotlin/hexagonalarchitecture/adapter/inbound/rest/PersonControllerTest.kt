package hexagonalarchitecture.adapter.inbound.rest

import com.ninjasquad.springmockk.MockkBean
import hexagonalarchitecture.adapter.inbound.rest.mapper.PersonApiMapper
import hexagonalarchitecture.adapter.inbound.rest.model.PersonApi
import hexagonalarchitecture.application.domain.model.Maturity
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.domain.model.Sex
import hexagonalarchitecture.application.port.inbound.GetAllPersonsUseCase
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(PersonController::class)
class PersonControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockkBean
    private lateinit var getAllPersonsUseCase: GetAllPersonsUseCase
    @MockkBean
    private lateinit var personApiMapper: PersonApiMapper

    @Test
    fun `should get all persons`() {
        val person1 = Person(1L, "Vasco Lusitano", Sex.MASCULINE, 27, Maturity.ADULT)
        val person2 = Person(2L, "Rita Lima", Sex.FEMININE, 26, Maturity.ADULT)
        val personsList = listOf(person1, person2)
        every { getAllPersonsUseCase.get() } returns personsList
        val expectedPerson1 = PersonApi(1L, "Vasco Lusitano", "MASCULINE", 27, "ADULT")
        val expectedPerson2 = PersonApi(2L, "Rita Lima", "FEMININE", 26, "ADULT")
        every { personApiMapper.domainToApi(personsList) } returns listOf(expectedPerson1, expectedPerson2)

        mockMvc.get("/api/v1/persons")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name") { value("Vasco Lusitano") }
                jsonPath("$[0].sex") { value("MASCULINE") }
                jsonPath("$[0].age") { value(27) }
                jsonPath("$[0].maturity") { value("ADULT") }
                jsonPath("$[1].name") { value("Rita Lima") }
                jsonPath("$[1].sex") { value("FEMININE") }
                jsonPath("$[1].age") { value(26) }
                jsonPath("$[1].maturity") { value("ADULT") }
            }
            .andReturn()
    }
}