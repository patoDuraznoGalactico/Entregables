package Model;

import Database.CRUD;
import Database.ConfigDB;
import controller.AvionController;
import entity.Reservación;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {

    public Object insert(Object obj) {
        Connection objConnection =  ConfigDB.openConnection();

        Reservación objReservacion = (Reservación) obj;
        try {
            String sql = "INSERT INTO reservacion (id_pasajero_fk,id_vuelo_fk,fecha_reservacion,asiento) VALUES(?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1, objReservacion.getId_pasajero());
            objPrepare.setInt(2, objReservacion.getId_vuelo());
            objPrepare.setString(3, objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objReservacion.setId_reservacion(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Reservacion añadida correctamente");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listReservaciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservacion ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservación objReservacion = new Reservación();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo_fk"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                listReservaciones.add(objReservacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservaciones;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reservación objReservacion = (Reservación) obj;

        boolean isUpdate = false;
        try {
            String sql = "UPDATE reservacion SET id_pasajero_fk= ?,id_vuelo_fk=?,fecha_reservacion=?,asiento= ?  WHERE id_reservacion =?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());
            objPrepare.setInt(5,objReservacion.getId_reservacion());


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
        Reservación objReservacion = (Reservación) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM reservacion WHERE id_reservacion =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objReservacion.getId_reservacion());

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

    public Reservación findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Reservación objReservacion = null;
        try {
            String sql  = "SELECT * FROM reservacion WHERE id_reservacion =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objReservacion = new Reservación();
                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo_fk"));
                objReservacion.setAsiento(objResult.getString("asiento"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }

    public List<Object> findAllByVuelo(int id) {
        Connection objConnection  = ConfigDB.openConnection();
        List<Object> listReservaciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservacion WHERE id_vuelo_fk =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservación objReservacion = new Reservación();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo_fk"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                listReservaciones.add(objReservacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservaciones;
    }
    public List<String> generateSeatsFree(int id_vuelo){
        Connection objConnection  = ConfigDB.openConnection();
        List<String> listSeats = new ArrayList<>();
        AvionModel objModelAvion = new AvionModel();
        List<String> listSeatsFree = objModelAvion.generateSeats(id_vuelo);
        try {

            for(Object i: this.findAll()){
                Reservación objReservacion = (Reservación) i;
                if (objReservacion.getId_vuelo()==id_vuelo){
                    listSeats.add(objReservacion.getAsiento());
                }
            }
            System.out.println(listSeats);
            System.out.println(listSeatsFree);

            listSeatsFree.removeAll(listSeats);

            System.out.println(listSeatsFree);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        if (listSeatsFree.isEmpty()){
            return null;
        }
        return listSeatsFree;
    }
    public boolean verificateSeats(String asiento, List<String> list){
        Connection objConnection  = ConfigDB.openConnection();
        try {
            for (String i :list){
                if (i.equals(asiento)){
                    return true;
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return false;

    }


}
