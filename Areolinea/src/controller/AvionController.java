package controller;

import Model.AvionModel;
import entity.Avion;

import javax.swing.*;
import java.util.List;

public class AvionController {
    public static void create(){
        AvionModel objModel = new AvionModel();

        String modelo = JOptionPane.showInputDialog("Por favor ingrese el modelo: ");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese la capacidad: "));

        Avion objAvion = new Avion();
        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);

        objAvion = (Avion) objModel.insert(objAvion);
        JOptionPane.showMessageDialog(null,objAvion.toString());

    }

    public static String getAll(){
        AvionModel objModel = new AvionModel();
        String listAviones = "Lista de todos los aviones: \n";
        for (Object i : objModel.findAll()){
            Avion objAvion = (Avion) i;
            listAviones += objAvion.toString()+"\n";
        }
        return listAviones;
    }
    public static void delete(){
        AvionModel objModel = new AvionModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id del avion a borrar: "));
        Avion objAvion = (Avion) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objAvion == null){
            JOptionPane.showMessageDialog(null,"Avion no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objAvion.toString()+"\nEstas seguro que deseas borrar este avion?");
            if (confirm == 0){
                objModel.delete(objAvion);
            }
        }
    }

    public static void update() {
        AvionModel objModel = new AvionModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll() + "\n Por favor ingrese el id del avion que desea actualizar: "));
        Avion objAvion = (Avion) objModel.findById(idFind);
        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "Avion no encontrado");
        } else {
            String modelo = JOptionPane.showInputDialog("Por favor ingrese el modelo nuevo: ", objAvion.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese la capacidad nueva: ", objAvion.getCapacidad()));

            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objModel.update(objAvion);
        }
    }


}
