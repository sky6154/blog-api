package blog.develobeer.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("blog.develobeer.api")
@ConfigurationProperties(prefix="origin")
@EnableWebMvc
@Data
public class AppConfig implements WebMvcConfigurer {
    private String[] hosts;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/info/**")
                .allowedOrigins(hosts)
                .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("X-Auth-Token", "Content-Type")
//                .exposedHeaders("custom-header1", "custom-header2")
                .allowCredentials(false)
                .maxAge(4800);
    }
}
