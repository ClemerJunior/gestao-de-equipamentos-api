package br.com.nomeaplicativo.api.controllers;

import br.com.nomeaplicativo.api.util.security.JwTokenManager;
import br.com.nomeaplicativo.api.util.security.dtos.AuthenticationTokenDTO;
import br.com.nomeaplicativo.api.util.security.dtos.LoginInfoDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autenticacao")
@Api(tags = "AutenticacaoService", description = " ")
public class AutenticacaoController {

    private final AuthenticationManager authManager;

    private final JwTokenManager tokenManager;

    @PostMapping
    public ResponseEntity<AuthenticationTokenDTO> authenticate(@RequestBody final LoginInfoDTO loginInfo){
            var authentication = authManager.authenticate(loginInfo.build());
            var tokenResponse = new AuthenticationTokenDTO("Bearer", tokenManager.generateToken(authentication));
            return ResponseEntity.ok(tokenResponse);
    }
    
}
