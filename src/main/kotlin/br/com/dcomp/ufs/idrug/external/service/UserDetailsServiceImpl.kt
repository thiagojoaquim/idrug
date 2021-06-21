package br.com.dcomp.ufs.idrug.external.service

import br.com.dcomp.ufs.idrug.domain.exceptions.NotFoundException
import br.com.dcomp.ufs.idrug.domain.messages.Error
import br.com.dcomp.ufs.idrug.domain.repositories.UserRepository
import br.com.dcomp.ufs.idrug.external.models.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        var user = userRepository.findByEmail(username!!)
        if (user.isEmpty) {
            throw NotFoundException(Error.INVALID_CREDENTIALS)
        }
        return UserDetailsImpl(user.get())
    }
}