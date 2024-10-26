package ejercicio1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private static final String FILE_PATH = "contactos.txt";

    public void agregarContacto(Contacto contacto) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(contacto.getNombre() + "," + contacto.getTelefono() + "," + contacto.getEmail() + "\n");
            System.out.println("ejercicio1.Contacto agregado.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public List<Contacto> listarContactos() {
        List<Contacto> contactos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 3) {
                    contactos.add(new Contacto(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contactos;
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto contacto : listarContactos()) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    public void eliminarContacto(String nombre) {
        List<Contacto> contactos = listarContactos();
        boolean removed = contactos.removeIf(contacto -> contacto.getNombre().equalsIgnoreCase(nombre));

        if (removed) {
            try (FileWriter writer = new FileWriter(FILE_PATH, false)) {
                for (Contacto contacto : contactos) {
                    writer.write(contacto.getNombre() + "," + contacto.getTelefono() + "," + contacto.getEmail() + "\n");
                }
                System.out.println("ejercicio1.Contacto eliminado.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("ejercicio1.Contacto no encontrado.");
        }
    }
}
