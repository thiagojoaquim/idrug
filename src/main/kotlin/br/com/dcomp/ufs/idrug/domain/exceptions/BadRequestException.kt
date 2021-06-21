package br.com.dcomp.ufs.idrug.domain.exceptions

import br.com.dcomp.ufs.idrug.domain.messages.Error
import java.lang.RuntimeException

class BadRequestException(error: Error) : RuntimeException(error.message) {
}