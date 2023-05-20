package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.configurations.NomeAplicativoApiProperties;
import br.com.nomeaplicativo.api.domain.PessoaFisica;
import br.com.nomeaplicativo.api.domain.Token;
import br.com.nomeaplicativo.api.domain.dtos.EmailDTO;
import br.com.nomeaplicativo.api.domain.enumeration.SimNaoEnum;
import br.com.nomeaplicativo.api.exceptions.NegocioException;
import br.com.nomeaplicativo.api.repositories.PessoaFisicaRepository;
import br.com.nomeaplicativo.api.util.Constants;
import br.com.nomeaplicativo.api.util.Messages;
import br.com.nomeaplicativo.api.util.TemplateEmail;
import br.com.nomeaplicativo.api.util.genericrestcrud.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService extends GenericService<PessoaFisica, Long> {

    private final PessoaFisicaRepository repository;

    private final PerfilService perfilService;

    private final EnvioEmailService envioEmailService;

    private final TokenService tokenService;

    private final TemplateEmail templateEmail;

    private final Messages messages;

    private final NomeAplicativoApiProperties properties;

    @Override
    protected PessoaFisicaRepository getRepository() {
        return repository;
    }

    @Override
    public PessoaFisica save(final PessoaFisica pessoa) {
        try {
            return super.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw (e.getCause().getCause().getMessage().toLowerCase().contains("unique index")) ?
                    new NegocioException(messages.getMessage(Constants.MSG_USUARIO_JA_CADASTRADO), e) :
                    e;
        }
    }

    @Override
    protected PessoaFisica preSave(final PessoaFisica pessoa) {
        this.validarMenorDeIdade(pessoa);
        this.configurarPerfilPadraoQuandoInclusao(pessoa);
        return super.preSave(pessoa);
    }

    @Override
    protected PessoaFisica postSave(final PessoaFisica pessoa) {
        this.configurarTokenEnviarEmailAtivacaoQuandoInclusao(pessoa);
        return super.postSave(pessoa);
    }

    // FIXME - LEANDRO: AJUSTAR STRINGS MARRETADAS PARA LUGAR APROPRIADO, SEJA ARQUIVO DE CONSTANTES OU DE PROPRIEDADES
    private void enviarEmailAtivacao(final PessoaFisica pessoa, final Token token){

        final EmailDTO email = new EmailDTO();
        email.setAssunto("nomeaplicativo");
        email.setEmail(pessoa.getUsuario().getEmail());
        email.setNome(pessoa.getNome());

        String conteudo = templateEmail.getTemplate("confirmacaoEmail.html");
        conteudo = conteudo.replace("${nome}", email.getNome())
                           .replace("${userId}", pessoa.getUsuario().getId().toString())
                           .replace("${userToken}", token.getToken());

        email.setConteudo(conteudo);

        envioEmailService.enviarEmail(email);
    }

    private void configurarPerfilPadraoQuandoInclusao(final PessoaFisica pessoa) {
        if (Objects.isNull(pessoa.getUsuario().getPerfil())) {
            perfilService.findById(Constants.ID_PERFIL_PADRAO)
                    .ifPresent(p -> pessoa.getUsuario().setPerfil(p));
        }
    }

    private void configurarTokenEnviarEmailAtivacaoQuandoInclusao(final PessoaFisica pessoa) {
        if (SimNaoEnum.NAO.equals(pessoa.getUsuario().getStatus())) {
            final var token = tokenService.criarTokenAtivacaoConta(pessoa);
            enviarEmailAtivacao(pessoa, token);
        }
    }

    private void validarMenorDeIdade(final PessoaFisica pessoa) {
        if(pessoa.getDataNascimento().isAfter(LocalDate.now().minus(properties.getMaiorIdade(), ChronoUnit.YEARS))){
            throw new NegocioException(messages.getMessage(Constants.MSG_ERRO_USUARIO_MENOR_IDADE, properties.getMaiorIdade().toString()));
        }
    }
}
