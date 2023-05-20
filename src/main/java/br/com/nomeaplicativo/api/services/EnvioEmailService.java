package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.dtos.EmailDTO;
import br.com.nomeaplicativo.api.exceptions.EmailException;
import br.com.nomeaplicativo.api.util.Messages;
import br.com.nomeaplicativo.api.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvioEmailService implements IEnvioEmailService {

    private final JavaMailSender javaMailSender;

    private final Messages messages;

    @Override
    public void enviarEmail(EmailDTO email) throws EmailException {
        enviar(email);
    }

    @Override
    public void enviarEmailEmLote(List<EmailDTO> emails) {
        try {
            for (EmailDTO email : emails) {
                enviar(email);
            }
        } catch (EmailException me) {
            log.error(me.getMessage(), me);
        }
    }

    private void enviar(EmailDTO email) throws EmailException {
        try {
            javaMailSender.send(montarEmail(email));
        } catch (MailException me) {
            String msg = messages.getMessage(Constants.MSG_EMAIL_ERRO_ENVIO, email.getEmail());
            throw new EmailException(msg, me);
        }
    }

    private MimeMessagePreparator montarEmail(EmailDTO email) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email.getEmail());
            messageHelper.setSubject(email.getAssunto());
            messageHelper.setText(email.getConteudo(), true);
        };
    }
}
