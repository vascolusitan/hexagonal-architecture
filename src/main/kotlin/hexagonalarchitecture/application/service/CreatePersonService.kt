package hexagonalarchitecture.application.service

import hexagonalarchitecture.application.domain.Person
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import hexagonalarchitecture.application.port.outbound.AuditMessagePort
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import org.springframework.stereotype.Service

@Service
class CreatePersonUseCaseService(
    private val createPersonPort: CreatePersonPort,
    private val auditMessagePort: AuditMessagePort
): CreatePersonUseCase {

    override fun create(messageId: String, messageHash: Int, person: Person) {
        createPersonPort.createWithMessageId(messageId, person)
        auditMessagePort.audit(messageId, messageHash)
    }

}