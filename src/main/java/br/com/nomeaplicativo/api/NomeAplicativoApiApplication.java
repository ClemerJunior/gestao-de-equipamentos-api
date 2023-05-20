package br.com.nomeaplicativo.api;

import br.com.nomeaplicativo.api.configurations.NomeAplicativoApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NomeAplicativoApiProperties.class)
public class NomeAplicativoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(NomeAplicativoApiApplication.class, args);
    }
}
