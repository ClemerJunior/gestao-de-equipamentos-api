package br.com.nomeaplicativo.api.util;

public class Constants {

    private Constants(){}

    /**
     * SESSÃO COM CONSTANTES DE VALIDAÇÃO
     * Criar os valores com {} para que sejam úteis também nos BeanValidations.
     */
    public static final String MSG_EMAIL_NOT_BLANK = "{email.notblank}";
    public static final String MSG_EMAIL_NOT_VALID = "{email.notvalid}";
    public static final String MSG_EMAIL_ERRO_ENVIO = "{email.erro.envio}";

    public static final String MSG_SENHA_NOT_BLANK = "{senha.notblank}";
    public static final String MSG_SENHA_LENGTH_NOT_VALID = "{senha.length.not.valid}";

    public static final String MSG_NOME_USUARIO_NOT_BLANK = "{nomeusuario.notblank}";

    public static final String MSG_CONTA_NAO_ATIVADA = "{conta.nao.ativada}";

    public static final String MSG_NOME_LENGTH = "{nome.maxlength}";
    public static final String MSG_NOME_NOT_BLANK = "{nome.notblank}";
    public static final String MSG_NOME_INVALIDO = "{nome.invalido}";

    public static final String MSG_SOBRENOME_MAX_LENGTH = "{sobrenome.maxlength}";
    public static final String MSG_SOBRENOME_NOT_BLANK = "{sobrenome.notblank}";
    public static final String MSG_SOBRENOME_INVALIDO = "{sobrenome.invalido}";

    public static final String MSG_DATA_NASCIMENTO_NOT_NULL = "{datanascimento.notnull}";

    public static final String MSG_USUARIO_JA_CADASTRADO = "{usuario.existente}";

    public static final String MSG_ERRO_LOGIN = "{login.invalido}";
    public static final String MSG_ERRO_USUARIO_ID_INVALIDO = "{usuario.id.invalido}";
    public static final String MSG_ERRO_USUARIO_MENOR_IDADE = "{usuario.menor.idade}";

    /**
     * CONSTANTES DE PADRÕES
     */
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String LOCAL_DATETIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss";
    public static final int TAMANHO_NOME = 40;
    public static final int TAMANHO_SOBRENOME = 100;
    public static final int TAMANHO_EMAIL = 200;
    public static final int TAMANHO_SENHA_TELA = 20;
    public static final int TAMANHO_SENHA_BANCO = 60;
    public static final int TAMANHO_TOKEN_BANCO = 128;
    public static final Long ID_PERFIL_PADRAO = 1L;


}
