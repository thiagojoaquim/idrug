package br.com.dcomp.ufs.idrug.domain.usecases

import br.com.dcomp.ufs.idrug.domain.entities.Paciente


interface RegistroPacienteUseCase {

    fun registrar(paciente: Paciente) : Paciente
}