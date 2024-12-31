package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.dto.CreatePersonDto
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import hexagonalarchitecture.application.service.mapper.PersonDtoMapper
import org.springframework.stereotype.Service

@Service
class CreatePersonService(
    private val createPersonPort: CreatePersonPort,
    private val personDtoMapper: PersonDtoMapper
): CreatePersonUseCase {

    override fun process(createPersonDto: CreatePersonDto) {
        val person = personDtoMapper.dtoToDomain(createPersonDto)
        person.validateMaturity()

        createPersonPort.save(createPersonDto)
    }

}