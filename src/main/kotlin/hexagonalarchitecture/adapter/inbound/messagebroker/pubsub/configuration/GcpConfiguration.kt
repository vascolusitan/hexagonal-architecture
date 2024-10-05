package hexagonalarchitecture.adapter.inbound.messagebroker.pubsub.configuration

import com.google.api.gax.core.CredentialsProvider
import com.google.api.gax.core.NoCredentialsProvider
import com.google.cloud.spring.core.GcpProjectIdProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GcpConfiguration {
    @Bean
    fun projectIdProvider(
        @Value("\${spring.cloud.gcp.project-id}") projectId: String?,
    ): GcpProjectIdProvider {
        return GcpProjectIdProvider { projectId }
    }

    @Bean
    fun credentialsProvider(): CredentialsProvider {
        return NoCredentialsProvider.create()
    }
}