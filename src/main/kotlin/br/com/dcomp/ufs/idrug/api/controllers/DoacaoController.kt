package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.domain.entities.Doacao
import br.com.dcomp.ufs.idrug.domain.entities.Medicamento
import br.com.dcomp.ufs.idrug.domain.repositories.DoacaoRepositorio
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DoacaoController(private val doacaoRepositorio: DoacaoRepositorio) {

    @GetMapping(value = ["/resgatar"])
    fun resgatarTodos(@RequestParam(required = false, defaultValue = "0") inicio : Int,
                      @RequestParam(required = false, defaultValue = "100") tamanho : Int) : ResponseEntity<Page<Doacao>> {
        val paginas = PageRequest.of(inicio, tamanho)
        val retorno = doacaoRepositorio.findAll(paginas)
        return ResponseEntity(retorno, HttpStatus.OK)
    }

    @GetMapping(value = ["/resgatar/{id}"])
    fun resgatarDoacao(@PathVariable id : Long) : ResponseEntity<Doacao> {
        val retorno = doacaoRepositorio.findById(id)
        if(retorno.isEmpty){
            throw RuntimeException()
        } else return ResponseEntity(retorno.get(), HttpStatus.OK)
    }
}