package br.com.nomeaplicativo.api.domain.enumeration;


import lombok.Getter;

@Getter
public enum SimNaoEnum {
    SIM('S'),
    NAO('N');

    private Character codigo;

    SimNaoEnum(Character codigo) {
        this.codigo = codigo;
    }
}
