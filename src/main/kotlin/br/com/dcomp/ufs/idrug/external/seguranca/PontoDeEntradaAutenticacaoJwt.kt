package br.com.dcomp.ufs.idrug.external.seguranca

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class PontoDeEntradaAutenticacaoJwt : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, excecao: AuthenticationException?) {
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado")
    }

}
