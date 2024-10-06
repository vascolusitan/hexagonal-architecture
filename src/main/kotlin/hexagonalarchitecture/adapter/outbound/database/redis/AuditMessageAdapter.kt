package hexagonalarchitecture.adapter.outbound.database.redis

import hexagonalarchitecture.adapter.outbound.database.redis.model.Message
import hexagonalarchitecture.application.port.outbound.AuditMessagePort
import org.springframework.stereotype.Component

@Component
class AuditMessageAdapter(
    private val messageRepository: MessageRepository,
): AuditMessagePort {
    override fun audit(messageId: String, messageHash: Int) {
        messageRepository.save(
            Message(
                messageId = messageId,
                messageHash = messageHash
            )
        )
    }
}