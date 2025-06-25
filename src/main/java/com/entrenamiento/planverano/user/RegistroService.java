package com.entrenamiento.planverano.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RegistroService {

    // Declaramos el Mapa como final para que no se pueda reasignar
    private static final Map<String, String> CODIGOS_JUGADORES;

    // Usamos un bloque de inicialización estático para rellenar el mapa
    static {
        Map<String, String> mapaTemporal = new java.util.HashMap<>();

        // --- ¡AQUÍ VA TU LISTA COMPLETA DE JUGADORES! ---
        mapaTemporal.put("GORA8392", "RUBEN APARICIO");
        mapaTemporal.put("GORD1047", "RUSU DENIS");
        mapaTemporal.put("GOLA3819", "LORIEN ARNAL");
        mapaTemporal.put("GOMT9254", "MOHAMMED TAGHILTI");
        mapaTemporal.put("GOJQ6118", "JORGE QUATRONNE P");
        mapaTemporal.put("GOLA2845", "LUCAS AROYO");
        mapaTemporal.put("GOPB7402", "PABLO BERDEJO");
        mapaTemporal.put("GOJC5936", "JESUS CLAVERIA");
        mapaTemporal.put("GODF1773", "DARIO FELIPE");
        mapaTemporal.put("GOJC8460", "JAVIER COLELL");
        mapaTemporal.put("GOPG4921", "PABLO GARCIA");
        mapaTemporal.put("GONG3305", "NICOLAS GARCIA");
        mapaTemporal.put("GOHP9588", "HUGO PRADOS");
        mapaTemporal.put("GODG6241", "DIEGO GELABERT");
        mapaTemporal.put("GOPL1897", "PEDRO LATORRE");
        mapaTemporal.put("GOBG7533", "BRUNO GOMEZ");
        mapaTemporal.put("GOJM5519", "JORGE MARTIN");
        mapaTemporal.put("GOMJ2964", "MARIO JOSE");
        mapaTemporal.put("GOAC4170", "ALEX COWASZ");
        mapaTemporal.put("GOSA6733", "SALIU");
        mapaTemporal.put("GOTI9201", "TEODOR IVANOV");
        mapaTemporal.put("GODE4857", "DANIEL ESPEJO");
        mapaTemporal.put("GOHL1123", "HUGO LOBERA P");
        mapaTemporal.put("GODM7698", "DANIEL MURCIA");
        mapaTemporal.put("GOKI3448", "KILIAN");
        mapaTemporal.put("GOJU8025", "JULEN");
        mapaTemporal.put("GOMO5381", "MOHA");
        mapaTemporal.put("GOCR9874", "CRISTIAN");
        mapaTemporal.put("GOEN6509", "ERIC NAVARRO");
        mapaTemporal.put("GOUR2216", "URIEL REYES");
        mapaTemporal.put("GOGS8842", "GEORGE STOICA");
        mapaTemporal.put("GOPL3177", "PABLO LACOMA");
        mapaTemporal.put("GOJM9403", "JAVI MIRAMON");
        mapaTemporal.put("GOVP6650", "VICTOR PARDOS");
        mapaTemporal.put("GOLG2086", "LUCAS GAUDES");
        mapaTemporal.put("GOME5714", "MARCOS EZQUERRA");
        mapaTemporal.put("GOMI1390", "MIGUEL PORTERO");
        mapaTemporal.put("GOAT7829", "ALI TAYYAB");
        mapaTemporal.put("GODC4551", "DAVID CIOCAN");
        mapaTemporal.put("GORD8138", "RAUL DOMITRASCU");
        mapaTemporal.put("GORF2815", "ROBERTO FERNANDO");
        mapaTemporal.put("GOEF5472", "ENRIQUE FLORIA");
        mapaTemporal.put("GOMT1907", "MIGUEL TORNERO");
        mapaTemporal.put("GODM6394", "DIEGO MARTINEZ");
        mapaTemporal.put("GOHN9720", "HUGO NAVARRO");
        mapaTemporal.put("GODN3056", "DARIO NOGUERAS");
        mapaTemporal.put("GOAD8612", "ALAN DAYAN");
        mapaTemporal.put("GORM4383", "RAUL MAG");
        mapaTemporal.put("GOLB7019", "LOKMAN BENCHOHRA");
        mapaTemporal.put("GORV2645", "RAUL VILLASANTE");
        mapaTemporal.put("GOAC5181", "AIMAR CALVO");
        mapaTemporal.put("GOSD9937", "SEBASTIAN DELGADO");
        mapaTemporal.put("GOBR1564", "BRAHIM");
        mapaTemporal.put("GORG4790", "RENAN GIRELLI");
        mapaTemporal.put("GOYL8228", "YAGO LORENTE");
        mapaTemporal.put("GOJM7304", "JOSE MANUEL MERINO");
        mapaTemporal.put("GOMS4829", "MATEO SANTABARBARA");

        // Hacemos el mapa final inmutable por seguridad
        CODIGOS_JUGADORES = Map.copyOf(mapaTemporal);
    }

    private static final String CODIGO_ENTRENADOR = "ENTRENADOR_SERGIO_2024";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor para la inyección de dependencias
    public RegistroService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para validar un código de registro
    public Optional<DatosCodigo> validarCodigo(String codigo) {
        if (CODIGOS_JUGADORES.containsKey(codigo)) {
            return Optional.of(new DatosCodigo(CODIGOS_JUGADORES.get(codigo), Rol.JUGADOR));
        }
        if (CODIGO_ENTRENADOR.equals(codigo)) {
            return Optional.of(new DatosCodigo(null, Rol.ENTRENADOR));
        }
        return Optional.empty();
    }

    // Record para devolver dos valores a la vez
    public record DatosCodigo(String nombreCompleto, Rol rol) {}

    // Método para registrar un nuevo usuario
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