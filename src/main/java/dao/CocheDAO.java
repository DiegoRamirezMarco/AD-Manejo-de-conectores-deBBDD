package dao;

import database.ConexionDB;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CocheDAO {
    private Connection conexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDAO(){
        conexion = new ConexionDB().getConexion();
    }

    //Añadir nuevo coche
    public void agregarCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_COCH,
                SchemaDB.COL_COCH_MARCA, SchemaDB.COL_COCH_MATR,SchemaDB.COL_COCH_PLAZ
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1,coche.getMarca());
        preparedStatement.setInt(2,coche.getMatricula());
        preparedStatement.setInt(3,coche.getPlazas());
        preparedStatement.execute();
    }
    //Consulta coche por ID
    public Coche buscarCoche(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_COCH,
                SchemaDB.COL_COCH_ID
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,id);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int idn = resultSet.getInt(SchemaDB.COL_COCH_ID);
            String marca = resultSet.getString(SchemaDB.COL_COCH_MARCA);
            int matricula = resultSet.getInt(SchemaDB.COL_COCH_MATR);
            int plazas = resultSet.getInt(SchemaDB.COL_COCH_PLAZ);
            Coche nuevoCoche = mapearCoche(idn,marca,matricula,plazas);
            return nuevoCoche;
        }
        return null;
    }
    public Coche buscarCocheM(int matricula) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_COCH,
                SchemaDB.COL_COCH_MATR
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,matricula);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int idn = resultSet.getInt(SchemaDB.COL_COCH_ID);
            String marca = resultSet.getString(SchemaDB.COL_COCH_MARCA);
            int matriculan = resultSet.getInt(SchemaDB.COL_COCH_MATR);
            int plazas = resultSet.getInt(SchemaDB.COL_COCH_PLAZ);
            Coche nuevoCoche = mapearCoche(idn,marca,matriculan,plazas);
            return nuevoCoche;
        }
        return null;
    }
    //Borrar coche por ID
    public void borrarCoche(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_COCH,
                SchemaDB.COL_COCH_ID
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();
    }
    //Modificar coche por ID
    public void modificarCoche(Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemaDB.TAB_COCH,
                SchemaDB.COL_COCH_MARCA,SchemaDB.COL_COCH_MATR,SchemaDB.COL_COCH_PLAZ,SchemaDB.COL_COCH_ID
        );
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1,coche.getMarca());
        preparedStatement.setInt(2,coche.getMatricula());
        preparedStatement.setInt(3,coche.getPlazas());
        preparedStatement.setInt(4,coche.getId());
        preparedStatement.executeUpdate();
    }
    //Listado de coches
    public List<Coche> listadoCoches() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_COCH);
        preparedStatement = conexion.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        List<Coche> listaCoches = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt(SchemaDB.COL_COCH_ID);
            String marca = resultSet.getString(SchemaDB.COL_COCH_MARCA);
            int matricula = resultSet.getInt(SchemaDB.COL_COCH_MATR);
            int plazas = resultSet.getInt(SchemaDB.COL_COCH_PLAZ);
            Coche nuevoCoche = mapearCoche(id,marca,matricula,plazas);
            listaCoches.add(nuevoCoche);
        }
        return listaCoches;
    }

    //Mapear Coche
    private Coche mapearCoche(int id,String marca, int matricula, int plazas){
        return new Coche(id,marca,matricula,plazas);
    }



}
