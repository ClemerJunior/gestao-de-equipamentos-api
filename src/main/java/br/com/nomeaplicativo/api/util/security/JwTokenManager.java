package br.com.nomeaplicativo.api.util.security;

import br.com.nomeaplicativo.api.configurations.NomeAplicativoApiProperties;
import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.util.DateUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwTokenManager {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String PREFIXO_AUTHORIZATION_HEADER = "Bearer ";

    private final NomeAplicativoApiProperties properties;

    public String generateToken(final Authentication authentication) {

        final var usuario = (Usuario) authentication.getPrincipal();
        final var dataAcesso = DateUtil.localDateTimeToDate(usuario.getUltimoAcesso());
        final var expiration = new Date(dataAcesso.getTime()+ properties.getJwt().getExpirationInMillis());

        return Jwts.builder()
            .setIssuer("nomeaplicativo")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(dataAcesso)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, properties.getJwt().getSecret())
                .compact();

    }

    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        final var bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        return (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PREFIXO_AUTHORIZATION_HEADER)) ?
               Optional.of(bearerToken.substring(7)) : Optional.empty();
    }

    public boolean isValid(final String jwt) {
        try {
            Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwt) {
        final var claims = Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public LocalDateTime getTimeOfGeneration(String jwt) {
        final var claims = Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt).getBody();
        return DateUtil.dateToLocalDateTime(claims.getIssuedAt());
    }
}
