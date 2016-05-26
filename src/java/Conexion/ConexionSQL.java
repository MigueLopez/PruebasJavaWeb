/*
 * Clase para realizar la conexión a la base de Datos
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSQL {
    
    private Connection conexion;
    
    public ConexionSQL(){
        String db;
        try {
            Class.forName("com.mysql.jdbc.Driver");  //ClassNotFoundException
            db = "jdbc:mysql://localhost/javawebtest";
            conexion = DriverManager.getConnection(db,"root","mustaine"); //SQLException
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int validarUsuario(String login, String password){
        String comando;
        
        comando = "SELECT (1) FROM Usuario WHERE Login = '" + login + "' AND Password = '" + password + "'";
        
        System.out.print(comando);
        
        try {
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(comando);
            
            if (rs.next())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        } catch (SQLException ex) {
            return -1;
        }
    }
    
}
