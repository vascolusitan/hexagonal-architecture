package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.dto.CreatePersonDto
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import hexagonalarchitecture.application.port.outbound.AuditMessagePort
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import hexagonalarchitecture.application.service.mapper.PersonDtoMapper
import org.springframework.stereotype.Service

@Service
class CreatePersonService(
    private val createPersonPort: CreatePersonPort,
    private val personDtoMapper: PersonDtoMapper
): CreatePersonUseCase {

    override fun create(createPersonDto: CreatePersonDto) {
        val person = personDtoMapper.dtoToDomain(createPersonDto)
        val messageId = createPersonDto.messageId
        messageId?.let {
            createPersonPort.createWithMessageId(messageId.toString(), person)
        }
            ?: createPersonPort.create(person)
    }

}