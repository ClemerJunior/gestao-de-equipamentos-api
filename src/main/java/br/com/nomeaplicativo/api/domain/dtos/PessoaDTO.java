package br.com.nomeaplicativo.api.domain.dtos;

import br.com.nomeaplicativo.api.domain.enumeration.SexoEnum;
import br.com.nomeaplicativo.api.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ApiModel(value = "Pessoa")
public class PessoaDTO {
    private Long id;

    @Length(max = Constants.TAMANHO_NOME, message = Constants.MSG_NOME_LENGTH)
    @Pattern(regexp = "[\\D ]{1,40}", message = Constants.MSG_NOME_INVALIDO)
    @NotBlank(message = Constants.MSG_NOME_NOT_BLANK)
    private String nome;

    @Length(max = Constants.TAMANHO_SOBRENOME, message = Constants.MSG_SOBRENOME_MAX_LENGTH)
    @Pattern(regexp = "[\\D ]{1,100}", message = Constants.MSG_SOBRENOME_INVALIDO)
    @NotBlank(message = Constants.MSG_SOBRENOME_NOT_BLANK)
    private String sobrenome;

    @JsonFormat(pattern = Constants.DD_MM_YYYY)
    @NotNull(message = Constants.MSG_DATA_NASCIMENTO_NOT_NULL)
    private LocalDate dataNascimento;

    private SexoEnum sexo;

}
