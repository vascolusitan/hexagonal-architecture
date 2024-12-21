package hexagonalarchitecture.adapter.outbound.database.sql

import hexagonalarchitecture.adapter.outbound.database.sql.mapper.PersonEntityMapper
import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.port.outbound.GetAllPersonsPort
import org.springframework.stereotype.Component

@Component
class GetAllPersonsAdapter(
    private val personRepository: PersonRepository,
    private val personEntityMapper: PersonEntityMapper
): GetAllPersonsPort {
    override fun get(): List<Person> {
        return personEntityMapper.dbEntityToDomain(
            personRepository.findAll()
        )
    }
}