package br.com.nomeaplicativo.api.controllers;

import br.com.nomeaplicativo.api.configurations.SwaggerConfiguration;
import br.com.nomeaplicativo.api.domain.PessoaFisica;
import br.com.nomeaplicativo.api.domain.dtos.CadastroDTO;
import br.com.nomeaplicativo.api.domain.dtos.PessoaFisicaDTO;
import br.com.nomeaplicativo.api.services.PessoaFisicaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoas")
@Api(tags = "PessoaService", description = " ")
public class PessoaController {

    private final MapperFacade mapper;
    private final PessoaFisicaService service;


    @PostMapping
    @ApiOperation(value = "Cadastrar novo usuário", authorizations = @Authorization(SwaggerConfiguration.AUTHORIZATION_KEY))
    public ResponseEntity<CadastroDTO> cadastrarPessoa(@Validated @RequestBody final CadastroDTO cadastroDTO){
        var pessoa = service.save(mapper.map(cadastroDTO, PessoaFisica.class));
        return new ResponseEntity(mapper.map(pessoa, CadastroDTO.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Alterar Pessoa", authorizations = @Authorization(SwaggerConfiguration.AUTHORIZATION_KEY))
    public ResponseEntity<PessoaFisicaDTO> alterarPessoa(@PathVariable("id") final  Long id, @RequestBody final PessoaFisicaDTO pessoaFisica) {
        return service.findById(id).map(o -> {
            mapper.map(pessoaFisica, o);
            return ResponseEntity.ok(mapper.map(service.save(o), PessoaFisicaDTO.class));
        }).orElseThrow();
    }
}












