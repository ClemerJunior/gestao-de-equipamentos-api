package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.domain.dtos.EmailDTO;
import br.com.nomeaplicativo.api.util.Messages;
import br.com.nomeaplicativo.api.util.TemplateEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExemploEmailService {

    private final TemplateEmail templateEmail;

    private final EnvioEmailService service;

    private final Messages messages;

    public void enviarEmailAtivacao(final Usuario usuario){

        final EmailDTO email = new EmailDTO();
        email.setAssunto("assunto de exemplo");
        email.setEmail("leandro.barbosa.teles@gmail.com");
        email.setNome("Leandro");

        String conteudo = templateEmail.getTemplate("confirmacaoEmail.html");
        conteudo = conteudo.replace("${nome}", email.getNome())
        .replace("${userToken}", "leroleroleroblablabla");

        email.setConteudo(conteudo);

        service.enviarEmail(email);
    }
}
