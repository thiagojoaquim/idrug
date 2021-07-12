package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Medicamento
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MedicamentoDisponibilidadeRepositorio : JpaRepository<MedicamentoDisponibilidade, Long> {

    fun findByMedicamentoIdAndFarmaciaId(medicamentoId : Long, farmaciaId : Long) : Optional<MedicamentoDisponibilidade>
    fun findByMedicamentoId(medicamentoId: Long) : Optional<MedicamentoDisponibilidade>
}