package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling

import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Entity
import hexagonalarchitecture.adapter.inbound.messagebroker.avro.header.Operation
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class Handler(val entity: Entity, val operation: Operation)