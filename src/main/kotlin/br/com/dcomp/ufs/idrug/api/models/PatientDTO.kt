package br.com.dcomp.ufs.idrug.api.models

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PatientDTO (
    @NotBlank(message = "O nome é obrigatório.")
    val name : String,
    @NotNull(message = "A data de nascimento é obrigatória.")
    @JsonSerialize(using = LocalDateSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val birthDate : LocalDate,
    @CPF(message = "O cpf é inválido")
    val cpf: String,
    @NotBlank(message = "O email é obrigatório.")
    val email : String
)