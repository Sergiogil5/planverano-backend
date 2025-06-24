package com.entrenamiento.planverano.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistroService {

    // --- El mapa de códigos que ya tenías ---
    private static final Map<String, String> CODIGOS_JUGADORES;

    static {
        // ... (AQUÍ VA TU LISTA COMPLETA DE JUGADORES)
        CODIGOS_JUGADORES = new HashMap<>();
        CODIGOS_JUGADORES.put("GORA8392", "RUBEN APARICIO");
        CODIGOS_JUGADORES.put("GORD1047", "RUSU DENIS");
        CODIGOS_JUGADORES.put("GOLA3819", "LORIEN ARNAL");
        CODIGOS_JUGADORES.put("GOMT9254", "MOHAMMED TAGHILTI");
        CODIGOS_JUGADORES.put("GOJQ6118", "JORGE QUATRONNE P");
        CODIGOS_JUGADORES.put("GOLA2845", "LUCAS AROYO");
        CODIGOS_JUGADORES.put("GOPB7402", "PABLO BERDEJO");
        CODIGOS_JUGADORES.put("GOJC5936", "JESUS CLAVERIA");
        CODIGOS_JUGADORES.put("GODF1773", "DARIO FELIPE");
        CODIGOS_JUGADORES.put("GOJC8460", "JAVIER COLELL");
        CODIGOS_JUGADORES.put("GOPG4921", "PABLO GARCIA");
        CODIGOS_JUGADORES.put("GONG3305", "NICOLAS GARCIA");
        CODIGOS_JUGADORES.put("GOHP9588", "HUGO PRADOS");
        CODIGOS_JUGADORES.put("GODG6241", "DIEGO GELABERT");
        CODIGOS_JUGADORES.put("GOPL1897", "PEDRO LATORRE");
        CODIGOS_JUGADORES.put("GOBG7533", "BRUNO GOMEZ");
        CODIGOS_JUGADORES.put("GOJM5519", "JORGE MARTIN");
        CODIGOS_JUGADORES.put("GOMJ2964", "MARIO JOSE");
        CODIGOS_JUGADORES.put("GOAC4170", "ALEX COWASZ");
        CODIGOS_JUGADORES.put("GOSA6733", "SALIU");
        CODIGOS_JUGADORES.put("GOTI9201", "TEODOR IVANOV");
        CODIGOS_JUGADORES.put("GODE4857", "DANIEL ESPEJO");
        CODIGOS_JUGADORES.put("GOHL1123", "HUGO LOBERA P");
        CODIGOS_JUGADORES.put("GODM7698", "DANIEL MURCIA");
        CODIGOS_JUGADORES.put("GOKI3448", "KILIAN");
        CODIGOS_JUGADORES.put("GOJU8025", "JULEN");
        CODIGOS_JUGADORES.put("GOMO5381", "MOHA");
        CODIGOS_JUGADORES.put("GOCR9874", "CRISTIAN");
        CODIGOS_JUGADORES.put("GOEN6509", "ERIC NAVARRO");
        CODIGOS_JUGADORES.put("GOUR2216", "URIEL REYES");
        CODIGOS_JUGADORES.put("GOGS8842", "GEORGE STOICA");
        CODIGOS_JUGADORES.put("GOPL3177", "PABLO LACOMA");
        CODIGOS_JUGADORES.put("GOJM9403", "JAVI MIRAMON");
        CODIGOS_JUGADORES.put("GOVP6650", "VICTOR PARDOS");
        CODIGOS_JUGADORES.put("GOLG2086", "LUCAS GAUDES");
        CODIGOS_JUGADORES.put("GOME5714", "MARCOS EZQUERRA");
        CODIGOS_JUGADORES.put("GOMI1390", "MIGUEL");
        CODIGOS_JUGADORES.put("GOAT7829", "ALI TAYYAB");
        CODIGOS_JUGADORES.put("GODC4551", "DAVID CIOCAN");
        CODIGOS_JUGADORES.put("GORD8138", "RAUL DOMITRASCU");
        CODIGOS_JUGADORES.put("GORF2815", "ROBERTO FERNANDO");
        CODIGOS_JUGADORES.put("GOEF5472", "ENRIQUE FLORIA");
        CODIGOS_JUGADORES.put("GOMT1907", "MIGUEL TORNERO");
        CODIGOS_JUGADORES.put("GODM6394", "DIEGO MARTINEZ");
        CODIGOS_JUGADORES.put("GOHN9720", "HUGO NAVARRO");
        CODIGOS_JUGADORES.put("GODN3056", "DARIO NOGUERAS");
        CODIGOS_JUGADORES.put("GOAD8612", "ALAN DAYAN");
        CODIGOS_JUGADORES.put("GORM4383", "RAUL MAG");
        CODIGOS_JUGADORES.put("GOLB7019", "LOKMAN BENCHOHRA");
        CODIGOS_JUGADORES.put("GORV2645", "RAUL VILLASANTE");
        CODIGOS_JUGADORES.put("GOAC5181", "AIMAR CALVO");
        CODIGOS_JUGADORES.put("GOSD9937", "SEBASTIAN DELGADO");
        CODIGOS_JUGADORES.put("GOBR1564", "BRAHIM");
        CODIGOS_JUGADORES.put("GORG4790", "RENAN GIRELLI");
        CODIGOS_JUGADORES.put("GOYL8228", "YAGO LORENTE");
        CODIGOS_JUGADORES.put("GOJM7304", "JOSE MANUEL MERINO");
        CODIGOS_JUGADORES.put("GOMS4829", "MATEO SANTA BARBARA");
    }

    private static final String CODIGO_ENTRENADOR = "ENTRENADOR_SERGIO_2024";
    // --- Fin del mapa ---

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyectamos las herramientas que necesitamos en el constructor
    public RegistroService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- El método validarCodigo que ya tenías ---
    public Optional<DatosCodigo> validarCodigo(String codigo) {
        if (CODIGOS_JUGADORES.containsKey(codigo)) {
            return Optional.of(new DatosCodigo(CODIGOS_JUGADORES.get(codigo), Rol.JUGADOR));
        }
        if (CODIGO_ENTRENADOR.equals(codigo)) {
            return Optional.of(new DatosCodigo(null, Rol.ENTRENADOR));
        }
        return Optional.empty();
    }
    public record DatosCodigo(String nombreCompleto, Rol rol) {}
    // --- Fin del método de validación ---


    // --- ¡REEMPLAZA ESTE MÉTODO POR COMPLETO! ---

    public User registrarUsuario(RegistroRequest request) {
        DatosCodigo datosCodigo = validarCodigo(request.codigoRegistro())
                .orElseThrow(() -> new IllegalStateException("Código de registro inválido"));

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalStateException("El correo electrónico ya está en uso");
        }

        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRol(datosCodigo.rol());
        newUser.setCodigoRegistro(request.codigoRegistro());

        if (datosCodigo.rol() == Rol.JUGADOR) {
            if (request.categoria() == null) {
                throw new IllegalStateException("La categoría es obligatoria para el registro de un jugador");
            }
            // ¡LÍNEA CLAVE! Obtenemos el nombre SIEMPRE del mapa de códigos para jugadores
            newUser.setNombreCompleto(datosCodigo.nombreCompleto());
            newUser.setCategoria(request.categoria());
        } else if (datosCodigo.rol() == Rol.ENTRENADOR) {
            if (request.nombreCompleto() == null || request.nombreCompleto().isBlank()) {
                throw new IllegalStateException("El nombre completo es obligatorio para el registro de un entrenador");
            }
            newUser.setNombreCompleto(request.nombreCompleto());
            newUser.setCategoria(null);
        }

        return userRepository.save(newUser);
    }
}