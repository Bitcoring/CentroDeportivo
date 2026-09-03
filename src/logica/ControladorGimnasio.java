package logica;

import java.util.HashMap;
import dominio.Socio;
import persistencia.GestorBD;
import persistencia.GestorBinario; // Mantenemos esta importación solo por ahora

public class ControladorGimnasio {

    private HashMap<String, Socio> sociosGuardados;

    public ControladorGimnasio() {
        this.sociosGuardados = new HashMap<>();
        // Seguimos cargando desde el archivo binario hasta que programemos la lectura SQL
        GestorBinario.cargar(this.sociosGuardados);
    }

    public void procesarRegistro(String dni, String nombre, int edad) {
        Socio nuevoSocio = new Socio(dni, nombre, edad);
        sociosGuardados.put(dni, nuevoSocio);
    }

    public HashMap<String, Socio> obtenerListaSocios() {
        return sociosGuardados;
    }

    // Único método de guardado, 100% conectado al motor de base de datos
    public void guardarDatos() {
        GestorBD.guardar(sociosGuardados);
    }
}