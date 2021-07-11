package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.api.converter.IdrugConverter
import br.com.dcomp.ufs.idrug.api.models.DisponibilizarMedicamentoRequestDTO
import br.com.dcomp.ufs.idrug.api.models.RegistrarPacienteRequestDTO
import br.com.dcomp.ufs.idrug.api.models.RegistroFarmaciaRequestDTO
import br.com.dcomp.ufs.idrug.domain.entities.Farmacia
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import br.com.dcomp.ufs.idrug.domain.usecases.DisponibilizarMedicamentoUseCase
import br.com.dcomp.ufs.idrug.domain.usecases.RegistroFarmaciaUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class FarmaciaController(
    private val registroFarmaciaUseCase: RegistroFarmaciaUseCase,
    private val disponibilizarMedicamentoUseCase: DisponibilizarMedicamentoUseCase,
    private val converter: IdrugConverter
) {

    @PostMapping(value = ["/registrar"])
    fun registerPatient(@Valid @RequestBody registroFarmaciaRequestDTO: RegistroFarmaciaRequestDTO): ResponseEntity<RegistrarPacienteRequestDTO> {
        val farmaciaRegistrada =
            registroFarmaciaUseCase.registrar(
                converter.converterObjeto(
                    registroFarmaciaRequestDTO,
                    Farmacia::class.java
                )
            )
        farmaciaRegistrada.usuario.senha = ""
        return ResponseEntity(
            converter.converterObjeto(farmaciaRegistrada, RegistrarPacienteRequestDTO::class.java),
            HttpStatus.CREATED
        )
    }

    @PostMapping(value = ["/{id}/medicamento{idMedicamento}/disponibilizar"])
    fun disponibilizarMedicamento(
        @PathVariable id: Long,
        @PathVariable idMedicamento: Long,
        @RequestBody disponibilizarMedicamentoRequestDTO: DisponibilizarMedicamentoRequestDTO
    ): ResponseEntity<MedicamentoDisponibilidade> {
        return ResponseEntity(
            disponibilizarMedicamentoUseCase.disponibilizar(
                id,
                idMedicamento,
                disponibilizarMedicamentoRequestDTO.quantidade
            ), HttpStatus.CREATED
        )
    }
}