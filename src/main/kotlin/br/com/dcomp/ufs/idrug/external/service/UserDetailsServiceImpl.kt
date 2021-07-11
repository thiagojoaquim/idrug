package br.com.dcomp.ufs.idrug.external.service

import br.com.dcomp.ufs.idrug.domain.exceptions.NotFoundException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.UsuarioRepositorio
import br.com.dcomp.ufs.idrug.external.models.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val usuarioRepositorio: UsuarioRepositorio) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        var user = usuarioRepositorio.findByEmail(username!!)
        if (user.isEmpty) {
            throw NotFoundException(Erro.CREDENCIAIS_INVALIDAS)
        }
        return UserDetailsImpl(user.get())
    }
}