package com.entrenamiento.planverano.training;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PlanDataLoader implements CommandLineRunner {

    private final SesionDiariaRepository sesionDiariaRepository;

    public PlanDataLoader(SesionDiariaRepository sesionDiariaRepository) {
        this.sesionDiariaRepository = sesionDiariaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (sesionDiariaRepository.count() == 0) {
            System.out.println(">>> [INFO] Base de datos de entrenamientos vacía. Cargando el plan de 5 semanas...");
            crearPlanCompleto();
            System.out.println(">>> [INFO] Plan de 5 semanas cargado con éxito.");
        }
    }

    /**
     * Crea y guarda en la base de datos el plan de entrenamiento completo de 5 semanas.
     * Cada PasoEjercicio ahora incluye una URL a un GIF demostrativo.
     *
     * NOTA: Se asume que el constructor de PasoEjercicio es:
     * new PasoEjercicio(orden, nombre, tipoMedida, valor, descanso, urlGif)
     */
    private void crearPlanCompleto() {
        // Mapa para centralizar las URL de los GIF y facilitar el mantenimiento.
        Map<String, String> gifUrls = Map.ofEntries(
                Map.entry("carrera", "https://tenor.com/es/view/carrera-gif-13118253062065078318"),
                Map.entry("sprint", "https://tenor.com/es/view/carrera-gif-13118253062065078318"),
                Map.entry("progresion", "https://tenor.com/es/view/carrera-gif-13118253062065078318"),
                Map.entry("comba", "https://tenor.com/es/view/salto-comba-gif-2914444104165610464"),
                Map.entry("flexiones", "https://tenor.com/es/view/flexiones-basicas-gif-1171585521907987152"),
                Map.entry("sentadillas", "https://tenor.com/es/view/sentadilla-gif-5998499603474216766"),
                Map.entry("burpees", "https://tenor.com/es/view/burpee-gif-9811966490760373898"),
                Map.entry("zancadas", "https://tenor.com/es/view/zancada-gif-3003118593572451999"),
                Map.entry("skipping", "https://tenor.com/es/view/skipping-gif-5387627474013946269"),
                Map.entry("abdominales_piernas", "https://tenor.com/es/view/abdominales-gif-3519843968412289278"),
                Map.entry("abdominales_oblicuos", "https://tenor.com/es/view/abdominales-gif-17372017450110417161"),
                Map.entry("abdominales_suelo", "https://tenor.com/es/view/abdominales-gif-4944685149997192053"),
                Map.entry("twist_ruso", "https://tenor.com/es/view/twist-ruso-gif-8725087732844623092"),
                Map.entry("escaladores", "https://tenor.com/es/view/escalada-gif-4589251845944064927")
        );

        // --- SEMANA 1 (Adaptación – 3 días de entrenamiento) ---
        // Día 1
        SesionDiaria s1d1 = new SesionDiaria(1, 1, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 1", "Adaptación Física General");
        BloqueEjercicio b1d1_1 = new BloqueEjercicio(1, 1, 180, List.of(
                new PasoEjercicio(1, "Carrera suave", TipoMedida.TIEMPO_MINUTOS, 10, 0, gifUrls.get("carrera"))
        ));
        BloqueEjercicio b1d1_2 = new BloqueEjercicio(2, 2, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b1d1_3 = new BloqueEjercicio(3, 3, 0, List.of(
                new PasoEjercicio(1, "Sprint 20 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("sentadillas"))
        ));
        BloqueEjercicio b1d1_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Sprint 20 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint"))
        ));
        BloqueEjercicio b1d1_5 = new BloqueEjercicio(5, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null) // Sin GIF
        ));
        s1d1.setBloques(List.of(b1d1_1, b1d1_2, b1d1_3, b1d1_4, b1d1_5));
        sesionDiariaRepository.save(s1d1);

        // Día 2
        SesionDiaria s1d2 = new SesionDiaria(1, 2, TipoSesion.ACTIVIDAD_LIBRE, "Día 2", "Actividad libre (Ciclismo, escalada, senderismo, tenis, pádel, baloncesto, natación, etc.)");
        sesionDiariaRepository.save(s1d2);

        // Día 3
        SesionDiaria s1d3 = new SesionDiaria(1, 3, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 3", "Resistencia y Fuerza");
        BloqueEjercicio b1d3_1 = new BloqueEjercicio(1, 1, 300, List.of(
                new PasoEjercicio(1, "Carrera continua", TipoMedida.TIEMPO_MINUTOS, 15, 0, gifUrls.get("carrera"))
        ));
        BloqueEjercicio b1d3_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Skipping alto", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("skipping")),
                new PasoEjercicio(2, "Zancadas", TipoMedida.REPETICIONES, 12, 30, gifUrls.get("zancadas")),
                new PasoEjercicio(3, "Burpees", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("burpees"))
        ));
        BloqueEjercicio b1d3_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Skipping alto", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("skipping"))
        ));
        BloqueEjercicio b1d3_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s1d3.setBloques(List.of(b1d3_1, b1d3_2, b1d3_3, b1d3_4));
        sesionDiariaRepository.save(s1d3);


        // --- SEMANA 2 (4 días de entrenamiento) ---
        // Día 1
        SesionDiaria s2d1 = new SesionDiaria(2, 1, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 1", "Fuerza y Potencia");
        BloqueEjercicio b2d1_1 = new BloqueEjercicio(1, 2, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b2d1_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Sprint 30 m", TipoMedida.REPETICIONES, 2, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 12, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("sentadillas")),
                new PasoEjercicio(4, "Abdominales pies suelo", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("abdominales_suelo"))
        ));
        BloqueEjercicio b2d1_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Sprint 30 m", TipoMedida.REPETICIONES, 2, 30, gifUrls.get("sprint"))
        ));
        BloqueEjercicio b2d1_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s2d1.setBloques(List.of(b2d1_1, b2d1_2, b2d1_3, b2d1_4));
        sesionDiariaRepository.save(s2d1);

        // Día 2
        SesionDiaria s2d2 = new SesionDiaria(2, 2, TipoSesion.ACTIVIDAD_LIBRE, "Día 2", "Actividad libre (Ciclismo, escalada, senderismo, tenis, pádel, baloncesto, natación, etc.)");
        sesionDiariaRepository.save(s2d2);

        // Día 3
        SesionDiaria s2d3 = new SesionDiaria(2, 3, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 3", "Resistencia y Fuerza");
        BloqueEjercicio b2d3_1 = new BloqueEjercicio(1, 1, 300, List.of(
                new PasoEjercicio(1, "Carrera continua", TipoMedida.TIEMPO_MINUTOS, 20, 0, gifUrls.get("carrera"))
        ));
        BloqueEjercicio b2d3_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Skipping alto", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("skipping")),
                new PasoEjercicio(2, "Zancadas", TipoMedida.REPETICIONES, 12, 30, gifUrls.get("zancadas")),
                new PasoEjercicio(3, "Burpees", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("burpees"))
        ));
        BloqueEjercicio b2d3_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Skipping alto", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("skipping"))
        ));
        BloqueEjercicio b2d3_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s2d3.setBloques(List.of(b2d3_1, b2d3_2, b2d3_3, b2d3_4));
        sesionDiariaRepository.save(s2d3);

        // Día 4
        SesionDiaria s2d4 = new SesionDiaria(2, 4, TipoSesion.ACTIVIDAD_LIBRE, "Día 4", "Actividad libre (Ciclismo, escalada, senderismo, tenis, pádel, baloncesto, natación, etc.)");
        sesionDiariaRepository.save(s2d4);

        // --- SEMANA 3 (5 días de entrenamiento, se añade balón en 2 días) ---
        // Día 1
        SesionDiaria s3d1 = new SesionDiaria(3, 1, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 1", "Fuerza, Potencia y Técnica");
        BloqueEjercicio b3d1_1 = new BloqueEjercicio(1, 2, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b3d1_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Sprint 40 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("sentadillas"))
        ));
        BloqueEjercicio b3d1_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Sprint 40 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Saltos verticales", TipoMedida.REPETICIONES, 12, 30, null), // Asumimos no GIF para saltos verticales
                new PasoEjercicio(3, "Saltos verticales", TipoMedida.REPETICIONES, 12, 30, null),
                new PasoEjercicio(4, "Saltos verticales", TipoMedida.REPETICIONES, 12, 30, null)
        ));
        BloqueEjercicio b3d1_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (Conducción rápida en zigzag + pase contra la pared)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        BloqueEjercicio b3d1_5 = new BloqueEjercicio(5, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s3d1.setBloques(List.of(b3d1_1, b3d1_2, b3d1_3, b3d1_4, b3d1_5));
        sesionDiariaRepository.save(s3d1);

        // Día 2
        SesionDiaria s3d2 = new SesionDiaria(3, 2, TipoSesion.ACTIVIDAD_LIBRE, "Día 2", "Actividad libre");
        sesionDiariaRepository.save(s3d2);

        // Día 3
        SesionDiaria s3d3 = new SesionDiaria(3, 3, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 3", "Resistencia y Core");
        BloqueEjercicio b3d3_1 = new BloqueEjercicio(1, 1, 300, List.of(
                new PasoEjercicio(1, "Carrera continua", TipoMedida.TIEMPO_MINUTOS, 20, 0, gifUrls.get("carrera"))
        ));
        BloqueEjercicio b3d3_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Skipping alto", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("skipping")),
                new PasoEjercicio(2, "Abdominales (elevación de piernas)", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("abdominales_piernas")),
                new PasoEjercicio(3, "Twist ruso", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("twist_ruso")),
                new PasoEjercicio(4, "Escaladores", TipoMedida.TIEMPO_SEGUNDOS, 30, 30, gifUrls.get("escaladores"))
        ));
        BloqueEjercicio b3d3_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s3d3.setBloques(List.of(b3d3_1, b3d3_2, b3d3_3));
        sesionDiariaRepository.save(s3d3);

        // Día 4
        SesionDiaria s3d4 = new SesionDiaria(3, 4, TipoSesion.ACTIVIDAD_LIBRE, "Día 4", "Actividad libre");
        sesionDiariaRepository.save(s3d4);

        // Día 5
        SesionDiaria s3d5 = new SesionDiaria(3, 5, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 5", "Fuerza, Potencia y Técnica");
        BloqueEjercicio b3d5_1 = new BloqueEjercicio(1, 2, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b3d5_2 = new BloqueEjercicio(2, 2, 0, List.of(
                new PasoEjercicio(1, "Progresión de menos a más 50 m", TipoMedida.REPETICIONES, 2, 30, gifUrls.get("progresion")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("sentadillas")),
                new PasoEjercicio(4, "Twist ruso", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("twist_ruso"))
        ));
        BloqueEjercicio b3d5_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Sprint 10 m", TipoMedida.REPETICIONES, 3, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("sentadillas")),
                new PasoEjercicio(4, "Twist ruso", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("twist_ruso"))
        ));
        BloqueEjercicio b3d5_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (Golpes con el interior y empeine contra la pared + control orientado)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        BloqueEjercicio b3d5_5 = new BloqueEjercicio(5, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s3d5.setBloques(List.of(b3d5_1, b3d5_2, b3d5_3, b3d5_4, b3d5_5));
        sesionDiariaRepository.save(s3d5);

        // --- SEMANA 4 (6 días de entrenamiento, mayor intensidad y balón en 2 días) ---
        // Día 1
        SesionDiaria s4d1 = new SesionDiaria(4, 1, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 1", "Alta Intensidad y Técnica");
        BloqueEjercicio b4d1_1 = new BloqueEjercicio(1, 1, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b4d1_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Sprint 30 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 25, 30, gifUrls.get("sentadillas")),
                new PasoEjercicio(4, "Saltos verticales", TipoMedida.REPETICIONES, 15, 30, null)
        ));
        BloqueEjercicio b4d1_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (Circuito de conducción + pase con control orientado)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        BloqueEjercicio b4d1_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s4d1.setBloques(List.of(b4d1_1, b4d1_2, b4d1_3, b4d1_4));
        sesionDiariaRepository.save(s4d1);

        // Día 2
        SesionDiaria s4d2 = new SesionDiaria(4, 2, TipoSesion.ACTIVIDAD_LIBRE, "Día 2", "Actividad libre");
        sesionDiariaRepository.save(s4d2);

        // Día 3
        SesionDiaria s4d3 = new SesionDiaria(4, 3, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 3", "Resistencia de Alta Intensidad");
        BloqueEjercicio b4d3_1 = new BloqueEjercicio(1, 1, 300, List.of(
                new PasoEjercicio(1, "Carrera continua", TipoMedida.TIEMPO_MINUTOS, 30, 0, gifUrls.get("carrera"))
        ));
        BloqueEjercicio b4d3_2 = new BloqueEjercicio(2, 3, 0, List.of(
                new PasoEjercicio(1, "Zancadas", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("zancadas")),
                new PasoEjercicio(2, "Burpees", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("burpees"))
        ));
        BloqueEjercicio b4d3_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s4d3.setBloques(List.of(b4d3_1, b4d3_2, b4d3_3));
        sesionDiariaRepository.save(s4d3);

        // Día 4
        SesionDiaria s4d4 = new SesionDiaria(4, 4, TipoSesion.ACTIVIDAD_LIBRE, "Día 4", "Actividad libre");
        sesionDiariaRepository.save(s4d4);

        // Día 5
        SesionDiaria s4d5 = new SesionDiaria(4, 5, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 5", "Velocidad, Core y Técnica");
        BloqueEjercicio b4d5_1 = new BloqueEjercicio(1, 1, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b4d5_2 = new BloqueEjercicio(2, 4, 0, List.of(
                new PasoEjercicio(1, "Sprint 50 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("sprint")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 10, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Abdominales pies suelo", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("abdominales_suelo")),
                new PasoEjercicio(4, "Abdominales oblicuos", TipoMedida.REPETICIONES, 25, 30, gifUrls.get("abdominales_oblicuos"))
        ));
        BloqueEjercicio b4d5_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (hacer toques con el balón (si estáis mas gente \"Que no caiga\") + circuito de zigzag y pase)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        BloqueEjercicio b4d5_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s4d5.setBloques(List.of(b4d5_1, b4d5_2, b4d5_3, b4d5_4));
        sesionDiariaRepository.save(s4d5);

        // Día 6
        SesionDiaria s4d6 = new SesionDiaria(4, 6, TipoSesion.ACTIVIDAD_LIBRE, "Día 6", "Actividad libre");
        sesionDiariaRepository.save(s4d6);

        // --- SEMANA 5 (Última semana antes de pretemporada, máxima intensidad) ---
        // Día 1
        SesionDiaria s5d1 = new SesionDiaria(5, 1, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 1", "Máxima Intensidad y Técnica");
        BloqueEjercicio b5d1_1 = new BloqueEjercicio(1, 1, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_MINUTOS, 3, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b5d1_2 = new BloqueEjercicio(2, 4, 0, List.of(
                new PasoEjercicio(1, "Progresión de menos a más 60 m", TipoMedida.REPETICIONES, 1, 30, gifUrls.get("progresion")),
                new PasoEjercicio(2, "Flexiones", TipoMedida.REPETICIONES, 15, 30, gifUrls.get("flexiones")),
                new PasoEjercicio(3, "Sentadillas", TipoMedida.REPETICIONES, 30, 30, gifUrls.get("sentadillas")),
                new PasoEjercicio(4, "Abdominales (elevación de piernas)", TipoMedida.REPETICIONES, 20, 30, gifUrls.get("abdominales_piernas"))
        ));
        BloqueEjercicio b5d1_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (Conducción rápida + golpeo a puerta)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        BloqueEjercicio b5d1_4 = new BloqueEjercicio(4, 1, 0, List.of(
                new PasoEjercicio(1, "Estiramientos", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s5d1.setBloques(List.of(b5d1_1, b5d1_2, b5d1_3, b5d1_4));
        sesionDiariaRepository.save(s5d1);

        // Día 2
        SesionDiaria s5d2 = new SesionDiaria(5, 2, TipoSesion.ACTIVIDAD_LIBRE, "Día 2", "Actividad libre (Ciclismo, escalada, senderismo, tenis, pádel, baloncesto, natación, etc.)");
        sesionDiariaRepository.save(s5d2);

        // Día 3
        SesionDiaria s5d3 = new SesionDiaria(5, 3, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 3", "Resistencia Aeróbica Larga");
        BloqueEjercicio b5d3_1 = new BloqueEjercicio(1, 1, 0, List.of(
                new PasoEjercicio(1, "Carrera continua", TipoMedida.TIEMPO_MINUTOS, 35, 0, gifUrls.get("carrera"))
        ));
        s5d3.setBloques(List.of(b5d3_1));
        sesionDiariaRepository.save(s5d3);

        // Día 4
        SesionDiaria s5d4 = new SesionDiaria(5, 4, TipoSesion.ACTIVIDAD_LIBRE, "Día 4", "Actividad libre (Ciclismo, escalada, senderismo, tenis, pádel, baloncesto, natación, etc.)");
        sesionDiariaRepository.save(s5d4);

        // Día 5
        SesionDiaria s5d5 = new SesionDiaria(5, 5, TipoSesion.ENTRENAMIENTO_GUIADO, "Día 5", "Velocidad Máxima y Técnica");
        BloqueEjercicio b5d5_1 = new BloqueEjercicio(1, 2, 0, List.of(
                new PasoEjercicio(1, "Saltos a la comba", TipoMedida.TIEMPO_SEGUNDOS, 150, 30, gifUrls.get("comba"))
        ));
        BloqueEjercicio b5d5_2 = new BloqueEjercicio(2, 8, 0, List.of(
                new PasoEjercicio(1, "Sprint 70 m", TipoMedida.REPETICIONES, 1, 60, gifUrls.get("sprint"))
        ));
        BloqueEjercicio b5d5_3 = new BloqueEjercicio(3, 1, 0, List.of(
                new PasoEjercicio(1, "Ejercicio técnico con balón (Conducción, cambios de dirección y sprint corto con balón) Si estáis mas gente podéis hacer Fut-tenis o partido reducido 2vs2, 3vs3, etc.)", TipoMedida.TIEMPO_MINUTOS, 10, 0, null)
        ));
        s5d5.setBloques(List.of(b5d5_1, b5d5_2, b5d5_3));
        sesionDiariaRepository.save(s5d5);

        // Día 6
        SesionDiaria s5d6 = new SesionDiaria(5, 6, TipoSesion.ACTIVIDAD_LIBRE, "Día 6", "Descanso activo (paseo, movilidad, estiramientos largos)");
        sesionDiariaRepository.save(s5d6);
    }
}