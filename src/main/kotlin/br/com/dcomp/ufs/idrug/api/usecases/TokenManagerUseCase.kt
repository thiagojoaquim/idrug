package br.com.dcomp.ufs.idrug.api.usecases

import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import java.util.*


interface TokenManagerUseCase {
    /**
     * Resgata o email encriptado no token.
     * @param token Jason web token.
     * @return retorna uma string contendo o email do usuário.
     */
    fun getEmaildoToken(token : String) : String

    /**
     * Resgata uma informação específica incluída no token.
     * @param token Jason web token.
     * @param chave nome da informação que deve ser resgatado do token.
     * @return retorna informação desejada no json web token.
     */
    fun getClaimToken(token: String, chave : String) : String

    /**
     * Verifica se o token está expirado.
     * @param token Jason web token.
     * @return um booleano indicando se o token está expirado.
     */
    fun tokenExpirado(token: String) : Boolean

    /**
     * Gera um token a partir das informações do usuário e da data de expiração configurada na aplicação.
     * @param usuario Usuário que será associado ao token.
     * @return Jason web token.
     */
    fun gerarToken(usuario : Usuario) : String

    /**
     * Verifica se o token é valido descriptografando e verificando as claims.
     */
    fun validarToken(token: String, usuario: Usuario) : Boolean

    /**
     * Resgata a data de expiração do token.
     * @param token Jason Web Token.
     * @return Data de expiração do token
     */
    fun getDataExpricao(token: String) : Date

}