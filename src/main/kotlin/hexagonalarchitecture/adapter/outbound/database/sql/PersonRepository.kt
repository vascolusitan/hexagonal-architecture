package hexagonalarchitecture.adapter.outbound.database.sql

import hexagonalarchitecture.adapter.outbound.database.sql.model.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<PersonEntity, String>