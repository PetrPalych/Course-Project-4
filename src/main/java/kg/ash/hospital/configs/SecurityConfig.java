package kg.ash.hospital.configs;

import kg.ash.hospital.services.implementation.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(configurator -> configurator
                        .requestMatchers(HttpMethod.GET,
                                "/patient/add").hasAnyRole("REGISTRAR", "ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/patient/update**").hasAnyRole("REGISTRAR", "ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/patient/save").hasAnyRole("REGISTRAR", "ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/patient/delete**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/doctor/add").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/doctor/update**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/doctor/delete**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/doctor/save").hasRole("ADMIN")

                        .anyRequest().authenticated())

                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )

                .logout(LogoutConfigurer::permitAll)

                .exceptionHandling(configurator -> configurator.accessDeniedPage("/access-denied"))

                .userDetailsService(userDetailsService)

                .httpBasic();

        return httpSecurity.build();
    }

}
