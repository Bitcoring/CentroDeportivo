package persistencia;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
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

    public static void cargar(HashMap<String, Socio> mapaSocios) {
        try {
            File archivoFisico = new File(ARCHIVO);
            
            if (!archivoFisico.exists()) {
                return; 
            }

            FileInputStream archivoEntrada = new FileInputStream(ARCHIVO);
            ObjectInputStream traductorLectura = new ObjectInputStream(archivoEntrada);
            
            // Leemos el objeto y forzamos su tipo con Casting
            HashMap<String, Socio> datosRecuperados = (HashMap<String, Socio>) traductorLectura.readObject();
            
            // Volcamos los datos al mapa original
            mapaSocios.putAll(datosRecuperados);
            
            traductorLectura.close();
            
            System.out.println("Datos binarios recuperados del disco duro.");
            
        } catch (Exception e) {
            System.out.println("Error fatal al leer en binario: " + e.getMessage());
        }
    }
}