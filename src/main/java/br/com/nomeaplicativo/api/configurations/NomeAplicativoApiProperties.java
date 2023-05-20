package br.com.nomeaplicativo.api.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("nomeaplicativo-api")
public class NomeAplicativoApiProperties {

    private Long maiorIdade;
    private Long validadeTokenAtivacao;
    private Cors cors = new Cors();
    private Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Cors {
        private String allowedOrigins;
        private String allowedMethods;
    }

    @Getter
    @Setter
    public static class Jwt {
        private String secret;
        private Long expirationInMillis;
    }

}
