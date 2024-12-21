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

    @Bean
    fun pubsubInputChannel(): MessageChannel = DirectChannel()

    @Bean
    fun messageChannelAdapter(
        @Qualifier("inboundChannel") inboundChannel: MessageChannel,
        pubSubTemplate: PubSubTemplate,
    ): PubSubInboundChannelAdapter =
        PubSubInboundChannelAdapter(pubSubTemplate, "json-topic-sub").apply {
            outputChannel = inboundChannel
            //ackMode = AckMode.MANUAL
        }
}