package br.com.nomeaplicativo.api.domain;

import br.com.nomeaplicativo.api.domain.converter.SimNaoConverter;
import br.com.nomeaplicativo.api.domain.enumeration.SimNaoEnum;
import br.com.nomeaplicativo.api.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "NOME_USUARIO", nullable = false, unique = true)
    private String nomeUsuario;

    @Email
    @Column(name = "EMAIL", length = Constants.TAMANHO_EMAIL, nullable = false)
    private String email;

    @Column(name = "SENHA", length = Constants.TAMANHO_SENHA_BANCO, nullable = false)
    private String senha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PERFIL_ID")
    private Perfil perfil;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ULTIMO_ACESSO")
    private LocalDateTime ultimoAcesso;

    @Column(name = "STATUS_ATIVACAO", length = 1, nullable = false)
    @Convert(converter = SimNaoConverter.class)
    private SimNaoEnum status = SimNaoEnum.NAO;

    /**
     * Daqui para baixo, métodos necessários para o spring security gerenciar o usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfil.getAcoes();
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SimNaoEnum.SIM.equals(status);
    }
}
