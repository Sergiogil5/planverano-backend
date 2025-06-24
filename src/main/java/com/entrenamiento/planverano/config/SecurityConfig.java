package com.entrenamiento.planverano.config;

import com.entrenamiento.planverano.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indica que esta clase contiene configuración de Spring
public class SecurityConfig {

    // 1. Bean para decir a Spring CÓMO encontrar a nuestros usuarios.
    //    Le pasamos nuestro UserRepository para que busque por email.
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));
    }

    // 2. Bean para el encriptador de contraseñas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. Bean para el "proveedor de autenticación".
    //    Este es el componente que une el UserDetailsService (paso 1) y el PasswordEncoder (paso 2).
    //    Es el que realmente hace el trabajo de comparar la contraseña.
    @Bean
    public AuthenticationProvider authenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(userRepository));
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // 4. Bean para el "gestor de autenticación" que usaremos en el AuthService.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 5. Bean para la cadena de filtros de seguridad (las reglas de acceso).
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/registro/**").permitAll() // Rutas públicas
                        .anyRequest().authenticated() // TODAS las demás rutas requieren autenticación
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Le decimos a Spring que no cree sesiones, usaremos JWT
                .authenticationProvider(authenticationProvider) // Configuramos nuestro proveedor
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Añadimos nuestro filtro JWT antes del filtro de username/password

        return http.build();
    }
}