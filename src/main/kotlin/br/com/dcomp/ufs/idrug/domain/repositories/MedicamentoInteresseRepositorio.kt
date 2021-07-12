package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoInteresse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MedicamentoInteresseRepositorio : JpaRepository<MedicamentoInteresse, Long>{
    fun findByMedicamentoIdAndPacienteId(medicamentoId : Long, pacienteId : Long) : Optional<MedicamentoInteresse>
    fun findByMedicamentoId(medicamentoId : Long) : Optional<MedicamentoInteresse>
}