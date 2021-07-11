package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Farmacia
import br.com.dcomp.ufs.idrug.domain.entities.Paciente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FarmaciaRepositorio : JpaRepository<Farmacia, Long> {
    fun findByCnpj(cnpj : String) : Optional<Farmacia>
}