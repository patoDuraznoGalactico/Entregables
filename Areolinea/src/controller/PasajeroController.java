package controller;

import Model.PasajeroModel;
import entity.Pasajero;

import javax.swing.*;

public class PasajeroController {
    public static void create(){
        PasajeroModel objModel = new PasajeroModel();

        String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
        String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos: ");
        String documento_identidad = JOptionPane.showInputDialog("Por favor ingrese el documento de identidad: ");

        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellidos);
        objPasajero.setDocumento_identidad(documento_identidad);

        objPasajero = (Pasajero) objModel.insert(objPasajero);
        JOptionPane.showMessageDialog(null,objPasajero.toString());

    }

    public static String getAll(){
        PasajeroModel objModel = new PasajeroModel();
        String listPasajeros = "Lista de todos los pasajeros: \n";
        for (Object i : objModel.findAll()){
            Pasajero objPasajero = (Pasajero) i;
            listPasajeros += objPasajero.toString()+"\n";
        }
        return listPasajeros;
    }
    public static void delete(){
        PasajeroModel objModel = new PasajeroModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id del pasajero a borrar: "));
        Pasajero objPasajero = (Pasajero) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objPasajero == null){
            JOptionPane.showMessageDialog(null,"Pasajero no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objPasajero.toString()+"\nEstas seguro que deseas borrar este pasajero?");
            if (confirm == 0){
                objModel.delete(objPasajero);
            }
        }
    }

    public static void update() {
        PasajeroModel objModel = new PasajeroModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll() + "\n Por favor ingrese el id del pasajero que desea actualizar: "));
        Pasajero objPasajero = (Pasajero) objModel.findById(idFind);
        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "Pasajero no encontrado");
        } else {
            String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre nuevo: ", objPasajero.getNombre());
            String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos nuevos: ", objPasajero.getApellido());
            String documento_identidad = JOptionPane.showInputDialog("Por favor ingrese el numero de documento de identidad nuevo: ", objPasajero.getDocumento_identidad());

            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellidos);
            objPasajero.setDocumento_identidad(documento_identidad);

            objModel.update(objPasajero);
        }
    }
}
