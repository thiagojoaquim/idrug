
import br.com.dcomp.ufs.idrug.api.usecases.TokenManagerUseCase
import br.com.dcomp.ufs.idrug.domain.exceptions.NotFoundException
import br.com.dcomp.ufs.idrug.domain.exceptions.UnauthorizedException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.external.models.UserDetailsImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
* Middleware responsável pela valdiação de segurança.
*/
class FiltroRequisicaoJwt(
    val tokenService: TokenManagerUseCase,
    val userDetailsService: UserDetailsService,
    val resolver: HandlerExceptionResolver
) :
    OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, p2: FilterChain) {
        try {
            validarRequisicao(request, response)
            p2.doFilter(request, response)
        } catch (ex: Exception) {
            resolver.resolveException(request, response, null, ex)
        }

    }

    private fun validarRequisicao(request: HttpServletRequest, response: HttpServletResponse) {
        val token: String? = request.getHeader("token")
        if(token == null)
            throw UnauthorizedException(Erro.TOKEN_INVALIDO)
        val email: String
        if (token != null && !tokenService.tokenExpirado(token!!)) {
            email = tokenService.getEmaildoToken(token!!)
        } else throw UnauthorizedException(Erro.TOKEN_INVALIDO)
        val detalhesDoUsuario = resgatarDetalhesUsuario(email)
        if (tokenService.validarToken(token = token!!, usuario = detalhesDoUsuario.usuario)) {
            val autToken = UsernamePasswordAuthenticationToken(detalhesDoUsuario.usuario, null, detalhesDoUsuario.authorities)
            SecurityContextHolder.getContext().authentication = autToken
        } else throw UnauthorizedException(Erro.TOKEN_INVALIDO)
    }

    private fun resgatarDetalhesUsuario(email: String): UserDetailsImpl {
        lateinit var detalhesDoUsuario: UserDetailsImpl
        try {
            detalhesDoUsuario = userDetailsService.loadUserByUsername(email) as UserDetailsImpl
        } catch (usuarioNaoEncontradoException: NotFoundException) {
            throw UnauthorizedException(Erro.TOKEN_INVALIDO)
        }
        return detalhesDoUsuario
    }
}