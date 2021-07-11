package br.com.dcomp.ufs.idrug.external.models

import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl( val usuario: Usuario) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(Role(usuario.perfil!!))
    }

    override fun getPassword(): String {
        return usuario.senha
    }

    override fun getUsername(): String {
        return usuario.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}