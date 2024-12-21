package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.annotation

import avro.header.Entity
import avro.header.Operation
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class Handler(val entity: Entity, val operation: Operation)