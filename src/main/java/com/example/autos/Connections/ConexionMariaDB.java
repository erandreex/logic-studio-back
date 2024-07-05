package com.example.autos.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMariaDB {

    @SuppressWarnings("finally")
    public static Connection getConexion() {
        Connection con = null;

        String server = "localhost";
        String user = "root";
        String pass = "taliTakumi514";
        String port = "3306";
        String db = "logic_studio";

        try {
            String url = "jdbc:mariadb://" + server + ":" + port + "/" + db;
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            con = null;
        } catch (Exception ex) {
            con = null;
        } finally {
            return con;
        }
    }

}
