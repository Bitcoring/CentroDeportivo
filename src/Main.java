import java.util.Scanner;
import java.util.HashMap;
import dominio.Socio;
import persistencia.GestorTexto;
import persistencia.GestorBinario;

public class Main {
    
    static HashMap<String, Socio> sociosGuardados = new HashMap<>();
    
    public static void main(String[] args) {
        
       // GestorTexto.cargar(sociosGuardados);
        GestorBinario.cargar(sociosGuardados);
        
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n--- CENTRO DEPORTIVO ---");
            System.out.println("1. Registrar Socio");
            System.out.println("2. Ver Socios");
            System.out.println("3. Guardar datos (TXT)");
            System.out.println("4. Guardar datos (BIN)");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine(); 

            switch (opcion) {
                case 1:
                    registrarSocio();
                    break;
                case 2:
                    verSocios();
                    break;
                case 3:
                    GestorTexto.guardar(sociosGuardados);
                    break;
                case 4:
                    GestorBinario.guardar(sociosGuardados);
                    break;
                case 5:
                    System.out.println("Cerrando el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Error: Elige una opción entre 1 y 5.");
            }
            
        } while (opcion != 5); // Condición actualizada
    }
    
    public static void registrarSocio() {
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("\n*** REGISTRO DE NUEVO SOCIO ***");
        
        System.out.print("Introduce el DNI: ");
        String dni = entrada.nextLine();
        
        System.out.print("Introduce el Nombre: ");
        String nombre = entrada.nextLine();
        
        System.out.print("Introduce la Edad: ");
        int edad = entrada.nextInt();
        entrada.nextLine(); 
        
        Socio nuevoSocio = new Socio(dni, nombre, edad);
        sociosGuardados.put(dni, nuevoSocio);
        
        System.out.println("¡Éxito! El socio " + nombre + " ha sido registrado.");
    }
    
    public static void verSocios() {
        System.out.println("\n*** LISTADO DE SOCIOS ***");
        
        if (sociosGuardados.isEmpty()) {
            System.out.println("No hay ningún socio registrado todavía.");
        } else {
            for (Socio cliente : sociosGuardados.values()) {
                System.out.println("DNI: " + cliente.getDni() + 
                                   " | Nombre: " + cliente.getNombre() + 
                                   " | Edad: " + cliente.getEdad());
            }
        }
    }
}