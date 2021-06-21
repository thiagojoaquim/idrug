package br.com.dcomp.ufs.idrug.external.models

import br.com.dcomp.ufs.idrug.domain.entities.Profile
import org.springframework.security.core.GrantedAuthority

class Role(private val profile: Profile) : GrantedAuthority {
    override fun getAuthority(): String {
        return profile.name
    }
}