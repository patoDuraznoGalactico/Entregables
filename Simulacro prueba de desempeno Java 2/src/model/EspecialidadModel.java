package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;
        try{
            String sql = "INSERT INTO especialidad (nombre,descripcion) VALUES(?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objEspecialidad.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null," Especialidad añadida :)");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEspecialidades = new ArrayList<>();

        Connection objConnection  = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                listEspecialidades.add(objEspecialidad);
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidades;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isUpdate = false;

        try {
            String sql = "UPDATE especialidad SET nombre= ? , descripcion =? WHERE id_especialidad=?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());
            objPrepare.setInt(3,objEspecialidad.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected>0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Actualizado correctamente :)");
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

        Especialidad objEspecialidad = (Especialidad) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspecialidad.getId());

            int totalAffected = objPrepare.executeUpdate();
            if(totalAffected>0){
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

    public Especialidad findById(int id){

        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM especialidad WHERE id_especialidad=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setId(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }
}
