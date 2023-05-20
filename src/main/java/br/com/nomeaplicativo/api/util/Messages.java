package br.com.nomeaplicativo.api.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class Messages {

    private final MessageSource messageSource;

    public String getMessage(final String codigo) {
        return messageSource.getMessage(codigo.replaceAll("\\{|\\}",""),null, Locale.getDefault());
    }

    public String getMessage(final String codigo, String... parametros) {
        return messageSource.getMessage(codigo.replaceAll("\\{|\\}",""), parametros, Locale.getDefault());
    }
}
