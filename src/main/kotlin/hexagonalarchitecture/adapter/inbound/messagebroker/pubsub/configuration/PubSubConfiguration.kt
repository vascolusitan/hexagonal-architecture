package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.configuration

import com.google.cloud.spring.pubsub.core.PubSubTemplate
import com.google.cloud.spring.pubsub.integration.AckMode
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

@Configuration
class PubSubConfiguration {
    @Bean
    fun messageChannelAdapter(
        @Qualifier("pubsubInputChannel") inputChannel: MessageChannel,
        pubSubTemplate: PubSubTemplate,
    ): PubSubInboundChannelAdapter =
        PubSubInboundChannelAdapter(pubSubTemplate, "json-topic-sub").apply {
            outputChannel = inputChannel
            ackMode = AckMode.MANUAL
        }

    @Bean
    fun pubsubInputChannel(): MessageChannel = DirectChannel()
}