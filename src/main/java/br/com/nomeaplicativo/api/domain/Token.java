package br.com.nomeaplicativo.api.domain;

import br.com.nomeaplicativo.api.util.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOKEN", length = Constants.TAMANHO_TOKEN_BANCO, nullable = false)
    private String token;

    @OneToOne(optional = false)
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @Column(name = "DATA_VALIDADE", nullable = false)
    private LocalDateTime dataValidade;

}
