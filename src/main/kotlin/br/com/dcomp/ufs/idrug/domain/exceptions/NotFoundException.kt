package br.com.dcomp.ufs.idrug.domain.exceptions

import br.com.dcomp.ufs.idrug.domain.messages.Erro
import java.lang.RuntimeException

class NotFoundException(erro : Erro) : RuntimeException(erro.message) {
}