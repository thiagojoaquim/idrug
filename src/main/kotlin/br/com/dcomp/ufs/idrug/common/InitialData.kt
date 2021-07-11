package br.com.dcomp.ufs.idrug.common

import br.com.dcomp.ufs.idrug.domain.entities.Perfil
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import br.com.dcomp.ufs.idrug.domain.repositories.PerfilRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.UsuarioRepositorio
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent

class InitialData(val usuarioRepositorio: UsuarioRepositorio, val profileRepository: PerfilRepositorio) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        createUser()
    }

    fun createUser() {
        var profile = Perfil(nome = "Admin", descricao = "Administrator of app", id = 0)
        profile = this.profileRepository.save(profile)
        val user = Usuario(nome = "thiago", email = "thiagojoaquim03@gmail.com", senha = "teste", perfil = profile, id = 0)
        usuarioRepositorio.save(user)
    }

}