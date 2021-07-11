package br.com.dcomp.ufs.idrug.domain.exceptions

import br.com.dcomp.ufs.idrug.domain.messages.Erro
import java.lang.RuntimeException

class UnauthorizedException(erro: Erro) : RuntimeException(erro.message) {

}
