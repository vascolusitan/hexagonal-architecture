package hexagonalarchitecture.adapter.inbound.rest

import hexagonalarchitecture.adapter.outbound.database.sql.PersonRepository
import hexagonalarchitecture.adapter.outbound.database.sql.model.PersonEntity
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
        val person1 = PersonEntity(
            id = 1L,
            name = "Vasco Lusitano",
            sex = "MASCULINE",
            age = 27,
            maturity = "ADULT"
        )
        val person2 = PersonEntity(
            id = 2L,
            name = "Rita Lima",
            sex = "FEMININE",
            age = 26,
            maturity = "ADULT"
        )
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