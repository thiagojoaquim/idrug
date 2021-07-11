package br.com.dcomp.ufs.idrug.api.exception

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.WebRequest

/**
 * Configuração da mensagem de exceção retornada.
 */
@Configuration
class ExcecaoConfig {

    private val DEFAULT_KEY_TIMESTAMP = "timestamp"
    private val DEFAULT_KEY_ERROR = "erro"
    private val DEFAULT_KEY_MESSAGE = "mensagem"
    private val DEFAULT_KEY_STATUS = "status"

    @Bean
    fun errorAttributes(): ErrorAttributes? {
        return object : DefaultErrorAttributes() {
            override fun getErrorAttributes(
                webRequest: WebRequest, includeStackTrace: Boolean
            ): Map<String, Any> {
                val defaultMap = super.getErrorAttributes(webRequest, includeStackTrace)
                val errorAttributes: MutableMap<String, Any> = LinkedHashMap()
                // Customize.
                // For eg: Only add the keys you want.
                errorAttributes["status"] = defaultMap[DEFAULT_KEY_STATUS]!!
                errorAttributes["message"] = defaultMap[DEFAULT_KEY_MESSAGE]!!
                errorAttributes["timestamp"] = defaultMap[DEFAULT_KEY_TIMESTAMP]!!
                errorAttributes["error"] = defaultMap[DEFAULT_KEY_ERROR]!!
                return errorAttributes
            }
        }
    }

}