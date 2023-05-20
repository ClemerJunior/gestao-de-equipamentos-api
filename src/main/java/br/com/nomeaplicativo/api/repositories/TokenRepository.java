package br.com.nomeaplicativo.api.repositories;

import br.com.nomeaplicativo.api.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenAndUsuarioIdAndDataValidadeAfter(String token, Long id, LocalDateTime dataValidade);
}
