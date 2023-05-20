package br.com.nomeaplicativo.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum SexoEnum {
    MASCULINO('M'),
    FEMININO('F');

    private Character codigo;

    SexoEnum(Character codigo) {
        this.codigo = codigo;
    }
}
