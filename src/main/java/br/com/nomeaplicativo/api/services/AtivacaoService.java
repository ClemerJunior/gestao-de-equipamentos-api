package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.enumeration.SimNaoEnum;
import br.com.nomeaplicativo.api.exceptions.NegocioException;
import br.com.nomeaplicativo.api.util.Messages;
import br.com.nomeaplicativo.api.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtivacaoService {

    private final TokenService tokenService;

    private final UsuarioService usuarioService;

    private final Messages messages;


    //TODO ENVIAR EMAIL DE ATIVAÇÃO DE CONTA
    public void ativarConta(Long userId, String token) {
        tokenService.recuperarToken(token, userId)
                .ifPresentOrElse(token1 ->
                        usuarioService.findById(userId)
                                .ifPresent(usuario -> {
                                    usuario.setStatus(SimNaoEnum.SIM);
                                    usuarioService.save(usuario);
                                }), () -> {
                    final var msg = messages.getMessage(Constants.MSG_CONTA_NAO_ATIVADA);
                    log.info(msg);
                    throw new NegocioException(msg);
                });


    }
}
