package ejercicio1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    gestor.agregarContacto(new Contacto(nombre, telefono, email));
                    break;
                case 2:
                    for (Contacto contacto : gestor.listarContactos()) {
                        System.out.println(contacto);
                    }
                    break;
                case 3:
                    System.out.print("Nombre del contacto a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    Contacto contacto = gestor.buscarContacto(nombreBuscar);
                    System.out.println(contacto != null ? contacto : "ejercicio1.Contacto no encontrado.");
                    break;
                case 4:
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    gestor.eliminarContacto(nombreEliminar);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
