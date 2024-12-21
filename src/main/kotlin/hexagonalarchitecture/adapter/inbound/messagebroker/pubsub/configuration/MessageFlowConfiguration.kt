package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.router.HeaderValueRouter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.ErrorMessage

@Configuration
class MessageFlowConfiguration {

    @Bean
    fun inboundChannel(): MessageChannel = DirectChannel()

    @Bean
    fun personChannel(): MessageChannel = DirectChannel()

    @Bean
    fun errorChannel(): MessageChannel = DirectChannel()

    //.filter(true /*TODO: schemaValidationFilter*/)
    @Bean
    fun inboundFlow() = IntegrationFlow.from("inboundChannel")
        .route(eventTypeRouter())
        .get()

    @Bean
    @ServiceActivator(inputChannel = "errorChannel")
    fun errorHandler(): (ErrorMessage) -> Unit = { errorMessage ->
        println("Error handling message: ${errorMessage.payload}")
    }

    private fun eventTypeRouter() = HeaderValueRouter("eventEntity").apply {
        setChannelMapping("person", "personChannel")
        defaultOutputChannel = errorChannel()
    }

//    fun genericFlow(type: String) =
//        IntegrationFlow.from(type + "Channel")
//            .route(HeaderValueRouter("eventOperation").apply {
//                setChannelMapping("Created", type + "CreatedHandler")
//                setChannelMapping("Updated", type + "UpdatedHandler")
//            }).get()

}