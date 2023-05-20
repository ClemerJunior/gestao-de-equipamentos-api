package br.com.nomeaplicativo.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "PERFIL")
public class Perfil implements GrantedAuthoritiesContainer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length =  50, nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", length = 200, nullable = false)
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "PERFIL_ACAO",
            joinColumns = @JoinColumn(name = "PERFIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACAO_ID")
    )
    private Set<Acao> acoes;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return this.getAcoes();
    }
}
