package hexagonalarchitecture.adapter.outbound.database

import hexagonalarchitecture.adapter.outbound.database.mapper.PersonEntityMapper
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import org.springframework.stereotype.Component

@Component
class CreatePersonAdapter(
    private val personRepository: PersonRepository,
    private val personEntityMapper: PersonEntityMapper
): CreatePersonPort {

    override fun create(person: Person) {
        personRepository.save(
            personEntityMapper.domainToDbEntity(person)
        )
    }

    override fun audit(message: String) {
        TODO("Not yet implemented")
    }

}