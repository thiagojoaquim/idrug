package br.com.dcomp.ufs.idrug.api.exception

import br.com.dcomp.ufs.idrug.api.models.MensagemErroDTO
import br.com.dcomp.ufs.idrug.domain.exceptions.BadRequestException
import br.com.dcomp.ufs.idrug.domain.exceptions.ForbiddenException
import br.com.dcomp.ufs.idrug.domain.exceptions.UnauthorizedException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import javassist.NotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    /**
     * Tratamento das exceções de tipo Not Found.
     */
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: Exception
    ): ResponseEntity<MensagemErroDTO> {
        val mensagem = criarMensagemRetorno(
            HttpStatus.NOT_FOUND,
            ex
        )
        return retornarResponse(mensagem)
    }

    /**
     * Tratamento das exceções de tipo Bad Request.
     */
    @ExceptionHandler(
        value = [BadRequestException::class]
    )
    fun badRequestExeptionHandler(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: Exception
    ): ResponseEntity<MensagemErroDTO> {
        val mensagem = criarMensagemRetorno(
            HttpStatus.BAD_REQUEST,
            ex
        )
        return retornarResponse(mensagem)
    }

    /**
     * Tratamento das exceções de tipo Unauthorized.
     */
    @ExceptionHandler(value = [UnauthorizedException::class, ForbiddenException::class])
    fun unauthorizedHandler(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: Exception
    ): ResponseEntity<MensagemErroDTO> {
        val mensagem = criarMensagemRetorno(
            HttpStatus.UNAUTHORIZED,
            ex
        )
        return retornarResponse(mensagem)
    }

    /**
     * Tratamento de exceções não mapeadas
     */
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(
        request: HttpServletRequest,
        response: HttpServletResponse, ex: Exception
    ): ResponseEntity<MensagemErroDTO> {
        val mensagem = criarMensagemRetorno(HttpStatus.INTERNAL_SERVER_ERROR, ex)
        return retornarResponse(mensagem)
    }

    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val retorno = criarMensagemRetorno(status, ex)
        return super.handleExceptionInternal(ex, retorno, headers, status, request)
    }

    private fun retornarResponse(mensagemErroDTO: MensagemErroDTO): ResponseEntity<MensagemErroDTO> {
        return ResponseEntity(mensagemErroDTO, HttpStatus.valueOf(mensagemErroDTO.status))
    }

    private fun criarMensagemRetorno(httpStatus: HttpStatus, ex: Exception): MensagemErroDTO {
        val mensagem = ex.message ?: Erro.ERRO_GENERICO.message
        return MensagemErroDTO(httpStatus.value(), OffsetDateTime.now(), ex::class.java.simpleName, mensagem, null)
    }
}