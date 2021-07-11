package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.api.converter.IdrugConverter
import br.com.dcomp.ufs.idrug.api.models.RegistrarPacienteRequestDTO
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoInteresse
import br.com.dcomp.ufs.idrug.domain.entities.Paciente
import br.com.dcomp.ufs.idrug.domain.usecases.RegistrarInteresseUseCase
import br.com.dcomp.ufs.idrug.domain.usecases.RegistroPacienteUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/paciente"])
class PacienteController(
    private val registerPatientUseCase: RegistroPacienteUseCase,
    private val converter: IdrugConverter,
    private val registrarInteresseUseCase: RegistrarInteresseUseCase
) {

    @PostMapping(value = ["/registrar"])
    fun registrarPaciente(@Valid @RequestBody registerPatientDTO: RegistrarPacienteRequestDTO): ResponseEntity<RegistrarPacienteRequestDTO> {
        val patientRegistred =
            registerPatientUseCase.registrar(converter.converterObjeto(registerPatientDTO, Paciente::class.java))
        patientRegistred.usuario.senha = ""
        return ResponseEntity(converter.converterObjeto(patientRegistred, RegistrarPacienteRequestDTO::class.java), HttpStatus.CREATED)
    }

    @PostMapping(value = ["/{id}/medicamento/{id}/interesse"])
    fun registrarInteresse(id : Long, medicamentoId:Long) : ResponseEntity<MedicamentoInteresse> {
        return ResponseEntity(registrarInteresseUseCase.registrar(id, medicamentoId), HttpStatus.CREATED)
    }
}