package br.com.dcomp.ufs.idrug.domain.usecases

import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.DrugStore
import br.com.dcomp.ufs.idrug.domain.entities.Patient

@UseCase
interface PatientRegisterUseCase {

    fun registerPatient(patient: Patient) : Patient
}