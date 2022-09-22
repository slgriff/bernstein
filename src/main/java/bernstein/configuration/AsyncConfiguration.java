package bernstein.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration(proxyBeanMethods = false)
@ConditionalOnExpression("${async.configuration.enabled:true}")
@EnableAsync
public class AsyncConfiguration {
}
