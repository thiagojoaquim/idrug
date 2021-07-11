package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.api.usecases.AutenticacaoUseCase
import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import br.com.dcomp.ufs.idrug.domain.exceptions.ForbiddenException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.UsuarioRepositorio
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

/**
 * Implementação da autenticação de um usuário no sistema.
 * @param usuarioRepositorio Abstração de acesso aos dados de usuário.
 */
@UseCase
class AutenticacaoImpl(
    private val usuarioRepositorio: UsuarioRepositorio
) :
    AutenticacaoUseCase {

    /**
     * Autenticação de um usuário.
     * @param email email do usuário.
     * @param senha do usuário.
     * @return Usuário cadastrado no sistema.
     */
    override fun autenticar(email: String, senha: String): Usuario {

        val usuarioRetornado = usuarioRepositorio.findByEmail(email)
        if (usuarioRetornado.isEmpty || ! (usuarioRetornado.get().senha.equals(encripitar(senha)))) {
            throw ForbiddenException(Erro.CREDENCIAIS_INVALIDAS)
        }
        return usuarioRetornado.get()
    }

    private fun encripitar(senha: String) = sha256(senha)

    fun sha256(entrada: String) = hashString("SHA-256", entrada)

    private fun hashString(algoritmo: String, entrada: String): String {
        val bytes = MessageDigest
            .getInstance(algoritmo)
            .digest(entrada.toByteArray())
        return DatatypeConverter.printHexBinary(bytes)
    }
}
