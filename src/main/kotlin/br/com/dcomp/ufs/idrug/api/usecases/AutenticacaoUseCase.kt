package br.com.dcomp.ufs.idrug.api.usecases

import br.com.dcomp.ufs.idrug.domain.entities.Usuario

interface AutenticacaoUseCase {

    /**
     * Autentica um usuário cadastrado.
     *@param email Email de acesso do usuário
     * @param senha Senha de acesso do usuário.
     * @return retorna a representação do usuário no domínio.
     */
    fun autenticar(email : String, senha : String) : Usuario
}