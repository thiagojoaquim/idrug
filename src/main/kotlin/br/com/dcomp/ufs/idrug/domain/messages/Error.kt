package br.com.dcomp.ufs.idrug.domain.messages

enum class Error(val message : String) {

    NOT_FOUND("Elemento não encontrado"),
    PATIENT_ALREADY_REGISTRED("O paciente já foi registrado."),
    CPF_ALREADY_REGISTRED("O CPF já foi registrado."),
    EMAIL_ALREADY_REGISTRED("O email já foi registrado."),
    INVALID_CREDENTIALS("As credenciais informadas são inválidas")

}