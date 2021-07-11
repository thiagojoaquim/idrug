package br.com.dcomp.ufs.idrug.domain.usecases

import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import java.time.LocalDate

interface DisponibilizarMedicamentoUseCase {
    fun disponibilizar(idFarmacia: Long, idMedicamento: Long, quantidade : Int): MedicamentoDisponibilidade
}