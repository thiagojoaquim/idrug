package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Patient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PatientRepository : CrudRepository<Patient, Long> {
    fun findByCpf(cpf : String) : Optional<Patient>

}