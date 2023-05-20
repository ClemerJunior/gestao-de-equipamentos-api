package br.com.nomeaplicativo.api.util.security.dtos;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@ApiModel(value = "LoginInfo")
public class LoginInfoDTO {

    private String username;
    private String senha;

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.username, this.senha);
    }
}
