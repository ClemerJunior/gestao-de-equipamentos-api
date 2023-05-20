package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.dtos.EmailDTO;
import br.com.nomeaplicativo.api.exceptions.EmailException;

import java.util.List;

public interface IEnvioEmailService {

    void enviarEmail(final EmailDTO destinatario) throws EmailException;
    void enviarEmailEmLote(final List<EmailDTO> destinatarios) throws EmailException;
}
