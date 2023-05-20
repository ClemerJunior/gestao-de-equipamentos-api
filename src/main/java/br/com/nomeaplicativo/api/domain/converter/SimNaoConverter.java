package br.com.nomeaplicativo.api.domain.converter;

import br.com.nomeaplicativo.api.domain.enumeration.SimNaoEnum;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class SimNaoConverter implements AttributeConverter<SimNaoEnum, Character> {
    @Override
    public Character convertToDatabaseColumn(SimNaoEnum attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public SimNaoEnum convertToEntityAttribute(Character dbData) {
        return Arrays.stream(SimNaoEnum.values())
                .filter(e -> e.getCodigo().equals(dbData))
                .findFirst().orElse(null);
    }
}
