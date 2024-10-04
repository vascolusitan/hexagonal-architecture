package hexagonalarchitecture.adapter.inbound.rest

import hexagonalarchitecture.adapter.outbound.database.PersonRepository
import hexagonalarchitecture.adapter.outbound.database.model.PersonEntity
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PersonControllerIntegrationTest {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var personRepository: PersonRepository

    @BeforeEach
    fun setup() {
        personRepository.deleteAll()
        val person1 = PersonEntity(1L, LocalDateTime.now(), "Vasco Lusitano", "MASCULINE", 27, "ADULT")
        val person2 = PersonEntity(2L, LocalDateTime.now(), "Rita Lima", "FEMININE", 26, "ADULT")
        personRepository.saveAll(
            listOf(person1, person2)
        )
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    fun `should get all persons`() {
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