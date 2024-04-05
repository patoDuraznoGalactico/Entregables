package controller;

import Model.ReservacionModel;
import entity.Reservación;

import javax.swing.*;

public class ReservacionController {
    public static void create(){
        ReservacionModel objModel = new ReservacionModel();

        int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(PasajeroController.getAll()+"\nPor favor ingrese el id del pasajero: "));
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(VueloController.getAll()+"\nPor favor ingrese el id del vuelo: "));
        String fecha_reservacion = JOptionPane.showInputDialog("Por favor ingrese la fecha de reservacion (YYYY-MM-DD): ");
        String asiento = JOptionPane.showInputDialog("Por favor ingrese el asiento: ");

        Reservación objReservacion = new Reservación();
        objReservacion.setId_pasajero(id_pasajero);
        objReservacion.setId_vuelo(id_vuelo);
        objReservacion.setFecha_reservacion(fecha_reservacion);
        objReservacion.setAsiento(asiento);

        objReservacion = (Reservación) objModel.insert(objReservacion);
        JOptionPane.showMessageDialog(null,objReservacion.toString());

    }

    public static String getAll(){
        ReservacionModel objModel = new ReservacionModel();
        String listReservaciones = "Lista de todas las reservaciones: \n";
        for (Object i : objModel.findAll()){
            Reservación objReservacion = (Reservación) i;
            listReservaciones += objReservacion.toString()+"\n";
        }
        return listReservaciones;
    }
    public static void delete(){
        ReservacionModel objModel = new ReservacionModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id de la reservacion a borrar: "));
        Reservación objReservacion = (Reservación) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objReservacion == null){
            JOptionPane.showMessageDialog(null,"Reservacion no encontrada");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objReservacion.toString()+"\nEstas seguro que deseas borrar esta reservacion?");
            if (confirm == 0){
                objModel.delete(objReservacion);
            }
        }
    }

    public static void update() {
        ReservacionModel objModel = new ReservacionModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll() + "\n Por favor ingrese el id de la reservacion que desea actualizar: "));
        Reservación objReservacion = (Reservación) objModel.findById(idFind);
        if (objReservacion == null) {
            JOptionPane.showMessageDialog(null, "Reservacion no encontrada");
        } else {
            int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(PasajeroController.getAll()+"\nPor favor ingrese el id del pasajero nuevo: ",objReservacion.getId_pasajero()));
            int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(VueloController.getAll()+"\nPor favor ingrese el id del vuelo nuevo: ",objReservacion.getId_vuelo()));
            String fecha_reservacion = JOptionPane.showInputDialog("Por favor ingrese la fecha de reservacion nueva (YYYY-MM-DD): ", objReservacion.getFecha_reservacion());
            String asiento = JOptionPane.showInputDialog("Por favor ingrese el asiento nuevo: ", objReservacion.getAsiento());

            objReservacion.setId_pasajero(id_pasajero);
            objReservacion.setId_vuelo(id_vuelo);
            objReservacion.setFecha_reservacion(fecha_reservacion);
            objReservacion.setAsiento(asiento);

            objModel.update(objReservacion);
        }
    }
}
