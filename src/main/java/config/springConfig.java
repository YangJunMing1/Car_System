package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"config"})
@PropertySource({"jdbc.properties"})
@Import(JdbcConfig.class)
public class springConfig {
}
