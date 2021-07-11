package br.com.dcomp.ufs.idrug.domain.messages

enum class Erro(val message : String) {

    NAO_ENCONTRADO("Elemento não encontrado"),
    PACIENTE_JA_CADASTRADO("O paciente já foi registrado."),
    CPF_JA_REGISTRADO("O CPF já foi registrado."),
    CNPJ_JA_REGISTRADO("O CNPJ já foi registrado."),
    EMAIL_JA_REGISTRADO("O email já foi registrado."),
    CREDENCIAIS_INVALIDAS("As credenciais informadas são inválidas"),
    TOKEN_INVALIDO("O token informado é invalido"),
    ERRO_GENERICO("Erro no servidor."),
    PACIENTE_NAO_ENCONTRADO("O paciente não foi encontrado."),
    FARMACIA_NAO_ENCONTRADA("A farmácia não foi encontrado."),
    MEDICAMENTO_NAO_ENCONTRADO("O medicamento não foi encontrado."),
    INTERESSE_JA_CADASTRADO("O interesse nesse medicamento já foi registrado.")


}