package br.com.nomeaplicativo.api.configurations;

import br.com.nomeaplicativo.api.domain.Usuario;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
    public static final String AUTHORIZATION_KEY = "JWT";

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.nomeaplicativo.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .ignoredParameterTypes(Usuario.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(ResponseEntity.class, WildcardType.class)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("Ops! Erro inesperado no servidor. :(")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(401)
                                        .message("Você não está autorizado a acessar este recurso.")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Você não pode acessar esse recurso.")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("O recurso que você buscou não foi encontrado.")
                                        .build()
                        ));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        Contact contato = new Contact("Nome Aplicativo",
                "nomeaplicativo",
                "contato@nomeaplicativo.com");

        return new ApiInfoBuilder().title("Nome Aplicacao API Documentation")
                .description("Esta é a documentação interativa da API do Nome Aplicativo")
                .version("1.0")
                .contact(contato)
                .build();
    }
}
