package hexagonalarchitecture.adapter.outbound.database.redis.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime

@RedisHash("Message")
data class Message(
    @Id
    val messageId: String,
    val timeStamp: LocalDateTime? = LocalDateTime.now(),
    val messageHash: Int
)
