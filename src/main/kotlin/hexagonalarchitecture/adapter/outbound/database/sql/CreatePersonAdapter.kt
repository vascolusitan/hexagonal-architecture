package hexagonalarchitecture.adapter.outbound.database.sql

import hexagonalarchitecture.adapter.outbound.database.sql.mapper.PersonEntityMapper
import hexagonalarchitecture.application.dto.CreatePersonDto
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import org.springframework.stereotype.Component

@Component
class CreatePersonAdapter(
    private val personRepository: PersonRepository,
    private val personEntityMapper: PersonEntityMapper
): CreatePersonPort {

    override fun save(createPersonDto: CreatePersonDto) {
        val personEntity = personEntityMapper.dtoToDbEntity(createPersonDto)
        personRepository.save(personEntity)
    }

}