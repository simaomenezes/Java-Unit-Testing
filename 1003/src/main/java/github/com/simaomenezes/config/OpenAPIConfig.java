package github.com.simaomenezes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Person API")
                        .version("1.0.0")
                        .description("Documentation Person API v1.0.0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(
                                new io.swagger.v3.oas.models.info.License()
                                        .name("Apache 2.0")
                                        .url("http://www.apache.org/licenses/LICENSE-2.0")
                        )
        );
    }
}
