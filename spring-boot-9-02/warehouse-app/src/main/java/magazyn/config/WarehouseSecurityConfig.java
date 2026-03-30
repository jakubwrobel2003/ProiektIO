package magazyn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Aktywuje mechanizmy bezpieczeństwa [cite: 3, 120]
public class WarehouseSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Używamy otwartego tekstu do celów edukacyjnych [cite: 182, 534]
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Definiujemy dwóch użytkowników w pamięci [cite: 25, 184, 215, 243]
        UserDetails admin = User.withUsername("admin")
                .password("admin123")
                .roles("ADMIN") // Rola wymagana do POST [cite: 311, 455]
                .build();

        UserDetails user = User.withUsername("user")
                .password("user123")
                .roles("REGULAR")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // [cite: 196, 838]
                .authorizeHttpRequests(auth -> auth
                        // DODAJ TĘ LINIĘ: Zezwala na publiczny dostęp do Actuatora [cite: 456]
                        .requestMatchers("/actuator/**").permitAll()

                        // Twoje obecne reguły [cite: 35, 456]
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // [cite: 33, 227]

        return http.build();
    }
}