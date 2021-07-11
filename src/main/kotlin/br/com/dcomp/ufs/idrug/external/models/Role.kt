package br.com.dcomp.ufs.idrug.external.models

import br.com.dcomp.ufs.idrug.domain.entities.Perfil
import org.springframework.security.core.GrantedAuthority

class Role(private val perfil: Perfil) : GrantedAuthority {
    override fun getAuthority(): String {
        return perfil.nome!!
    }
}