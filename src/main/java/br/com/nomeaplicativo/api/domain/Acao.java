package br.com.nomeaplicativo.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ACAO")
public class Acao implements GrantedAuthority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 200)
    private String descricao;

    @Override
    public String getAuthority() {
        return this.getNome();
    }
}
