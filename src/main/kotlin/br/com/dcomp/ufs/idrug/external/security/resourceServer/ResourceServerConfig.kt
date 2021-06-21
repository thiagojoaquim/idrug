package br.com.dcomp.ufs.idrug.external.security.resourceServer

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources!!.resourceId("restservice")
    }

    override fun configure(http: HttpSecurity?) {
        http!!.logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .and()
            .authorizeRequests()
            .anyRequest()
            .fullyAuthenticated()

    }
}