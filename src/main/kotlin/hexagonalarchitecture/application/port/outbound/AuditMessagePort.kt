package hexagonalarchitecture.application.port.outbound

interface AuditMessagePort {

    fun audit(messageId: String, messageHash: Int)

}