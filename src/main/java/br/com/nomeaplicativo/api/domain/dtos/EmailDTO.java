package br.com.nomeaplicativo.api.domain.dtos;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Email")
public class EmailDTO {

    private String nome;
    private String email;

    private String assunto;
    private String conteudo;
}
