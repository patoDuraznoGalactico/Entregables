package controller;

import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;

public class PacienteController {

    public static void create(){
        PacienteModel objModel = new PacienteModel();

        String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
        String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos: ");
        String fecha_nacimiento = JOptionPane.showInputDialog("Por favor ingrese la fecha de nacimiento (yyyy-MM-dd): ");
        String documento_identidad = JOptionPane.showInputDialog("Por favor ingrese el documento de indentidad: ");

        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(fecha_nacimiento);
        objPaciente.setDocumento_identidad(documento_identidad);

        objPaciente = (Paciente) objModel.insert(objPaciente);
        JOptionPane.showMessageDialog(null,objPaciente.toString());

    }

    public static String getAll(){
        PacienteModel objModel = new PacienteModel();
        String lisPacientes = "Lista de todos los pacientes: \n";
        for (Object i : objModel.findAll()){
            Paciente objPaciente = (Paciente) i;
            lisPacientes += objPaciente.toString()+"\n";
        }
            return lisPacientes;
    }
    public static String getByDoc(){
        PacienteModel objModel = new PacienteModel();
        String docNum = JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el numero de documento de del paciente: ");
        String listPacientes = "Resultados:\n";

        for (Object i: objModel.findByDoc(docNum)){
            Paciente objPaciente = (Paciente) i;
            listPacientes += objPaciente.toString()+"\n";
        }
        return listPacientes;
    }
    public static void delete(){
        PacienteModel objModel = new PacienteModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id del paciente a borrar: "));
        Paciente objPaciente = (Paciente) objModel.findById(id_eliminar);

        int confirm = 1;
        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"Paciente no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objPaciente.toString()+"\nEstas seguro que deseas borrar este paciete?");
            if (confirm == 0){
                objModel.delete(objPaciente);
            }
        }
    }

    public static void update(){
        PacienteModel objModel = new PacienteModel();
        int idFind = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\n Por favor ingrese el id del paciente que desea actualizar: "));
        Paciente objPaciente = (Paciente) objModel.findById(idFind);
        if (objPaciente ==null){
            JOptionPane.showMessageDialog(null,"Paciente no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre nuevo: ",objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos nuevos: ",objPaciente.getApellidos());
            String fecha_nacimiento = JOptionPane.showInputDialog("Por favor ingrese la fecha de nacimiento nueva (yyyy-MM-dd): ",objPaciente.getFecha_nacimiento());
            String documento_identidad = JOptionPane.showInputDialog("Por favor ingrese el documento de indentidad nuevo: ",objPaciente.getDocumento_identidad());

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objModel.update(objPaciente);
        }

    }
}
