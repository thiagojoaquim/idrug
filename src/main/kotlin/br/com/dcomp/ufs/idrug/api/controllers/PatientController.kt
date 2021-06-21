package br.com.dcomp.ufs.idrug.api.controllers

import br.com.dcomp.ufs.idrug.api.converter.IdrugConverter
import br.com.dcomp.ufs.idrug.api.models.PatientDTO
import br.com.dcomp.ufs.idrug.api.models.RegisterPatientDTO
import br.com.dcomp.ufs.idrug.domain.entities.Patient
import br.com.dcomp.ufs.idrug.domain.usecases.PatientRegisterUseCase
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/patient"])
class PatientController(
    private val registerPatientUseCase: PatientRegisterUseCase,
    private val converter: IdrugConverter
) {

    fun registerPatient(@RequestBody @Valid registerPatientDTO: RegisterPatientDTO): PatientDTO {
        val patientRegistred =
            registerPatientUseCase.registerPatient(converter.converterObjeto(registerPatientDTO, Patient::class.java))
        return converter.converterObjeto(patientRegistred, PatientDTO::class.java)
    }
}