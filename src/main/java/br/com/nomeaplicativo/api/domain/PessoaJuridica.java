package br.com.nomeaplicativo.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name="id")
public class PessoaJuridica extends Pessoa {

    @CNPJ
    @Column(name = "CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL", length = 100, nullable = false)
    private String razaoSocial;
}
