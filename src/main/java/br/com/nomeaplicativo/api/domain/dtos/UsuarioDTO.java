package br.com.nomeaplicativo.api.domain.dtos;

import br.com.nomeaplicativo.api.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "Usuario")
public class UsuarioDTO {

    private Long id;

    @Length(max = Constants.TAMANHO_EMAIL)
    @Email(message = Constants.MSG_EMAIL_NOT_VALID)
    @NotBlank(message = Constants.MSG_EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = Constants.MSG_SENHA_NOT_BLANK)
    @Length(min = 8, max = Constants.TAMANHO_SENHA_TELA, message = Constants.MSG_SENHA_LENGTH_NOT_VALID)
    private String senha;

    @NotBlank(message = Constants.MSG_NOME_USUARIO_NOT_BLANK)
    private String nomeUsuario;

    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    @JsonProperty
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
