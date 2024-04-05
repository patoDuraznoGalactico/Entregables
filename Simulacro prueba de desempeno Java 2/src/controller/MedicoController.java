package controller;

import dtos.MedicoDTO;
import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;

public class MedicoController {

    public static void create(){
        MedicoModel objModel = new MedicoModel();

        String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
        String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos: ");
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAll()+"\nPor favor ingrese el id de la especialidad: "));

        Medico objMedico = new Medico();
        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellidos);
        objMedico.setId_especialidad(id_especialidad);

        objMedico = (Medico) objModel.insert(objMedico);

        JOptionPane.showMessageDialog(null, objMedico.toString());
    }

    public static String getAll(){
        MedicoModel objModel = new MedicoModel();

        String listMedicos = "Lista de todos Medicos:\n";
        for (Object i: objModel.findAll()){
            Medico objMedico = (Medico) i;
            listMedicos += objMedico.toString()+"\n";
        }
        return listMedicos;
    }
    public static String getAllByEsp(){
        MedicoModel objModel = new MedicoModel();
        int id_esp = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAll()+"\nPor favor ingrese el id de de la especialidad: "));
        String listMedicos = "Lista de Medicos por especialidad:\n";

        for (Object i: objModel.findByEsp(id_esp)){
            MedicoDTO objMedicoDTO = (MedicoDTO) i;
            listMedicos += objMedicoDTO.toString()+"\n";
        }   
        return listMedicos;
    }
    public static void delete(){
        MedicoModel objModel = new MedicoModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id del medico a borrar: "));
        Medico objMedico = (Medico) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"Medico no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objMedico.toString()+"\n¿Estas seguro que deseas borrar este medico? ");
            if (confirm ==0){
                objModel.delete(objMedico);
            }
        }
    }

    public static void update(){
        MedicoModel objModel = new MedicoModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\n Por favor ingrese el id del medico que desea actualizar: "));
        Medico objMedico = (Medico) objModel.findById(idFind);

        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"Medico no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre nuevo: ",objMedico.getNombre());
            String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos nuevos: ",objMedico.getApellidos());
            int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAll()+"\nPor favor ingrese el id de la especialidad: ",objMedico.getId_especialidad()));

            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setId_especialidad(id_especialidad);
            objModel.update(objMedico);
        }

    }
}
