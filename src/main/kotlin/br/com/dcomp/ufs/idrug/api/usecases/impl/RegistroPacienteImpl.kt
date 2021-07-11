package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.common.SHA256Converter
import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.Paciente
import br.com.dcomp.ufs.idrug.domain.entities.Perfil
import br.com.dcomp.ufs.idrug.domain.exceptions.BadRequestException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.PacienteRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.UsuarioRepositorio
import br.com.dcomp.ufs.idrug.domain.usecases.RegistroPacienteUseCase

@UseCase
class RegistroPacienteImpl(val patientRepository: PacienteRepositorio, val usuarioRepositorio: UsuarioRepositorio) : RegistroPacienteUseCase {

    override fun registrar(paciente: Paciente): Paciente {
        var patientInBase = patientRepository.findByCpf(paciente.cpf)
        if (patientInBase.isPresent) {
            throw BadRequestException(Erro.CPF_JA_REGISTRADO)
        }
        var userWithThisEmail = usuarioRepositorio.findByEmail(paciente.usuario.email)
        if (userWithThisEmail.isPresent) {
            throw BadRequestException(Erro.EMAIL_JA_REGISTRADO)
        }
        paciente.usuario.perfil = Perfil(id = 1)
        paciente.usuario.senha = SHA256Converter.convert(paciente.usuario.senha)
        return patientRepository.save(paciente)
    }
}