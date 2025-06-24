package com.entrenamiento.planverano.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RegistroService {

    // --- El mapa de códigos que ya tenías ---
    private static final Map<String, String> CODIGOS_JUGADORES = Map.ofEntries(
            // ... (AQUÍ VA TU LISTA COMPLETA DE JUGADORES)
            Map.entry("GORA8392", "RUBEN APARICIO"),
            Map.entry("GORD1047", "RUSU DENIS"),
            Map.entry("GOLA3819", "LORIEN ARNAL"),
            Map.entry("GOMT9254", "MOHAMMED TAGHILTI"),
            Map.entry("GOJQ6118", "JORGE QUATRONNE P"),
            Map.entry("GOLA2845", "LUCAS AROYO"),
            Map.entry("GOPB7402", "PABLO BERDEJO"),
            Map.entry("GOJC5936", "JESUS CLAVERIA"),
            Map.entry("GODF1773", "DARIO FELIPE"),
            Map.entry("GOJC8460", "JAVIER COLELL"),
            Map.entry("GOPG4921", "PABLO GARCIA"),
            Map.entry("GONG3305", "NICOLAS GARCIA"),
            Map.entry("GOHP9588", "HUGO PRADOS"),
            Map.entry("GODG6241", "DIEGO GELABERT"),
            Map.entry("GOPL1897", "PEDRO LATORRE"),
            Map.entry("GOBG7533", "BRUNO GOMEZ"),
            Map.entry("GOJM5519", "JORGE MARTIN"),
            Map.entry("GOMJ2964", "MARIO JOSE"),
            Map.entry("GOAC4170", "ALEX COWASZ"),
            Map.entry("GOSA6733", "SALIU"),
            Map.entry("GOTI9201", "TEODOR IVANOV"),
            Map.entry("GODE4857", "DANIEL ESPEJO"),
            Map.entry("GOHL1123", "HUGO LOBERA P"),
            Map.entry("GODM7698", "DANIEL MURCIA"),
            Map.entry("GOKI3448", "KILIAN"),
            Map.entry("GOJU8025", "JULEN"),
            Map.entry("GOMO5381", "MOHA"),
            Map.entry("GOCR9874", "CRISTIAN"),
            Map.entry("GOEN6509", "ERIC NAVARRO"),
            Map.entry("GOUR2216", "URIEL REYES"),
            Map.entry("GOGS8842", "GEORGE STOICA"),
            Map.entry("GOPL3177", "PABLO LACOMA"),
            Map.entry("GOJM9403", "JAVI MIRAMON"),
            Map.entry("GOVP6650", "VICTOR PARDOS"),
            Map.entry("GOLG2086", "LUCAS GAUDES"),
            Map.entry("GOME5714", "MARCOS EZQUERRA"),
            Map.entry("GOMI1390", "MIGUEL"),
            Map.entry("GOAT7829", "ALI TAYYAB"),
            Map.entry("GODC4551", "DAVID CIOCAN"),
            Map.entry("GORD8138", "RAUL DOMITRASCU"),
            Map.entry("GORF2815", "ROBERTO FERNANDO"),
            Map.entry("GOEF5472", "ENRIQUE FLORIA"),
            Map.entry("GOMT1907", "MIGUEL TORNERO"),
            Map.entry("GODM6394", "DIEGO MARTINEZ"),
            Map.entry("GOHN9720", "HUGO NAVARRO"),
            Map.entry("GODN3056", "DARIO NOGUERAS"),
            Map.entry("GOAD8612", "ALAN DAYAN"),
            Map.entry("GORM4383", "RAUL MAG"),
            Map.entry("GOLB7019", "LOKMAN BENCHOHRA"),
            Map.entry("GORV2645", "RAUL VILLASANTE"),
            Map.entry("GOAC5181", "AIMAR CALVO"),
            Map.entry("GOSD9937", "SEBASTIAN DELGADO"),
            Map.entry("GOBR1564", "BRAHIM"),
            Map.entry("GORG4790", "RENAN GIRELLI"),
            Map.entry("GOYL8228", "YAGO LORENTE"),
            Map.entry("GOJM7304", "JOSE MANUEL MERINO")
    );
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