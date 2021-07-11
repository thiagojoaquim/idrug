package br.com.dcomp.ufs.idrug.external.seguranca

import FiltroRequisicaoJwt
import br.com.dcomp.ufs.idrug.api.usecases.TokenManagerUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.HandlerExceptionResolver

/**
 * Classe responsável pela implementação da configuração de segurança.
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    val userDetailsService: UserDetailsService,
    val jwtAuthenticationEntryPoint: PontoDeEntradaAutenticacaoJwt,
    val tokenService: TokenManagerUseCase,
    val handlerExceptionResolver: HandlerExceptionResolver
) : WebSecurityConfigurerAdapter() {

    private val SWAGGER_AUTH_WHITELIST = arrayOf(
        "/v2/api-docs",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/webjars/**"
    )

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: HttpSecurity?) {

        web!!.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest()
            .permitAll()
            .and().exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        web.addFilterBefore(FiltroRequisicaoJwt(tokenService, userDetailsService, handlerExceptionResolver), UsernamePasswordAuthenticationFilter::class.java)

    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers(HttpMethod.POST, "/autenticacao/logar")
            //.antMatchers(HttpMethod.POST, "/api/autenticacao/logar")
            .antMatchers(HttpMethod.POST, "/paciente/registrar")
            .antMatchers(HttpMethod.GET, *SWAGGER_AUTH_WHITELIST)
    }


}