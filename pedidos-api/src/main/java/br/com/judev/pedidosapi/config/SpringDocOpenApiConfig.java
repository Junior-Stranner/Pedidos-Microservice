package br.com.judev.pedidosapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Pedidos API")
                        .version("v1")
                        .description("API para gerenciamento de pedidos.")
                        .termsOfService("https://judev.com.br/meus-cursos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Júnior Stranner")
                                .email("junior@spring-pedidosapi.com"))
                );
    }
}
