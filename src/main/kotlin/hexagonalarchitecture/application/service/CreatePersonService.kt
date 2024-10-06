package hexagonalarchitecture.application.service

import hexagonalarchitecture.adapter.outbound.database.redis.AuditMessageAdapter
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.port.inbound.CreatePersonUseCase
import hexagonalarchitecture.application.port.outbound.AuditMessagePort
import hexagonalarchitecture.application.port.outbound.CreatePersonPort
import org.springframework.stereotype.Service

@Service
class CreatePersonService(
    private val createPersonPort: CreatePersonPort,
    private val auditMessagePort: AuditMessagePort
): CreatePersonUseCase {

    override fun create(messageId: String, messageHash: Int, person: Person) {
        createPersonPort.createWithMessageId(messageId, person)
        auditMessagePort.audit(messageId, messageHash)
    }

}