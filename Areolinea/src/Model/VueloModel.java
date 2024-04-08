package Model;

import Database.CRUD;
import Database.ConfigDB;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class    VueloModel implements CRUD {

    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Vuelo objVuelo = (Vuelo) obj;
        try {
            String sql = "INSERT INTO vuelo (destino,fecha_salida,hora_salida,id_avion_fk) VALUES(?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getId_avion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Vuelo añadido correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listVuelos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vuelo ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion_fk"));

                listVuelos.add(objVuelo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelos;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE vuelo SET destino= ?, fecha_salida=?,hora_salida=?,id_avion_fk=?  WHERE id_vuelo =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFecha_salida());
            objPrepare.setString(3,objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getId_avion());
            objPrepare.setInt(5,objVuelo.getId_vuelo());

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
        Vuelo objVuelo = (Vuelo) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM vuelo WHERE id_vuelo =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objVuelo.getId_vuelo());

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

    public Vuelo findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = null;
        try {
            String sql  = "SELECT * FROM vuelo WHERE id_vuelo =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objVuelo = new Vuelo();
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion_fk"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    public List<Object> findVuelosByDestiny(String destino) {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listVuelosByDestiny = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vuelo WHERE destino LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+destino+"%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion_fk"));

                listVuelosByDestiny.add(objVuelo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelosByDestiny;
    }
}
