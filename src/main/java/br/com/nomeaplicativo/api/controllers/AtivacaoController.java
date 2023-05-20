package br.com.nomeaplicativo.api.controllers;


import br.com.nomeaplicativo.api.services.AtivacaoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ativacao")
@Api(tags = "AtivacaoService", description = " ")
public class AtivacaoController {

    private final AtivacaoService service;

    @PutMapping(value = "{id}")
    public ResponseEntity<String> ativarConta(@PathVariable final Long id, final String token) {
        service.ativarConta(id, token);

        return ResponseEntity.ok("Sucesso");
    }
}
