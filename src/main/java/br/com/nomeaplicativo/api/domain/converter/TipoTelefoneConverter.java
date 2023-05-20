package br.com.nomeaplicativo.api.domain.converter;

import br.com.nomeaplicativo.api.domain.enumeration.TipoTelefoneEnum;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class TipoTelefoneConverter implements AttributeConverter<TipoTelefoneEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoTelefoneEnum attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public TipoTelefoneEnum convertToEntityAttribute(Character dbData) {
        return Arrays.stream(TipoTelefoneEnum.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
