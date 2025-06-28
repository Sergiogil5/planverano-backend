package com.entrenamiento.planverano.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}