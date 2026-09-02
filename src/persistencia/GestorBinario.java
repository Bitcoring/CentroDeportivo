package persistencia;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import dominio.Socio;

public class GestorBinario {
    
    private static final String ARCHIVO = "socios.dat";

    public static void guardar(HashMap<String, Socio> mapaSocios) {
        try {
            FileOutputStream archivoFisico = new FileOutputStream(ARCHIVO);
            ObjectOutputStream traductor = new ObjectOutputStream(archivoFisico);
            
            traductor.writeObject(mapaSocios);
            traductor.close();
            
            System.out.println("Datos guardados en formato binario en " + ARCHIVO);
            
        } catch (Exception e) {
            System.out.println("Error fatal al escribir en binario: " + e.getMessage());
        }
    }
}