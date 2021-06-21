package br.com.dcomp.ufs.idrug.common

import br.com.dcomp.ufs.idrug.domain.entities.Profile
import br.com.dcomp.ufs.idrug.domain.entities.User
import br.com.dcomp.ufs.idrug.domain.repositories.ProfileRepository
import br.com.dcomp.ufs.idrug.domain.repositories.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent

class InitialData(val userRepository: UserRepository, val profileRepository: ProfileRepository) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        createUser()
    }

    fun createUser() {
        var profile = Profile(name = "Admin", description = "Administrator of app", id = 0)
        profile = this.profileRepository.save(profile)
        val user = User(name = "thiago", email = "thiagojoaquim03@gmail.com", password = "teste", profile = profile, id = 0)
        userRepository.save(user)
    }

}