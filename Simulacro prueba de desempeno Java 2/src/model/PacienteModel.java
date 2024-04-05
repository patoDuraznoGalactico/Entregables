package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = "INSERT INTO paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES(?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFecha_nacimiento());
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPaciente.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Paciente añadido correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listPacientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                 Paciente objPaciente = new Paciente();

                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPacientes;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;

        boolean isUpdate = false;
        try {
            String sql = "UPDATE paciente SET nombre= ? , apellidos =?, fecha_nacimiento =?, documento_identidad =? WHERE id_paciente=?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected >0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Actualizado correctamente ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public Boolean delete(Object obj) {
        Paciente objPaciente = (Paciente) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM paciente WHERE id_paciente=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objPaciente.getId());

            int totalAffected = objPrepare.executeUpdate();
            if (totalAffected>0){
                isDelete=true;
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

    public Paciente findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Paciente objPaciente = null;
        try {
            String sql  = "SELECT * FROM paciente WHERE id_paciente =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

    public List<Object> findByDoc(String docNum){
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listPacientes = new ArrayList<>();
        try {
            String sql  = "SELECT * FROM paciente WHERE documento_identidad LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+docNum+"%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPacientes;
    }
}
