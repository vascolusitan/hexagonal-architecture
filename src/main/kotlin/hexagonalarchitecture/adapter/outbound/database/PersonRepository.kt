package hexagonalarchitecture.adapter.outbound.database

import hexagonalarchitecture.adapter.outbound.database.model.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<PersonEntity, Long>