import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import entity.Pasajero;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String option;
        do {
            option = JOptionPane.showInputDialog("""
                    AEROLINEA:
                                        
                    1. Aviones.
                    2. Pasajeros.
                    3. Vuelos.
                    4. Reservaciones.
                                        
                    5. Salir.
                    """);

            switch (option) {
                case "1":
                    String optio;
                    optio = JOptionPane.showInputDialog("""
                        1. Ingresar un nuevo avion.
                        2. Listar aviones.
                        3. Actualizar avion.
                        4. Eliminar avion.
                        
                        0. Salir.
                        """);
                    switch (optio) {
                        case "1":
                            AvionController.create();
                            break;

                        case "2":
                            JOptionPane.showMessageDialog(null,AvionController.getAll());
                            break;

                        case "3":
                            AvionController.update();
                            break;

                        case "4":
                            AvionController.delete();
                            break;
                        case "0":
                            break;
                    }
                    break;

                case "2":
                    String opti;
                    opti = JOptionPane.showInputDialog("""
                        1. Ingresar un nuevo pasajero.
                        2. Listar pasajeros.
                        3. Actualizar pasajero.
                        4. Eliminar pasajero.
                        
                        0. Salir.
                        """);
                    switch (opti) {
                        case "1":
                            PasajeroController.create();
                            break;

                        case "2":
                            String opti1;
                            opti1 = JOptionPane.showInputDialog("""
                        1. Listar todos.
                        2. Buscar pasajeros por nombre.
                        
                        0. Salir.
                        """);
                            switch (opti1) {
                                case "1":
                                    JOptionPane.showMessageDialog(null,PasajeroController.getAll());
                                    break;
                                case "2":
                                    JOptionPane.showMessageDialog(null, PasajeroController.getByName());
                                    break;
                                case "0":
                                    break;
                            }

                            break;

                        case "3":
                            PasajeroController.update();
                            break;

                        case "4":
                            PasajeroController.delete();
                            break;
                        case "0":
                            break;
                    }
                    break;

                case "3":
                    String opt;
                    opt = JOptionPane.showInputDialog("""
                        1. Ingresar un nuevo vuelo.
                        2. Listar vuelos.
                        3. Actualizar vuelo.
                        4. Eliminar vuelo.
                        
                        0. Salir.
                        """);
                    switch (opt) {
                        case "1":
                            VueloController.create();
                            break;

                        case "2":
                            String opti2;
                            opti2 = JOptionPane.showInputDialog("""
                        1. Listar todos.
                        2. buscar vuelos por destino.
                      2                        
                        0. Salir.
                        """);
                            switch (opti2) {
                                case "1":
                                    JOptionPane.showMessageDialog(null,VueloController.getAll());
                                    break;
                                case "2":
                                    JOptionPane.showMessageDialog(null,VueloController.getVuelosByDestiny());
                                    break;
                                case "0":
                                    break;
                            }
                            break;


                        case "3":
                            VueloController.update();
                            break;

                        case "4":
                            VueloController.delete();
                            break;
                        case "0":
                            break;
                    }
                    break;

                case "4":
                    String op;
                    op = JOptionPane.showInputDialog("""
                        1. Ingresar una nueva reservacion.
                        2. Listar reservaciones.
                        3. Actualizar reservacion.
                        4. Eliminar reservacion.
                        
                        0. Salir.
                        """);
                    switch (op) {
                        case "1":
                            ReservacionController.create();
                            break;

                        case "2":
                            String opti3;
                            opti3 = JOptionPane.showInputDialog("""
                        1. Listar todas.
                        2. buscar reservaciones de un vuelo.
                        
                        0. Salir.
                        """);
                            switch (opti3) {
                                case "1":
                                    JOptionPane.showMessageDialog(null,ReservacionController.getAll());
                                    break;
                                case "2":
                                    JOptionPane.showMessageDialog(null,ReservacionController.getAllByVuelo());
                                    break;
                                case "0":
                                    break;
                            }
                            break;

                        case "3":
                            ReservacionController.update();
                            break;

                        case "4":
                            ReservacionController.delete();
                            break;
                        case "0":
                            break;
                    }
                    break;

            }}while (!option.equals("5"));
    }
}