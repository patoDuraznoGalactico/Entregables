package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        try {
            String sql = "INSERT INTO cita (id_paciente_cita,id_medico_cita,fecha_cita,hora_cita,motivo) Values(?,?,?,?,?)";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objCita.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Cita añadida correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCitas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cita;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                listCitas.add(objCita);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCitas;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE cita SET id_paciente_cita = ?,id_medico_cita = ?,fecha_cita = ?,hora_cita = ?,motivo = ? WHERE id_cita = ?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Actualizado correctamente ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public Boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;

        boolean isDelete = false;
        try {
            String sql = "DELETE FROM cita WHERE id_cita= ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objCita.getId());

            int totalAffected = objPrepare.executeUpdate();
            if (totalAffected>0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Eliminado correctamente");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }
    public Cita findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM cita WHERE id_cita =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objCita= new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }
    public List<Object> findCitasbydate(String fecha_cita){
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCitasDate = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cita WHERE fecha_cita LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+fecha_cita+"%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                listCitasDate.add(objCita);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCitasDate;
    }

}

























