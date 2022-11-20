package bernstein.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration(proxyBeanMethods = false)
@ConditionalOnExpression("${scheduling.enabled:false}")
@EnableScheduling
public class SchedulingConfiguration {
}
