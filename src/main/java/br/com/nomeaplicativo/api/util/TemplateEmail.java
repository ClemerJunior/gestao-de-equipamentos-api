package br.com.nomeaplicativo.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class TemplateEmail {

    private static final String PATH_TEMPLATES = "/templates";

    public String getTemplate(final String templatePath) {
        final var template = this.getClass().getResourceAsStream(PATH_TEMPLATES + "/" + templatePath);
        final var sb = new StringBuilder();

        try (final var br = new BufferedReader(new InputStreamReader(template))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return sb.toString();
    }
}
