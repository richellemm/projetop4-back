package br.com.alunoonline.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Acesso ao Swagger: http://localhost:8080/swagger-ui/index.html */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Aluno Online")
                        .version("1.0")
                        .description("Documentação da API Aluno Online"));
    }
}
