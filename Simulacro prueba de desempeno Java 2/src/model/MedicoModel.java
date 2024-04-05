package model;

import database.CRUD;
import database.ConfigDB;
import dtos.MedicoDTO;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = (Medico) obj;
        try {
            String sql = "INSERT INTO medico (nombre,apellidos,id_especialidad_medico ) VALUES(?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getId_especialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objMedico.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Medico añadido correctamente");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

        @Override
    public List<Object> findAll() {

        List<Object> listMedicos = new ArrayList<>();

        Connection objConnection  = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM medico;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();

                objMedico.setId(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad_medico"));

                listMedicos.add(objMedico);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
            ConfigDB.closeConnection();
            return listMedicos;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = (Medico) obj;
        boolean isUpdate= false;
        try {
            String sql = "UPDATE medico SET nombre= ? , apellidos =?, id_especialidad_medico  =? WHERE id_medico=?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getId_especialidad());
            objPrepare.setInt(4,objMedico.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected >0){
                isUpdate =true;
                JOptionPane.showMessageDialog(null, "Actualizado correctamente ");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public Boolean delete(Object obj) {
        Medico objMedico = (Medico) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM medico WHERE id_medico=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objMedico.getId());

            int totalAffected = objPrepare.executeUpdate();
            if (totalAffected>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Eliminado correctamente");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Medico findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = null;
        try {
            String sql  = "SELECT * FROM medico WHERE id_medico =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objMedico = new Medico();
                objMedico.setId(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad_medico"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }
    public List<Object> findByEsp(int id){
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listMedicosEsp = new ArrayList<>();
        try {
            String sql  = "SELECT medico.nombre, medico.apellidos, especialidad.nombre FROM medico INNER JOIN especialidad ON medico.id_especialidad_medico = especialidad.id_especialidad  WHERE especialidad.id_especialidad  = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                MedicoDTO objMedico = new MedicoDTO();
                objMedico.setNombre_medico(objResult.getString("medico.nombre"));
                objMedico.setApellidos_medico(objResult.getString("medico.apellidos"));
                objMedico.setNombre_especialidad(objResult.getString("especialidad.nombre"));

                listMedicosEsp.add(objMedico);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedicosEsp;
    }

}



















