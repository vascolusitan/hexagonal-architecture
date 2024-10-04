package hexagonalarchitecture.adapter.outbound.database.model

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
    val timeStamp: LocalDateTime,
    val name: String,
    val sex: String,
    val age: Int,
    val maturity: String
)
