import controller.Controlador;
import model.Coche;
import model.Pasajero;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
Scanner scan = new Scanner(System.in);

        selectorPrincipal();
        scan.close();
    }

    private static void menuPrincipal() {
        System.out.println("""
                        ╔══════════════════════════════════╗
                        ║          MENU PRINCIPAL          ║
                        ╠══════════════════════════════════╣
                        ║  1. Gestionar COCHES             ║
                        ║──────────────────────────────────║
                        ║  2. Gestionar PASAJEROS          ║
                        ║──────────────────────────────────║
                        ║  3. SALIR                        ║                        
                        ╚══════════════════════════════════╝                  
                        """);
    }
    private static void menuCoche() {
        System.out.println("""
                        ╔══════════════════════════════════╗
                        ║           MENU COCHE             ║
                        ╠══════════════════════════════════╣
                        ║  1. Agregar un Coche nuevo       ║
                        ║──────────────────────────────────║
                        ║  2. Buscar un Coche              ║
                        ║──────────────────────────────────║
                        ║  3. Eliminar un Coche            ║
                        ║──────────────────────────────────║
                        ║  4. Modificar un Coche           ║
                        ║──────────────────────────────────║
                        ║  5. Listado de Coches            ║
                        ║──────────────────────────────────║                        
                        ║  6. Salir al Menu Principal      ║                                                
                        ╚══════════════════════════════════╝                  
                        """);
    }
    private static void menuPasajero() {
        System.out.println("""
                        ╔══════════════════════════════════╗
                        ║           MENU PASAJERO          ║
                        ╠══════════════════════════════════╣
                        ║  1. Agregar un Pasajero nuevo    ║
                        ║──────────────────────────────────║
                        ║  2. Buscar un Pasajero           ║
                        ║──────────────────────────────────║
                        ║  3. Eliminar un Pasajero         ║
                        ║──────────────────────────────────║
                        ║  4. Listado de Pasajeros         ║
                        ║──────────────────────────────────║
                        ║  5. Añadir Pasajero a Coche      ║
                        ║──────────────────────────────────║
                        ║  6. Eliminar Pasajero de Coche   ║
                        ║──────────────────────────────────║
                        ║  7. Listar Pasajeros de un Coche ║
                        ║──────────────────────────────────║                                                                        
                        ║  8. Salir al Menu Principal      ║                                                
                        ╚══════════════════════════════════╝                  
                        """);
    }
    private static void selectorPrincipal(){
        menuPrincipal();
        Scanner scanner = new Scanner(System.in);
        String opcion = "0";
        while(!opcion.equals("3")){
            System.out.println("Elige una Opcion");
            opcion = scanner.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("#############################################");
                    selectorCoche();
                    break;
                case "2":
                    System.out.println("#############################################");
                    selectorPasajero();
                    break;
                case "3":
                    System.out.println("Fin del Programa");
                    break;
                default:
                    System.out.println("#############################################");
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
    private static void selectorCoche() {
        Controlador con = new Controlador();
        menuCoche();
        Scanner scanner = new Scanner(System.in);
        String opcion = "0";
        while(!opcion.equals("6")){
            System.out.println("Elige una Opcion");
            opcion = scanner.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("#############################################");
                    System.out.println("Introduce la marca del coche");
                    String marca = scanner.nextLine();
                    System.out.println("Introduce matricula");
                    int matricula = scanner.nextInt();
                    System.out.println("Introduce las plazas");
                    int plazas = scanner.nextInt();
                    scanner.nextLine();
                    Coche cocheNuevo = new Coche(marca, matricula, plazas);
                    con.nuevoCoche(cocheNuevo);
                    break;
                case "2":
                    System.out.println("#############################################");
                    System.out.println("Introduce el ID del coche que quieres buscar");
                    int idBuscarCoche = scanner.nextInt();
                    con.buscarCocheID(idBuscarCoche);
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("#############################################");
                    con.eliminarCocheID();
                    break;
                case "4":
                    System.out.println("#############################################");
                    System.out.println("Introduce el ID del coche que quieres modificar");
                    int idCocheAModificar = scanner.nextInt();
                    con.modificarCoche(idCocheAModificar);
                    scanner.nextLine();
                    break;
                case "5":
                    System.out.println("#############################################");
                    con.listaCoches();
                    break;
                case "6":
                    menuPrincipal();
                    break;
                default:
                    System.out.println("#############################################");
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
    private static void selectorPasajero() {
        Controlador con = new Controlador();
        menuPasajero();
        Scanner scanner = new Scanner(System.in);
        String opcion = "0";
        while(!opcion.equals("8")){
            System.out.println("Elige una Opcion");
            opcion = scanner.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("#############################################");
                    System.out.println("Introduce el nombre del Pasajero");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce la edad");
                    int edad = scanner.nextInt();
                    System.out.println("Introduce el peso");
                    double peso = scanner.nextInt();
                    scanner.nextLine();
                    Pasajero pasajeroNuevo = new Pasajero(nombre,edad,peso);
                    con.nuevoPasajero(pasajeroNuevo);
                    break;
                case "2":
                    System.out.println("#############################################");
                    System.out.println("Introduce el ID del Pasajero que quieres buscar");
                    int idBuscarPasajero = scanner.nextInt();
                    scanner.nextLine();
                    con.buscarPasajeroID(idBuscarPasajero);
                    break;
                case "3":
                    System.out.println("#############################################");
                    System.out.println("Introduce el ID del Pasajero que quieres eliminar");
                    int idEliminarPasajero = scanner.nextInt();
                    scanner.nextLine();
                    con.eliminarPasajeroID(idEliminarPasajero);
                    break;
                case "4":
                    System.out.println("#############################################");
                    con.listaPasajeros();
                    break;
                case "5":
                    System.out.println("#############################################");
                    con.nuevoPasajeroACoche();
                    break;
                case "6":
                    System.out.println("#############################################");
                    con.eliminarPasajeroDeCoche();
                    break;
                case "7":
                    System.out.println("#############################################");
                    System.out.println("Introduce ID del el Coche al que consultar pasajeros");
                    int idCoche = scanner.nextInt();
                    con.listarPasajerosDeCoche(idCoche);
                    scanner.nextLine();
                    break;
                case "8":
                    menuPrincipal();
                    break;
                default:
                    System.out.println("#############################################");
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

}
