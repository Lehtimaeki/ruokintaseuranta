package kissat.ruokintaseuranta.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/kirjaudu", "/kirjauduulos").permitAll()
                .requestMatchers("/ruokinnat").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/ruokinnat/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/kirjaudu")
                .defaultSuccessUrl("/ruokinnat", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user = User.withUsername("user")
                               .password(encoder.encode("user"))
                               .roles("USER")
                               .build();

        UserDetails admin = User.withUsername("admin")
                                .password(encoder.encode("admin"))
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(List.of(user, admin));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


