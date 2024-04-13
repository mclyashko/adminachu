package ru.mirea.adminachu.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByUserName

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            authorizeRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize("/actuator/**", permitAll)
                authorize("/error/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            httpBasic {}
        }

        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

@Component
class PostgresUserDetailsService(
    private val getUserByUserName: GetUserByUserName,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("User with null user name not found")
        }

        val user = getUserByUserName.execute(userName = User.Name(value = username))

        return object : UserDetails {
            override fun getAuthorities(): List<GrantedAuthority> {
                val adminAuthority = GrantedAuthority { "ROLE_ADMIN" }
                return listOf(adminAuthority)
            }

            override fun getPassword(): String = user.password.toStringValue()

            override fun getUsername(): String = user.userName.toStringValue()

            override fun isAccountNonExpired(): Boolean = true

            override fun isAccountNonLocked(): Boolean = true

            override fun isCredentialsNonExpired(): Boolean = true

            override fun isEnabled(): Boolean = user.activated.toBooleanValue()
        }
    }
}
