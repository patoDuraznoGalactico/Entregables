package controller;

import Model.VueloModel;
import entity.Vuelo;

import javax.swing.*;

public class VueloController {
    public static void create(){
        VueloModel objModel = new VueloModel();

        String destino = JOptionPane.showInputDialog("Por favor ingrese el destino: ");
        String fecha_salida = JOptionPane.showInputDialog("Por favor ingrese la fecha de salida (YYYY-MM-DD): ");
        String hora_salida = JOptionPane.showInputDialog("Por favor ingrese la hora de salida (HH:MM): ");
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog(AvionController.getAll()+"\nPor favor ingrese el id del avion: "));


        Vuelo objVuelo = new Vuelo();
        objVuelo.setDestino(destino);
        objVuelo.setFecha_salida(fecha_salida);
        objVuelo.setHora_salida(hora_salida);
        objVuelo.setId_avion(id_avion);

        objVuelo = (Vuelo) objModel.insert(objVuelo);
        JOptionPane.showMessageDialog(null,objVuelo.toString());

    }

    public static String getAll(){
        VueloModel objModel = new VueloModel();
        String listVuelos = "Lista de todos los Vuelos: \n";
        for (Object i : objModel.findAll()){
            Vuelo objVuelo = (Vuelo) i;
            listVuelos += objVuelo.toString()+"\n";
        }
        return listVuelos;
    }
    public static void delete(){
        VueloModel objModel = new VueloModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id del vuelo a borrar: "));
        Vuelo objVuelo = (Vuelo) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objVuelo == null){
            JOptionPane.showMessageDialog(null,"Vuelo no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objVuelo.toString()+"\nEstas seguro que deseas borrar este vuelo?");
            if (confirm == 0){
                objModel.delete(objVuelo);
            }
        }
    }

    public static void update() {
        VueloModel objModel = new VueloModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll() + "\n Por favor ingrese el id del vuelo que desea actualizar: "));
        Vuelo objVuelo = (Vuelo) objModel.findById(idFind);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "vuelo no encontrado");
        } else {
            String destino = JOptionPane.showInputDialog("Por favor ingrese el destino nuevo: ", objVuelo.getDestino());
            String fecha_salida = JOptionPane.showInputDialog("Por favor ingrese la de fecha de salida nueva: ", objVuelo.getFecha_salida());
            String hora_salida = JOptionPane.showInputDialog("Por favor ingrese la de hora de salida nueva: ", objVuelo.getHora_salida());
            int id_avion = Integer.parseInt(JOptionPane.showInputDialog(AvionController.getAll()+"Por favor ingrese el id del avion nuevo: ", objVuelo.getId_avion()));

            objVuelo.setDestino(destino);
            objVuelo.setFecha_salida(fecha_salida);
            objVuelo.setHora_salida(hora_salida);
            objVuelo.setId_avion(id_avion);

            objModel.update(objVuelo);
        }
    }
}
