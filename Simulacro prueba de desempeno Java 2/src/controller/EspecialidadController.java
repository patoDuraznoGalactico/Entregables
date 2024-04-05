package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;

public class EspecialidadController {

    public static void create(){
        EspecialidadModel objModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
        String descripcion = JOptionPane.showInputDialog("Por favor ingrese la descripcion: ");

        Especialidad objEspecialidad = new Especialidad();

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        objEspecialidad = (Especialidad) objModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null,objEspecialidad.toString());
    }

    public static String getAll(){
        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = "LISTA DE ESPECIALIDADES:\n";
        for (Object i : objModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidades += objEspecialidad.toString() +"\n";
        }
        return listEspecialidades;
    }
    public static void delete(){
        EspecialidadModel objModel = new EspecialidadModel();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\n Por favor ingrese el id de la Especialidad que desea eliminar: "));

        Especialidad objEspecialidad = objModel.findById(idDelete);
        int confirm = 1;
        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro que deseas borrar la especialidad? ");
            if (confirm == 0){
                objModel.delete(objEspecialidad);
            }
        }
    }

    public static void update(){
        EspecialidadModel objModel = new EspecialidadModel();

        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\n Por favor ingrese el id de la Especialidad que desea actualizar: "));

        Especialidad objEspecialidad = objModel.findById(idFind);

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
        }else {
            String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
            String descripcion = JOptionPane.showInputDialog("Por favor ingrese la descripcion: ");
            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objModel.update(objEspecialidad);
        }
    }



}
