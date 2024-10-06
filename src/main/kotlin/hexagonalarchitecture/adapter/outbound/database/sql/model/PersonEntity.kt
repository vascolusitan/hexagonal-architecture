package hexagonalarchitecture.adapter.outbound.database.sql.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val messageId: String? = null,
    val timeStamp: LocalDateTime? = LocalDateTime.now(),
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String
)
