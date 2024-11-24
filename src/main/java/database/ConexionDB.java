package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static Connection conexion;

    public Connection getConexion(){
        if (conexion == null){
            nuevaConexion();
        }
        return conexion;
    }

    private void nuevaConexion() {
        String url = "jdbc:mysql://127.0.0.1:3306/BBCar";
        try {
            conexion = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la BBDD " + e.getMessage());
        }
    }
    private void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexion " + e.getMessage());
        }
    }
}
