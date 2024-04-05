package database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://b0r52mdgt9ugonyyu1rg-mysql.services.clever-cloud.com:3306/b0r52mdgt9ugonyyu1rg   ";
            String user = "uoud0etkusnadgja";
            String password = "Vv3L1mghrvZLj9kTxLUZ";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente!!!!");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objConnection;
    }
    public static void closeConnection(){
        try{
            if (objConnection != null){
                objConnection.close();
                System.out.println("Se finalizo la conexión con éxito");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
