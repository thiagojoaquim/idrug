package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepositorio : JpaRepository<Usuario, Long> {

    fun findByEmail(email : String) : Optional<Usuario>
}