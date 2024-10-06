package hexagonalarchitecture.adapter.outbound.database.redis

import hexagonalarchitecture.adapter.outbound.database.redis.model.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: CrudRepository<Message, String>