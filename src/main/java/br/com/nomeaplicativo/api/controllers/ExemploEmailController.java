package br.com.nomeaplicativo.api.controllers;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.services.ExemploEmailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
@Api(tags = "EmailService", description = " ")
public class ExemploEmailController {

    private final ExemploEmailService exemploEmailService;

    @GetMapping
    public ResponseEntity<String> sendSimpleMessage(@AuthenticationPrincipal final Usuario usuario) {

        exemploEmailService.enviarEmailAtivacao(new Usuario());

        return ResponseEntity.ok("Sucesso");
    }

}
