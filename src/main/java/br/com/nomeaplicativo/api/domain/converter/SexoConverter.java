package br.com.nomeaplicativo.api.domain.converter;

import br.com.nomeaplicativo.api.domain.enumeration.SexoEnum;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class SexoConverter implements AttributeConverter<SexoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(SexoEnum attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public SexoEnum convertToEntityAttribute(Character dbData) {
        return Arrays.stream(SexoEnum.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
