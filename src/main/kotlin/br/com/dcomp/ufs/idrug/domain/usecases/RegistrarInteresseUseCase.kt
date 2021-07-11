package br.com.dcomp.ufs.idrug.domain.usecases

import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoInteresse


interface RegistrarInteresseUseCase {
    fun registrar(idPaciente : Long, idMedicamento : Long) : MedicamentoInteresse
}