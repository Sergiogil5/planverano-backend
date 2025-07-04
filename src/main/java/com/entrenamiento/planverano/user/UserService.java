package com.entrenamiento.planverano.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import com.entrenamiento.planverano.training.ProgresoJugadorRepository;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // Método que busca todos los usuarios y filtra para devolver solo los jugadores
    public List<User> findAllPlayers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRol() == Rol.JUGADOR)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private final ProgresoJugadorRepository progresoRepository;

    public void eliminarJugadorPorId(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return;

        User user = userOpt.get();

        if (user.getRol() != Rol.JUGADOR) return; // Solo borrar si es jugador

        // Opcional: eliminar también su progreso
        progresoRepository.deleteByUsuarioId(id);

        // Finalmente, eliminar el usuario
        userRepository.deleteById(id);
    }

}