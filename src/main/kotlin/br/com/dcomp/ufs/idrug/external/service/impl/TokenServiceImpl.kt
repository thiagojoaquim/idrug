package br.com.dcomp.ufs.idrug.external.service.impl

import br.com.dcomp.ufs.idrug.api.usecases.TokenManagerUseCase
import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import br.com.dcomp.ufs.idrug.domain.exceptions.ForbiddenException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*
import kotlin.collections.HashMap

@UseCase
class TokenServiceImpl : TokenManagerUseCase {
    @Value(value = "\${jwt.secret}")
    lateinit var secret: String

    @Value(value = "\${jwt.validade}")
    var validade: Long = 0

    override fun getEmaildoToken(token: String): String {
        return getAllClaims(token).subject
    }

    override fun getClaimToken(token: String, chave: String): String {
        val claims = getAllClaims(token)
        return claims.get(chave, String::class.java)
    }

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    override fun tokenExpirado(token: String): Boolean {
        try {
            return getDataExpricao(token).before(Date())
        } catch (ex: ExpiredJwtException) {
            throw ForbiddenException(Erro.CREDENCIAIS_INVALIDAS)
        }
    }

    override fun gerarToken(usuario: Usuario): String {
        val claims = HashMap<String, Any>()
        claims["nome"] = usuario.nome
        claims["email"] = usuario.email
        claims["id"] = usuario.id!!
        return doGerarToken(claims, usuario.email)
    }

    private fun doGerarToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + validade))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    override fun validarToken(token: String, usuario: Usuario): Boolean {
        return (usuario.email == getEmaildoToken(token) && !tokenExpirado(token))
    }

    override fun getDataExpricao(token: String): Date {
        return getAllClaims(token).expiration
    }
}