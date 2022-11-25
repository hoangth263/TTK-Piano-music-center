/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangth.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class DBContext {
    public static Connection getConnection(){
        String url = "jdbc:sqlserver://localhost;databaseName=SE162042_Assigment;user=sa;password=12345";
        Connection con = null;
        try {
            //Loading a driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Creating a connection
            con =DriverManager.getConnection(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
}