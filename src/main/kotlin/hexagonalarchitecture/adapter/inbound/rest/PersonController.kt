package hexagonalarchitecture.adapter.inbound.rest

import hexagonalarchitecture.adapter.inbound.rest.mappers.PersonApiMapper
import hexagonalarchitecture.application.port.inbound.GetAllPersonsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/persons")
class PersonController (
    private val getAllPersonsUseCase: GetAllPersonsUseCase,
    private val personApiMapper: PersonApiMapper
) {

    @GetMapping
    fun getAllPersons(): List<PersonApi> {
        return personApiMapper.domainToApi(
            getAllPersonsUseCase.get()
        )
    }

}