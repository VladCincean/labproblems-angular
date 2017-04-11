package ro.droptable.labproblems.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ro.droptable.labproblems.core.JPAConfig;

/**
 * Created by vlad on 11.04.2017.
 */
@Configuration
@ComponentScan({"ro.droptable.labproblems.core"})
@Import({JPAConfig.class})
public class AppLocalConfig {

    /**
     * Enables placeholders usage with SpEL expressions.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
