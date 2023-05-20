package br.com.nomeaplicativo.api.util.security.dtos;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "AuthenticationToken")
@AllArgsConstructor
public class AuthenticationTokenDTO {

    private String tokenType;
    private String token;
}
