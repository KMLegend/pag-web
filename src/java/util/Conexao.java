/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection connection = null;

    public static Connection getConnection() {
        
        if (connection != null)
            return connection;
        else {
            try {
                
                String user = "postgres";
                String password = "123456";
                
                Class.forName("org.postgresql.Driver");// Para quem for usar Postgres
                
                connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/ueg_pgweb202301_v2",user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            //se deu tudo certo, cria a conexão e devolve para quem 
            //solicitou
            return connection;
        }

    }
}