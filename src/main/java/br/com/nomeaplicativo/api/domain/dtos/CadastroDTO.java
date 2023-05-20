package br.com.nomeaplicativo.api.domain.dtos;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Cadastro")
public class CadastroDTO extends PessoaFisicaDTO {

    private UsuarioDTO usuario;
}
