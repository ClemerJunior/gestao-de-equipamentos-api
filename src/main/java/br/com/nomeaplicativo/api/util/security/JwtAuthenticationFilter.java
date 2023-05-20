package br.com.nomeaplicativo.api.util.security;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.services.UsuarioService;
import br.com.nomeaplicativo.api.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwTokenManager tokenManager;
    private final UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        tokenManager.getTokenFromRequest(request)
                .ifPresent(jwt -> {
                    if (tokenManager.isValid(jwt)) {
                        final var userId = tokenManager.getUserIdFromToken(jwt);
                        final var userDetails = usuarioService.loadUserById(userId);
                        final var dataAcessoBanco = DateUtil.getLocalDateTimeWithZeroMillis(((Usuario) userDetails).getUltimoAcesso());

                        if (!tokenManager.getTimeOfGeneration(jwt).isBefore(dataAcessoBanco)) {
                            final var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                    null, userDetails.getAuthorities());

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                });

//        todo: remover?
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        if ("OPTIONS".equals(request.getMethod()) && "http://localhost:4200".equals(request.getHeader("Origin"))) {
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
//            response.setHeader("Access-Control-Max-Age", "3600");
//
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(request, response);
//        }

        chain.doFilter(request, response);
    }


}
