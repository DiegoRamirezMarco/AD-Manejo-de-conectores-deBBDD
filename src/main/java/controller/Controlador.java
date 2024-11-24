package controller;

import dao.CocheDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    private CocheDAO cocheDao;
    private PasajeroDAO pasajeroDAO;

    public Controlador() {
        cocheDao = new CocheDAO();
        pasajeroDAO = new PasajeroDAO();
    }

    Scanner scan = new Scanner(System.in);

    //Controlador Coche----------------------------------------------------------------------
    public void nuevoCoche(Coche nuevoCoche) {
        Coche comprobarCoche = buscarCocheMatricula(nuevoCoche.getMatricula());
        try {
            if(comprobarCoche == null) {
                    cocheDao.agregarCoche(nuevoCoche);
                System.out.println("Agregado Coche: " + nuevoCoche.getMarca() + " Matricula: " + nuevoCoche.getMatricula() + " Plazas: " + nuevoCoche.getPlazas());

            } else {
                    System.out.println("Ese coche con la matricula " + nuevoCoche.getMatricula() + " ya existe.");
                }
        } catch (SQLException e) {
            System.out.println("Error al agregar coche a la BBDD");
            System.out.println(e.getMessage());
        }
    }
    public Coche buscarCocheID(int id) {
        Coche encontradoCoche = null;
        try {
            encontradoCoche = cocheDao.buscarCoche(id);
            if(encontradoCoche != null){
            System.out.println("Coche " + encontradoCoche.getId() + ": " + encontradoCoche.getMarca() + " Matricula: " + encontradoCoche.getMatricula() + " Plazas: " + encontradoCoche.getPlazas());
            return encontradoCoche;
            }else {
                System.out.println("Coche no encontrado");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar coche en la BBDD");
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void eliminarCocheID() {
        System.out.println("Introduce el ID del coche que quieres eliminar");
        int id = scan.nextInt();
        Coche comprobarEliminacion = buscarCocheID(id);
        try {
            if(comprobarEliminacion!=null){
                cocheDao.borrarCoche(id);
                System.out.println("Coche " + comprobarEliminacion.getMarca() + " " + comprobarEliminacion.getMatricula() +" ha sido eliminado");
            }else {
                System.out.println("No se encontro el coche que deseas eliminar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el coche de la BBDD");
            System.out.println(e.getMessage());
        }
    }
    public void modificarCoche(int id) {

        try {
            Coche cochMod = buscarCocheID(id);
            if(cochMod == null){
                System.out.println("El coche que quieres modificar no se puede modificar");
                return;
            }
                System.out.println("introduce la nueva Marca");
                String nuevaMarca = scan.next();
                System.out.println("introduce la nueva Matricula");
                int nuevaMatricula = scan.nextInt();
                System.out.println("introduce las nuevas Plazas");
                int nuevaPlaza = scan.nextInt();

                Coche cocheModificado = new Coche(id,nuevaMarca, nuevaMatricula, nuevaPlaza);
                cocheDao.modificarCoche(cocheModificado);
                System.out.println("Atiguo Coche "+cochMod.getId() +": " + cochMod.getMarca() + " Matricula: " + cochMod.getMatricula() + " Plazas: " + cochMod.getPlazas());
                System.out.println("Nuevo Coche "+cocheModificado.getId() +": " + cocheModificado.getMarca() + " Matricula: " + cocheModificado.getMatricula() + " Plazas: " + cocheModificado.getPlazas());
        } catch (SQLException e) {
            System.out.println("Error al modificar coche");
            System.out.println(e.getMessage());
        }

    }
    public void listaCoches() {
        List<Coche> listaCoches;
        try {
            listaCoches = cocheDao.listadoCoches();
            for(Coche co : listaCoches){
                System.out.println("Coche " + co.getId() + ": " + co.getMarca() + " Matricula: " + co.getMatricula() + " Plazas: " + co.getPlazas());
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar la lista de coches");
            System.out.println(e.getMessage());
        }
    }
    private Coche buscarCocheMatricula(int matricula) {
        Coche encontradoCoche = null;
        try {
            encontradoCoche = cocheDao.buscarCocheM(matricula);

            return encontradoCoche;
        } catch (SQLException e) {
            System.out.println("Error al buscar coche en la BBDD");
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Controlador Pasajero---------------------------------------------------------------------
    public void nuevoPasajero(Pasajero pasajeroNuevo) {
        try {
            pasajeroDAO.agregarPasajero(pasajeroNuevo);
        } catch (SQLException e) {
            System.out.println("Error al agregar pasajero a la BBDD");
            System.out.println(e.getMessage());
        }
    }
    public Pasajero buscarPasajeroID(int id) {
        Pasajero encotradoPasajero = null;
        try {
            encotradoPasajero = pasajeroDAO.buscarPasajero(id);
            if(encotradoPasajero!=null) {
                System.out.println("Pasajero " + encotradoPasajero.getId() + ": " + encotradoPasajero.getNombre() + " Edad: " + encotradoPasajero.getEdad() + " Peso: " + encotradoPasajero.getPeso());
                return encotradoPasajero;
            }else {
                System.out.println("Pasajero no encontrado");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Pasajero en la BBDD");
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void eliminarPasajeroID(int id) {
        Pasajero np = buscarPasajeroID(id);
        try {
            if (np != null) {
                pasajeroDAO.borrarPasajero(id);
                System.out.println("Pasajero eliminado");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el Pasajero de la BBDD");
            System.out.println(e.getMessage());
        }
    }
    public void listaPasajeros() {
        List<Pasajero> listaPasajeros;
        try {
            listaPasajeros = pasajeroDAO.listadoPasajeros();
            for (Pasajero pas : listaPasajeros){
                System.out.println("Pasajero "+pas.getId()+": "+pas.getNombre()+" Edad: "+pas.getEdad()+" Peso: "+pas.getPeso());
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar la lista de pasajeros");
            System.out.println(e.getMessage());
        }
    }

    //Controlador Coche_Pasajero---------------------------------------------------------------
    public void nuevoPasajeroACoche() {
        System.out.println("Inserta el ID del Pasajero para agregar al Coche");
        int idPasajero = scan.nextInt();
        System.out.println("Inserta el ID del Coche");
        int idCoche = scan.nextInt();
        Pasajero pasajeroParaCoche = buscarPasajeroID(idPasajero);
        Coche cocheDePasajeros = buscarCocheID(idCoche);
        List<Pasajero> listaPasajerosEnCoche = listarPasajerosDeCoche(idCoche);
        for(Pasajero pas : listaPasajerosEnCoche){
            if(pas.getId()==idPasajero){
                System.out.println("Ese pasajero ya esta en el coche");
                return;
            }
        }
        try {
            if(pasajerosPorCoche(idCoche)<cocheDePasajeros.getPlazas()){
            pasajeroDAO.añadirPasajeroACoche(idCoche, idPasajero);
            System.out.println("Pasajero " + pasajeroParaCoche.getNombre() + " agregado correctamente al coche " + cocheDePasajeros.getMarca() + " con matricula " + cocheDePasajeros.getMatricula());
            }else {
                System.out.println("El coche esta lleno");
            }
            } catch (SQLException e) {
            System.out.println("Error al agregar pasajero a un coche");
            System.out.println(e.getMessage());
        }
    }
    public void eliminarPasajeroDeCoche() {
        System.out.println("Inserta el ID del Pasajero que deseas retirar de un Coche");
        int idPasajero = scan.nextInt();
        System.out.println("Inserta el ID del Coche");
        int idCoche = scan.nextInt();
        try {
            pasajeroDAO.eliminarPasajeroDeCoche(idCoche, idPasajero);
        } catch (SQLException e) {
            System.out.println("Error al retirar pasajero a un coche");
            System.out.println(e.getMessage());
        }
    }
    public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
        List<Pasajero> listaPasajerosCoche = new ArrayList<>();
        try {
            listaPasajerosCoche = pasajeroDAO.listaDePasajerosCoche(idCoche);
            for (Pasajero pas : listaPasajerosCoche) {
                System.out.println("Pasajero "+pas.getId()+ ": " + pas.getNombre());
            }
            return listaPasajerosCoche;

        } catch (SQLException e) {
            System.out.println("Error al listar paasjeros del Coche" + idCoche);
            System.out.println(e.getMessage());

        }
        return listaPasajerosCoche;
    }
    private int pasajerosPorCoche(int idCoche) {
        try {
            return pasajeroDAO.contarPasajerosEnCoche(idCoche);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}