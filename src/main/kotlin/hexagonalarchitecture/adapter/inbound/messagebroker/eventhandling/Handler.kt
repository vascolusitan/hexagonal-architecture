package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling

import avro.header.Entity
import avro.header.Operation
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class Handler(val entity: Entity, val operation: Operation)