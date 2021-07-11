package br.com.dcomp.ufs.idrug.api.models

import org.hibernate.validator.constraints.br.CNPJ
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegistroFarmaciaRequestDTO(
    @field:NotBlank(message = "O nome é obrigatório.")
    val nome : String,
    @field:CNPJ(message = "O CNPJ é inválido.")
    val cnpj: String,
    @field:NotBlank(message = "O email é obrigatório.")
    @field:Email(message = "O email é inválido.")
    val email : String,
    @field:NotBlank(message = "A senha é obrigatória.")
    val senha : String
)