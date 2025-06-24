package com.entrenamiento.planverano.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // --- BLOQUE AÑADIDO ---
        // Si la petición es a una ruta pública (registro o login),
        // no hacemos nada y pasamos al siguiente filtro inmediatamente.
        final String servletPath = request.getServletPath();
        if (servletPath.contains("/api/auth") || servletPath.contains("/api/registro")) {
            filterChain.doFilter(request, response);
            return; // ¡Importante! El 'return' detiene la ejecución de este filtro aquí.
        }
        // --- FIN DEL BLOQUE AÑADIDO ---


        // 1. Obtener la cabecera "Authorization" de la petición
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 2. Comprobar si la cabecera existe y tiene el formato correcto ("Bearer token")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Si no hay token, pasamos al siguiente filtro
            return;
        }

        // 3. Extraer el token (quitando el prefijo "Bearer ")
        jwt = authHeader.substring(7);
        userEmail = jwtService.getUsernameFromToken(jwt); // Extraemos el email del token

        // 4. Si tenemos email y el usuario no está ya autenticado en esta sesión
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargamos los detalles del usuario desde la base de datos
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // Validamos el token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Si el token es válido, creamos un objeto de autenticación
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // No necesitamos credenciales
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Y lo guardamos en el contexto de seguridad. ¡El usuario está autenticado!
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // 5. Pasamos la petición al siguiente filtro de la cadena
        filterChain.doFilter(request, response);
    }
}