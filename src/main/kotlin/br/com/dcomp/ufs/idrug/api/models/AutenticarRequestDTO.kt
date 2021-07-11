package br.com.dcomp.ufs.idrug.api.models

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * Estrutura de dados responsável por representar a requisição de Autenticação.
 */
data class AutenticarRequestDTO(
    @field:NotNull
    @field:NotEmpty
    val email: String,
    @field:NotNull
    @field:NotEmpty
    val senha: String
)