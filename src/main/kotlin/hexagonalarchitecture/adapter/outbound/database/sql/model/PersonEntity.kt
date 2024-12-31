package hexagonalarchitecture.adapter.outbound.database.sql.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val personId: UUID,
    val messageId: UUID? = null,
    val timeStamp: LocalDateTime? = LocalDateTime.now(),
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String
)
