package br.com.nomeaplicativo.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum TipoTelefoneEnum {

    CELULAR('C'),
    FIXO('F');

    private Character codigo;

    TipoTelefoneEnum(Character codigo){
        this.codigo = codigo;
    }
}
