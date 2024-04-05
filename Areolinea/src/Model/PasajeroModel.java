package Model;

import Database.CRUD;
import Database.ConfigDB;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        ?????  ????? = (????) obj;
        try {
            String sql = "INSERT INTO ????? (atributos) VALUES(?????);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, ???);
            objPrepare.setInt(2, ????);

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                ????.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"????? añadido correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return null;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> ????? = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ????? ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                ???? ??? = new ????;

                ????.setId(objResult.getInt("?????"));
                ????.setNombre(objResult.getString("?????"));

                ????.add(????);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return null;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        ??? ??? = (???) obj;

        boolean isUpdate = false;
        try {
            String sql = "UPDATE ???? SET nombre= ?  WHERE id??? =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,???);
            objPrepare.setInt(5,???);

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
        return null;
    }

    @Override
    public Boolean delete(Object obj) {
        ???? ???? = (????) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM ????? WHERE id???? =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,?????.getId());

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

    public ???? findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        ???? ????? = null;
        try {
            String sql  = "SELECT * FROM ?????? WHERE id????? =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                ????? = new ????();
                ????.setId(objResult.getInt("???"));
                ????.setNombre(objResult.getString("????"));
               }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return null;
    }
}
