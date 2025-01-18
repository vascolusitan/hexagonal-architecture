package hexagonalarchitecture.adapter.inbound.messagebroker.configuration.pubsub

import com.google.cloud.spring.pubsub.core.PubSubTemplate
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

@Configuration
class PubSubConfiguration {

    companion object {
        const val PUBSUB_TOPIC = "json-topic"
        const val PUBSUB_SUBSCRIPTION = "json-topic-sub"
    }

    @Bean
    fun pubsubInputChannel(): MessageChannel = DirectChannel()

    @Bean
    fun messageChannelAdapter(
        @Qualifier("inboundChannel") inboundChannel: MessageChannel,
        pubSubTemplate: PubSubTemplate,
    ): PubSubInboundChannelAdapter =
        PubSubInboundChannelAdapter(pubSubTemplate, PUBSUB_SUBSCRIPTION).apply {
            outputChannel = inboundChannel
            //ackMode = AckMode.MANUAL
        }
}