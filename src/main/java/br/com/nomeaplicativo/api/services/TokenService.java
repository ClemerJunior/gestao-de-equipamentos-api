package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.configurations.NomeAplicativoApiProperties;
import br.com.nomeaplicativo.api.domain.PessoaFisica;
import br.com.nomeaplicativo.api.domain.Token;
import br.com.nomeaplicativo.api.repositories.TokenRepository;
import br.com.nomeaplicativo.api.util.genericrestcrud.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService extends GenericService<Token, Long> {

    private final NomeAplicativoApiProperties properties;

    private final TokenRepository repository;

    @Override
    protected TokenRepository getRepository() {
        return repository;
    }

    public Token criarTokenAtivacaoConta(final PessoaFisica pessoa) {
        var token = new Token();
        token.setToken(Sha512DigestUtils.shaHex(pessoa.getUsuario().getId()+pessoa.getUsuario().getEmail()+System.currentTimeMillis()));
        token.setUsuario(pessoa.getUsuario());
        token.setDataValidade(LocalDateTime.now().plus( properties.getValidadeTokenAtivacao(), ChronoUnit.MINUTES));
        return save(token);
    }

    Optional<Token> recuperarToken(String token, Long userId){
        return repository.findByTokenAndUsuarioIdAndDataValidadeAfter(token, userId, LocalDateTime.now());
    }
}
