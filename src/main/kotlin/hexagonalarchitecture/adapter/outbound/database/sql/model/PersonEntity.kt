package hexagonalarchitecture.adapter.outbound.database.sql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    @Column(name = "person_id", updatable = false, nullable = false)
    val personId: String,
    @Column(name = "message_id")
    val messageId: String? = null,
    @Column(name = "timestamp")
    val timeStamp: LocalDateTime = LocalDateTime.now(),
    @Column(name = "name")
    val name: String,
    @Column(name = "sex")
    val sex: String,
    @Column(name = "age")
    val age: Int,
    @Column(name = "maturity")
    val maturity: String
)
