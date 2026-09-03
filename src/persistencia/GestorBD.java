package persistencia;
import java.sql.PreparedStatement;
import java.util.HashMap;
import dominio.Socio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; // Nueva interfaz importada

public class GestorBD {
    
    private static final String URL = "jdbc:sqlite:gimnasio.db";

    public static Connection conectar() {
        Connection cable = null;
        try {
            cable = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return cable;
    }

    // Nuevo método para estructurar la base de datos
    public static void crearTabla() {
        // 1. Escribimos la orden en lenguaje SQL puro
        String sql = "CREATE TABLE IF NOT EXISTS socios ("
                   + "dni TEXT PRIMARY KEY, "
                   + "nombre TEXT NOT NULL, "
                   + "edad INTEGER NOT NULL"
                   + ");";
        
        try {
            // 2. Pedimos un cable prestado
            Connection cable = conectar();
            
            // 3. Fabricamos el "sobre" para la orden
            Statement orden = cable.createStatement();
            
            // 4. Enviamos la orden por el cable
            orden.execute(sql);
            
            // 5. Cerramos los recursos para no saturar la memoria
            orden.close();
            cable.close();
            
            System.out.println("Estructura de la base de datos validada y lista.");
            
        } catch (Exception e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }
    public static void guardar(HashMap<String, Socio> mapaSocios) {
        // La instrucción SQL estática con sus huecos seguros (?)
        String sql = "INSERT OR REPLACE INTO socios (dni, nombre, edad) VALUES (?, ?, ?);";
        
        try {
            Connection cable = conectar();
            // Creamos el sobre especial que entiende los huecos
            PreparedStatement ordenPreparada = cable.prepareStatement(sql);
            
            // Recorremos todo el HashMap (RAM)
            for (Socio cliente : mapaSocios.values()) {
                // Rellenamos cada hueco indicando su posición (1, 2 y 3)
                ordenPreparada.setString(1, cliente.getDni());
                ordenPreparada.setString(2, cliente.getNombre());
                ordenPreparada.setInt(3, cliente.getEdad());
                
                // Apretamos el gatillo: enviamos la orden para este socio exacto
                ordenPreparada.executeUpdate();
            }
            
            ordenPreparada.close();
            cable.close();
            
            System.out.println("Base de datos sincronizada correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error al guardar en SQLite: " + e.getMessage());
        }
    }
}