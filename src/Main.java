import java.util.Scanner;
import java.util.HashMap;
import dominio.Socio;
import logica.ControladorGimnasio;
import persistencia.GestorBD; // Importamos el Gestor de Base de Datos

public class Main {
    
    public static void main(String[] args) {
        
        // 1. Garantizamos que la tabla física exista antes de hacer nada
        GestorBD.crearTabla();
        
        // 2. Instanciamos el cerebro (que internamente todavía carga los datos binarios)
        ControladorGimnasio controlador = new ControladorGimnasio();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n--- CENTRO DEPORTIVO ---");
            System.out.println("1. Registrar Socio");
            System.out.println("2. Ver Socios");
            System.out.println("3. Guardar datos (Base de Datos)");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine(); 

            switch (opcion) {
                case 1:
                    registrarSocio(controlador, teclado);
                    break;
                case 2:
                    verSocios(controlador);
                    break;
                case 3:
                    // La vista lanza la orden, el controlador ejecuta la inserción SQL
                    controlador.guardarDatos();
                    break;
                case 4:
                    System.out.println("Cerrando el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Error: Elige una opción válida.");
            }
            
        } while (opcion != 4);
    }
    
    public static void registrarSocio(ControladorGimnasio controlador, Scanner teclado) {
        System.out.println("\n*** REGISTRO DE NUEVO SOCIO ***");
        System.out.print("Introduce el DNI: ");
        String dni = teclado.nextLine();
        
        System.out.print("Introduce el Nombre: ");
        String nombre = teclado.nextLine();
        
        System.out.print("Introduce la Edad: ");
        int edad = teclado.nextInt();
        teclado.nextLine(); 
        
        controlador.procesarRegistro(dni, nombre, edad);
        System.out.println("¡Éxito! El socio " + nombre + " ha sido registrado.");
    }
    
    public static void verSocios(ControladorGimnasio controlador) {
        System.out.println("\n*** LISTADO DE SOCIOS ***");
        HashMap<String, Socio> lista = controlador.obtenerListaSocios();
        
        if (lista.isEmpty()) {
            System.out.println("No hay ningún socio registrado todavía.");
        } else {
            for (Socio cliente : lista.values()) {
                System.out.println("DNI: " + cliente.getDni() + 
                                   " | Nombre: " + cliente.getNombre() + 
                                   " | Edad: " + cliente.getEdad());
            }
        }
    }
}