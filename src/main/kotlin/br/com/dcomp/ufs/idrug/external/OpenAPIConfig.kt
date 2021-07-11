package br.com.dcomp.ufs.idrug.external



import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun api() = OpenAPI()
        .info(metadata())

    private fun metadata() = Info()
        .title("Idrug API")
        .description(
            "API desenvolvida na disciplina de Engenharia de Software"
        )
        .version("v1")
}

