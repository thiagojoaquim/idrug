package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.common.SHA256Converter
import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.Patient
import br.com.dcomp.ufs.idrug.domain.exceptions.BadRequestException
import br.com.dcomp.ufs.idrug.domain.messages.Error
import br.com.dcomp.ufs.idrug.domain.repositories.PatientRepository
import br.com.dcomp.ufs.idrug.domain.repositories.UserRepository
import br.com.dcomp.ufs.idrug.domain.usecases.PatientRegisterUseCase

@UseCase
class PatientRegisterUseCaseImpl(private val patientRepository: PatientRepository, private val userRepository: UserRepository) : PatientRegisterUseCase {

    override fun registerPatient(patient: Patient): Patient {
        var patientInBase = patientRepository.findByCpf(patient.cpf)
        if (patientInBase.isPresent) {
            throw BadRequestException(Error.CPF_ALREADY_REGISTRED)
        }
        var userWithThisEmail = userRepository.findByEmail(patient.user.email)
        if (userWithThisEmail.isPresent) {
            throw BadRequestException(Error.EMAIL_ALREADY_REGISTRED)
        }

        patient.user.password = SHA256Converter.convert(patient.user.password)
        return patientRepository.save(patient)
    }
}