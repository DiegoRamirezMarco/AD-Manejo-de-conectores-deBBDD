package dao;

import database.ConexionDB;
import database.SchemaDB;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {
    private Connection conexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO(){
        conexion = new ConexionDB().getConexion();

    }
    //Añadir nuevo pasajero
    public void agregarPasajero(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_NOMBRE, SchemaDB.COL_PAS_EDAD,SchemaDB.COL_PAS_PESO
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1,pasajero.getNombre());
        preparedStatement.setInt(2,pasajero.getEdad());
        preparedStatement.setDouble(3,pasajero.getPeso());

        preparedStatement.execute();
        System.out.println("Pasajero agregado correctamente");
    }
    //Consulta pasajero por ID
    public Pasajero buscarPasajero(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_ID
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,id);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int idp = resultSet.getInt(SchemaDB.COL_PAS_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOMBRE);
            int edad = resultSet.getInt(SchemaDB.COL_PAS_EDAD);
            double peso = resultSet.getDouble(SchemaDB.COL_PAS_PESO);
            Pasajero nuevoPasajero = mapearPersonaje(idp,nombre,edad,peso);
            return nuevoPasajero;
        }
        return null;
    }
    //Borrar pasajero por ID
    public void borrarPasajero(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_ID
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();
    }
    //Listado de pasajeros
    public List<Pasajero> listadoPasajeros() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PAS);
        preparedStatement = conexion.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        List<Pasajero> listaPasajeros = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt(SchemaDB.COL_PAS_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOMBRE);
            int edad = resultSet.getInt(SchemaDB.COL_PAS_EDAD);
            double peso = resultSet.getDouble(SchemaDB.COL_PAS_PESO);
            Pasajero nuevoPersonaje = mapearPersonaje(id,nombre,edad,peso);
            listaPasajeros.add(nuevoPersonaje);
        }
        return listaPasajeros;
    }
    //Añadir Pasajero a Coche
    public void añadirPasajeroACoche(int idCoche,int idPasajero) throws SQLException {
    String query = String.format("INSERT INTO %s (%s, %s) VALUES (?,?)",
            SchemaDB.TAB_COCHPAS,
            SchemaDB.COL_COCHPAS_IDCOCH,SchemaDB.COL_COCHPAS_IDPAS
            );
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,idCoche);
            preparedStatement.setInt(2,idPasajero);
            preparedStatement.execute();

    }
    //Eliminar Pasajero de Coche
    public void eliminarPasajeroDeCoche(int idCoche,int idPasajero) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                SchemaDB.TAB_COCHPAS,
                SchemaDB.COL_COCHPAS_IDCOCH,SchemaDB.COL_COCHPAS_IDPAS
                );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,idCoche);
        preparedStatement.setInt(2,idPasajero);

        preparedStatement.executeUpdate();
        System.out.println("Pasajero se ha eliminado del Coche");

    }
    //Listar todos los Pasajeros de un Coche
    public List<Pasajero> listaDePasajerosCoche(int idCoche) throws SQLException {
        String query = String.format(
                "SELECT p.* FROM %s p JOIN %s cp ON p.%s = cp.%s WHERE cp.%s = ?",
                SchemaDB.TAB_PAS,
                SchemaDB.TAB_COCHPAS,
                SchemaDB.COL_PAS_ID,
                SchemaDB.COL_COCHPAS_IDPAS, SchemaDB.COL_COCHPAS_IDCOCH
        );

        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,idCoche);
        resultSet = preparedStatement.executeQuery();
        List<Pasajero> listaPasajerosCoche = new ArrayList<>();
        while (resultSet.next()){
            int idPas = resultSet.getInt(SchemaDB.COL_PAS_ID);
            String nombrePas = resultSet.getString(SchemaDB.COL_PAS_NOMBRE);
            int edadPas = resultSet.getInt(SchemaDB.COL_PAS_EDAD);
            double pesoPas = resultSet.getDouble(SchemaDB.COL_PAS_PESO);
            Pasajero pasajero = new Pasajero(idPas,nombrePas,edadPas,pesoPas);
            listaPasajerosCoche.add(pasajero);
        }
        return listaPasajerosCoche;

    }
    //Mapear Pasajero
    private Pasajero mapearPersonaje(int id, String nombre, int edad, double peso){
        return new Pasajero(id,nombre,edad,peso);
    }
    public int contarPasajerosEnCoche(int idCoche) throws SQLException {
        String query = String.format(
                "SELECT COUNT(%s) AS cantidad_pasajeros FROM %s WHERE %s = ?",
                SchemaDB.COL_COCHPAS_IDPAS,
                SchemaDB.TAB_COCHPAS,
                SchemaDB.COL_COCHPAS_IDCOCH
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,idCoche);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("cantidad_pasajeros");
        }

        return 0;
    }


}
