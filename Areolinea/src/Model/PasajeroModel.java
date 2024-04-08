package Model;

import Database.CRUD;
import Database.ConfigDB;
import entity.Pasajero;
import entity.Reservación;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;
        try {
            String sql = "INSERT INTO pasajero (nombre,apellido,documento_identidad) VALUES(?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId_pasajero(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Pasajero añadido correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listPasajeros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pasajero ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE pasajero SET nombre= ?, apellido=?, documento_identidad=?  WHERE id_pasajero =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());
            objPrepare.setInt(4,objPasajero.getId_pasajero());

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
        Pasajero objPasajero = (Pasajero) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM pasajero WHERE id_pasajero =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objPasajero.getId_pasajero());

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

    public Pasajero findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;
        try {
            String sql  = "SELECT * FROM pasajero WHERE id_pasajero =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
               }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }
    public List<Object> findByName(String nombre) {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listPasajeros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pasajero WHERE nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+nombre+"%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }
}
