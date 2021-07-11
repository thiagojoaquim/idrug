package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.api.converter.IdrugConverter
import br.com.dcomp.ufs.idrug.api.models.AutenticarRequestDTO
import br.com.dcomp.ufs.idrug.api.models.AutenticarResponseDTO
import br.com.dcomp.ufs.idrug.api.usecases.AutenticacaoUseCase
import br.com.dcomp.ufs.idrug.api.usecases.TokenManagerUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/autenticacao"])
class AutenticacaoController(
    private val autenticaoService: AutenticacaoUseCase,
    private val tokenManager: TokenManagerUseCase,
    private val modelConverter: IdrugConverter
) {


    @PostMapping(value = ["/logar"])
    fun autenticar(@RequestBody @Valid autenticarRequestDTO: AutenticarRequestDTO): ResponseEntity<AutenticarResponseDTO> {
        val usuarioAutenticado = autenticaoService.autenticar(autenticarRequestDTO.email, autenticarRequestDTO.senha)
        val usuarioAutenticadoRetorno =
            modelConverter.converterObjeto(usuarioAutenticado, AutenticarResponseDTO::class.java)
        usuarioAutenticadoRetorno.token = tokenManager.gerarToken(usuarioAutenticado)
        return ResponseEntity(usuarioAutenticadoRetorno, HttpStatus.OK)
    }
}