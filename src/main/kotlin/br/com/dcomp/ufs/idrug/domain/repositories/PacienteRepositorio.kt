package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Paciente
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PacienteRepositorio : CrudRepository<Paciente, Long> {
    fun findByCpf(cpf : String) : Optional<Paciente>

}