package com.entrenamiento.planverano.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Le indicamos a Spring que esto es un "Archivador"
public interface UserRepository extends JpaRepository<User, Long> {
    // La magia de Spring Data JPA:
    // Solo con escribir el nombre de este método, Spring ya sabe cómo
    // buscar un usuario por su campo "codigoRegistro".
    // Usamos Optional<User> por si no encuentra un usuario con ese código,
    // para no tener errores de tipo "null".
    Optional<User> findByCodigoRegistro(String codigoRegistro);
    Optional<User> findByEmail(String email);
}
