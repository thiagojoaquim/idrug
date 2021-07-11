package br.com.dcomp.ufs.idrug.api.models

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RegistrarPacienteRequestDTO (
    @field:NotBlank(message = "O nome é obrigatório.")
    val nome : String,
    @field:NotNull(message = "A data de nascimento é obrigatória.")
    @JsonSerialize(using = LocalDateSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val dataNascimento : LocalDate,
    @field:CPF(message = "O cpf é inválido")
    val cpf: String,
    @field:NotBlank(message = "O email é obrigatório.")
    @field:Email(message = "O email é inválido.")
    val email : String,
    @field:NotBlank(message = "A senha é obrigatória.")
    val senha : String
)