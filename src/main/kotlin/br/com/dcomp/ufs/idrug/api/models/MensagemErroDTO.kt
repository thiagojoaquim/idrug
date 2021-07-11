package br.com.dcomp.ufs.idrug.api.models
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.OffsetDateTime

/**
 * Representações das mensagens de erro da API.
 */
data class Objeto(val nome : String, val mensagemUsuario : String)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MensagemErroDTO(
    val status: Int,
    val timestamp: OffsetDateTime,
    val erro : String,
    val mensagem : String,
    val objetosComErro : List<Objeto>?
)