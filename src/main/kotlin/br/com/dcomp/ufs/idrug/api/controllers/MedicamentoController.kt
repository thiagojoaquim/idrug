package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.domain.entities.Medicamento
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoRepositorio
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/medicamento"])
class MedicamentoController(private val medicamentoRepositorio: MedicamentoRepositorio) {

    @GetMapping(value = ["/resgatar"])
    fun getAll(@RequestParam(required = false, defaultValue = "0") inicio : Int,
               @RequestParam(required = false, defaultValue = "100") tamanho : Int) : ResponseEntity<Page<Medicamento>> {
        val paginas = PageRequest.of(inicio, tamanho)
        val retorno = medicamentoRepositorio.findAll(paginas)
        return ResponseEntity(retorno, HttpStatus.OK)
    }

    @GetMapping(value = ["/resgatar/{id}"])
    fun getDrug(@PathVariable id : Long) : ResponseEntity<Medicamento>{
        val retorno = medicamentoRepositorio.findById(id)
        if(retorno.isEmpty){
            throw RuntimeException()
        } else return ResponseEntity(retorno.get(), HttpStatus.OK)
    }
}