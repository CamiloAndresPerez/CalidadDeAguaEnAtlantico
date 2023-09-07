package BaseDatos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection connection = null;
    
    public String obtener_url(){
        String sjdbc = "jdbc:sqlite";
        Path path = Paths.get("src/BaseDatos/BaseDeDatosCuerpoDeAgua.db");
        String url = sjdbc + ":\\" + path.toAbsolutePath();

        return url;
    }    
    public void conectar() throws SQLException{
        try{
            connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             System.out.println("Conexión exitosa!");
          }
          
          connection.close();
        }
        catch ( Exception ex ) {
          JOptionPane.showMessageDialog(null, "Ingresa los datos correctamente");
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }   
       
    }
    public void Agregar(String Query) throws SQLException{  
        try{
            connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             connection.prepareStatement(Query).executeUpdate();
             //Para INSERT, UPDATE or DELETE usar el metodo executeUpdate() y para SELECT usar el metodo executeQuery() que retorna un ResultSet.
             System.out.println("Conexión exitosa!");
          }
          connection.close();
        }
        catch ( Exception ex ) {
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }  
    }
    public ResultSet consultar(String Query) throws SQLException{  
        try{
          connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             ResultSet resultado = connection.prepareStatement(Query).executeQuery();
             //Para INSERT, UPDATE or DELETE usar el metodo executeUpdate() y para SELECT usar el metodo executeQuery() que retorna un ResultSet.
             System.out.println("Conexión exitosa!");
             return resultado;
          } 
          connection.close();
        }
        catch ( Exception ex ) {
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }
       
        return null;
    }

}
