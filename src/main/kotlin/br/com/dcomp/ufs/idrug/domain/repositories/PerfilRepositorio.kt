package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Perfil
import org.springframework.data.jpa.repository.JpaRepository

interface PerfilRepositorio : JpaRepository<Perfil, Long> {
}