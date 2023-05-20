package br.com.nomeaplicativo.api.domain;

import br.com.nomeaplicativo.api.domain.enumeration.TipoTelefoneEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TELEFONE")
public class Telefone {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DDD", nullable = false, length = 2)
    private Integer ddd;

    @Column(name = "TELEFONE", nullable = false, length = 9)
    private String telefone;

    @Column(name = "TIPO", nullable = false, length = 1)
    private TipoTelefoneEnum tipo;
}
