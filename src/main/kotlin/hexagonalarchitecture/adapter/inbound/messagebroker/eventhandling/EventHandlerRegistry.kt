package hexagonalarchitecture.adapter.inbound.messagebroker.eventhandling

import avro.header.Entity
import avro.header.Operation
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class EventHandlerRegistry(
    private val applicationContext: ApplicationContext
) {
    private val handlers: MutableMap<Pair<Entity, Operation>, EventHandler<*>> = mutableMapOf()

    init {
        val handlerBeans = applicationContext.getBeansWithAnnotation(Handler::class.java)

        for (bean in handlerBeans.values) {
            val handlerAnnotation = bean::class.java.getAnnotation(Handler::class.java)
            handlerAnnotation?.let {
                val key = Pair(it.entity, it.operation)
                handlers[key] = bean as EventHandler<*>
            }
        }
    }

    fun getHandler(entity: Entity, operation: Operation): EventHandler<*>? {
        return handlers[Pair(entity,operation)]
    }
}