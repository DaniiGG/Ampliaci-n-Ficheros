package ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnalizadorLog {

    public static void main(String[] args) {
        String logFile = "logs.txt";
        Map<String, Integer> logLevels = new HashMap<>();
        Map<String, Integer> errorMessages = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir en 2 partes: timestamp y el resto
                String[] parts = line.split(" ", 3);

                if (parts.length < 3) continue; // Saltar líneas mal formadas

                // El timestamp está en la primera parte
                String timestamp = parts[0] + " " + parts[1]; // Combinar fecha y hora
                String rest = parts[2]; // El resto es nivel y mensaje

                // Dividir el resto en nivel y mensaje
                String[] restParts = rest.split(" ", 2); // Separa el nivel del mensaje
                if (restParts.length < 2) continue; // Saltar si no se puede separar

                String level = restParts[0].toUpperCase().trim(); // Asegurar mayúsculas y espacios
                String message = restParts[1].trim();

                // Validación de nivel de log
                if (!level.equals("INFO") && !level.equals("WARNING") && !level.equals("ERROR")) {
                    System.err.println("Nivel de log desconocido en línea: " + line);
                    continue;
                }

                // Contar los niveles de log
                logLevels.put(level, logLevels.getOrDefault(level, 0) + 1);

                // Contar mensajes solo para los errores
                if (level.equals("ERROR")) {
                    errorMessages.put(message, errorMessages.getOrDefault(message, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de log: " + e.getMessage());
        }

        // Mostrar el conteo de niveles de log
        System.out.println("Estadísticas de Log:");
        logLevels.forEach((level, count) -> System.out.println(level + ": " + count));

        // Mostrar los 5 errores más comunes
        System.out.println("\nTop 5 errores:");
        errorMessages.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
