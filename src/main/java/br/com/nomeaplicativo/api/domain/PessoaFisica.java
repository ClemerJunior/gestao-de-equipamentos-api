package br.com.nomeaplicativo.api.domain;

import br.com.nomeaplicativo.api.domain.converter.SexoConverter;
import br.com.nomeaplicativo.api.domain.enumeration.SexoEnum;
import br.com.nomeaplicativo.api.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PESSOA_FISICA")
@PrimaryKeyJoinColumn(name="id")
public class PessoaFisica extends Pessoa {

    @Column(name = "NOME", length = Constants.TAMANHO_NOME, nullable = false)
    private String nome;

    @Column(name = "SOBRENOME", length = Constants.TAMANHO_SOBRENOME, nullable = false)
    private String sobrenome;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SEXO", length = 1)
    @Convert(converter = SexoConverter.class)
    private SexoEnum sexo;

    @CPF
    @Column(name = "CPF", length = 11)
    private String cpf;

}
