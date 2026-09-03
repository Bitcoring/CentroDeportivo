package logica;

import java.util.HashMap;
import dominio.Socio;
import persistencia.GestorBinario;

public class ControladorGimnasio {

    private HashMap<String, Socio> sociosGuardados;

    public ControladorGimnasio() {
        this.sociosGuardados = new HashMap<>();
        // El controlador lee los datos al nacer
        GestorBinario.cargar(this.sociosGuardados);
    }

    public void procesarRegistro(String dni, String nombre, int edad) {
        Socio nuevoSocio = new Socio(dni, nombre, edad);
        sociosGuardados.put(dni, nuevoSocio);
    }

    public HashMap<String, Socio> obtenerListaSocios() {
        return sociosGuardados;
    }

    public void guardarDatos() {
        GestorBinario.guardar(sociosGuardados);
    }
}