package controller;

import dtos.MedicoDTO;
import entity.Cita;
import entity.Medico;
import model.CitaModel;
import model.MedicoModel;

import javax.swing.*;

public class CitaController {
    public static void create(){
        CitaModel objModel = new CitaModel();

        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAll()+"\nPor favor ingrese el id del paciente: "));
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAll()+"\nPor favor ingrese el id del medico: "));
        String fecha_cita = JOptionPane.showInputDialog("Por favor ingrese la fecha de la cita  (YYYY-MM-DD): ");
        String hora_cita = JOptionPane.showInputDialog("Por favor ingrese la hora de la cita (HH:MM):");
        String motivo = JOptionPane.showInputDialog("Por favor ingrese el motivo de la cita: ");

        Cita objCita = new Cita();
        objCita.setId_paciente(id_paciente);
        objCita.setId_medico(id_medico);
        objCita.setFecha_cita(fecha_cita);
        objCita.setHora_cita(hora_cita);
        objCita.setMotivo(motivo);

        objCita = (Cita) objModel.insert(objCita);
        JOptionPane.showMessageDialog(null, objCita.toString());
    }

    public static String getAll(){
        CitaModel objModel = new CitaModel();

        String listCitas= "Lista de todas las citas: \n";
        for (Object i: objModel.findAll()){
            Cita objCita = (Cita) i;
            listCitas += objCita.toString()+"\n";
        }
        return listCitas;
    }
    public static String getCitasDate(){
        CitaModel objModel = new CitaModel();
        String dateCita = JOptionPane.showInputDialog("Por favor ingrese la fecha de la citas que deseas buscar (YYYY-MM-DD): ");

        String listCitasDate= "Lista de las citas: \n";
        for (Object i: objModel.findCitasbydate(dateCita)){
            Cita objCita = (Cita) i;
            listCitasDate += objCita.toString()+"\n";
        }
        return listCitasDate;
    }
    public static void delete(){
        CitaModel objModel = new CitaModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id de la cita a borrar: "));
        Cita objCita = (Cita) objModel.findById(id_eliminar);
        int confirm = 1;
        if (objCita == null){
            JOptionPane.showMessageDialog(null,"Cita no encontrada");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,objCita.toString()+"\n¿Estas seguro que deseas borrar esta cita? ");
            if (confirm == 0){
                objModel.delete(objCita);
            }
        }
    }

    public static void update(){
        CitaModel objModel = new CitaModel();
        int id_eliminar = Integer.parseInt(JOptionPane.showInputDialog(getAll()+"\nPor favor ingrese el id de la cita a actualizar: "));
        Cita objCita = (Cita) objModel.findById(id_eliminar);
        if (objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        }else{
            int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAll()+"\nPor favor ingrese el id del paciente nuevo: ",objCita.getId_paciente()));
            int id_medico = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAll()+"\nPor favor ingrese el id del medico nuevo: ",objCita.getId_medico()));
            String fecha_cita = JOptionPane.showInputDialog("Por favor ingrese la fecha de la cita nueva (YYYY-MM-DD): ",objCita.getFecha_cita());
            String hora_cita = JOptionPane.showInputDialog("Por favor ingrese la hora de la cita nueva (HH:MM):",objCita.getHora_cita());
            String motivo = JOptionPane.showInputDialog("Por favor ingrese el motivo de la cita nueva: ",objCita.getMotivo());


            objCita.setId_paciente(id_paciente);
            objCita.setId_medico(id_medico);
            objCita.setFecha_cita(fecha_cita);
            objCita.setHora_cita(hora_cita);
            objCita.setMotivo(motivo);
            objModel.update(objCita);
        }

    }
}
