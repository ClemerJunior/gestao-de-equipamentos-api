package br.com.nomeaplicativo.api.controllers;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.domain.dtos.UsuarioDTO;
import br.com.nomeaplicativo.api.services.UsuarioService;
import br.com.nomeaplicativo.api.util.genericrestcrud.GenericController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Api(tags = "UsuarioService", description = " ")
public class UsuarioController extends GenericController<UsuarioDTO, Usuario, Long> {


    private final UsuarioService service;

    @Override
    protected UsuarioService getService() {
        return service;
    }
}
