package com.entrenamiento.planverano.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 1. Rutas Públicas para login y registro.
                        .requestMatchers("/api/auth/**", "/api/registro/**").permitAll()
                        // 2. GET /api/users/me → cualquier usuario logueado
                        .requestMatchers("/api/users/me").authenticated()
                        // Solo JUGADOR puede acceder a su propio progreso
                        .requestMatchers("/api/progreso/mis-progresos", "/api/progreso/mi-pausa")
                        .hasAuthority("JUGADOR")
                        // 3. Rutas del Panel de Administrador (solo para ENTRENADOR).
                        //    Usa hasAuthority para una comparación directa.
                        .requestMatchers("/api/users/**", "/api/progreso/jugador/**").hasAuthority("ENTRENADOR")
                        // 4. El resto de las rutas son para CUALQUIER usuario autenticado.
                        //    Esto incluye explícitamente /api/progreso/mis-progresos
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean de CORS (sin cambios)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite peticiones desde cualquier origen (o cámbialo por tu dominio exacto)
        configuration.setAllowedOriginPatterns(List.of("*"));

        // Métodos que permites
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        // Cabeceras que permites, incluida Authorization
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        configuration.setAllowCredentials(true);  // si necesitas enviar cookies o auth

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}