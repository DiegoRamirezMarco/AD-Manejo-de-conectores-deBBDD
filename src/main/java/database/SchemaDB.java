package database;

public interface SchemaDB {

    String BD_NAME = "BBCar";
    /*---------------------------------*/
    String TAB_COCH = "coches";
    String COL_COCH_ID = "id";
    String COL_COCH_MARCA = "marca";
    String COL_COCH_MATR = "matricula";
    String COL_COCH_PLAZ = "plazas";
    /*----------------------------------*/
    String TAB_PAS = "pasajeros";
    String COL_PAS_ID = "id";
    String COL_PAS_NOMBRE = "nombre";
    String COL_PAS_EDAD = "edad";
    String COL_PAS_PESO = "peso";
    /*----------------------------------*/
    String TAB_COCHPAS = "coche_pasajero";
    String COL_COCHPAS_IDCOCH = "id_coche";
    String COL_COCHPAS_IDPAS = "id_pasajero";


}
