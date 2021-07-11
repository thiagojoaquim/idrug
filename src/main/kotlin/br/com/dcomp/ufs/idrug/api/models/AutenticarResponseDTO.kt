package br.com.dcomp.ufs.idrug.api.models

/**
 * Estrutura de dados responsável por representar o corpo da resposta da requisição de autenticação.
 */
data class AutenticarResponseDTO(
    val id : Long = 0,
    val perfilId : Long = 0,
    var token: String = ""
)