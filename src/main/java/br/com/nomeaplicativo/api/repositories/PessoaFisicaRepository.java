package br.com.nomeaplicativo.api.repositories;

import br.com.nomeaplicativo.api.domain.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
