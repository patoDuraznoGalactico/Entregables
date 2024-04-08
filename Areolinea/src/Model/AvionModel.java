package Model;

import DTO.ReservacionDTO;
import Database.CRUD;
import Database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {
    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Avion objAvion = (Avion) obj;
        try {
            String sql = "INSERT INTO avion (modelo,capacidad) VALUES(?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2, objAvion.getCapacidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAvion.setId_avion(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Avion añadido correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listAviones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM avion ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                listAviones.add(objAvion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE avion SET modelo= ?, capacidad=?  WHERE id_avion =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());
            objPrepare.setInt(3,objAvion.getId_avion());

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
        Avion objAvion = (Avion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM avion WHERE id_avion =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objAvion.getId_avion());

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

    public Avion findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Avion objAvion = null;
        try {
            String sql  = "SELECT * FROM avion WHERE id_avion =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }
    public List<String> generateSeats(int id){
        Connection objConnection  = ConfigDB.openConnection();
        List<String> listSeats = new ArrayList<>();
        try {
            String sql  = "SELECT avion.capacidad FROM avion JOIN vuelo ON avion.id_avion = vuelo.id_avion_fk  WHERE vuelo.id_vuelo  = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

             if (objResult.next()){
                int capacidad = objResult.getInt("avion.capacidad");
                for (int i=1;i<capacidad+1;i++){
                    ReservacionDTO objDTO = new ReservacionDTO();
                    listSeats.add("p"+i);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listSeats;
    }

}
