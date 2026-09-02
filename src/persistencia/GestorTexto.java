package persistencia;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import dominio.Socio;

public class GestorTexto {
    
    private static final String ARCHIVO = "socios.txt";

    public static void guardar(HashMap<String, Socio> mapaSocios) {
        try {
            FileWriter archivo = new FileWriter(ARCHIVO);
            PrintWriter escritor = new PrintWriter(archivo);

            for (Socio cliente : mapaSocios.values()) {
                escritor.println(cliente.getDni() + "," + cliente.getNombre() + "," + cliente.getEdad());
            }

            escritor.close();
            System.out.println("Datos guardados correctamente en " + ARCHIVO);
            
        } catch (Exception e) {
            System.out.println("Error fatal al escribir en el disco: " + e.getMessage());
        }
    }
    
    public static void cargar(HashMap<String, Socio> mapaSocios) {
        try {
            java.io.File archivoFisico = new java.io.File(ARCHIVO);
            
            if (!archivoFisico.exists()) {
                return; 
            }

            java.util.Scanner lector = new java.util.Scanner(archivoFisico);

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(",");
                
                String dni = partes[0];
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);

                Socio socioCargado = new Socio(dni, nombre, edad);
                mapaSocios.put(dni, socioCargado);
            }

            lector.close();
            System.out.println("Datos anteriores recuperados del disco duro.");

        } catch (Exception e) {
            System.out.println("Error fatal al leer el disco: " + e.getMessage());
        }
    }
}