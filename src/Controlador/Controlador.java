package Controlador;
import BaseDatos.Conexion;
import Modelo.CuerpoDeAgua;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador {
    
    ArrayList<CuerpoDeAgua> CuerposDeAgua = new ArrayList<>();
    Conexion conect = new Conexion();
    
    
    public void agregar(String nombre, int codigo, String municipio, float irca, String TipoDeAgua, String TipoCuerpoAgua) throws SQLException{
      
       String Query_Agregar = "INSERT INTO CuerpoDeAgua(id,nombre,municipio,TipoCuerpoAgua,TipoAgua,Irca) VALUES("+codigo+",'"+nombre+"','"+municipio+"','"+TipoCuerpoAgua+"','"+TipoDeAgua+"',"+irca+");";       
      
       conect.Agregar(Query_Agregar);     
    }
    
    public String consultar() throws SQLException{
       String sentencia = "SELECT * FROM CuerpoDeAgua;";
       ResultSet Resultado = conect.consultar(sentencia); 
       String Consulta="";
       
       while(Resultado.next()){
           Consulta += Resultado.getString("id") + " "
                   + Resultado.getString("nombre") + " "
                   + Resultado.getString("municipio") + " "
                   + Resultado.getString("TipoCuerpoAgua")+ " "
                   + Resultado.getString("TipoAgua") + " "
                   + Resultado.getString("Irca") + " "
                   + "\n";
       }
       
       return Consulta;
       
    }
    public String consultarPorId(int id) throws SQLException{
       String sentencia = "SELECT * FROM CuerpoDeAgua WHERE id="+id+";" ;
       ResultSet Resultado = conect.consultar(sentencia); 
       String Consulta="";
       
       while(Resultado.next()){
           Consulta += Resultado.getString("id") + " "
                   + Resultado.getString("nombre") + " "
                   + Resultado.getString("municipio") + " "
                   + Resultado.getString("TipoCuerpoAgua")+ " "
                   + Resultado.getString("TipoAgua") + " "
                   + Resultado.getString("Irca") + " "
                   + "\n";
       }
       
       return Consulta;
    }
    
    
    public void editar(String nombre, int codigo, String municipio, float irca, String TipoDeAgua, String TipoCuerpoAgua) throws SQLException{
       String sentenciaEditar = "UPDATE CuerpoDeAgua "
               + "SET " + " nombre='"+nombre+"', municipio='"+municipio+"',TipoCuerpoAgua='"+TipoCuerpoAgua+"',TipoAgua='"+TipoDeAgua+"',Irca="+irca
               + " WHERE  id=" + codigo + ";";
       conect.Agregar(sentenciaEditar);     
    }
    
    
    //Metodo para eliminar
    public void Eliminar(int id) throws SQLException{
       String Query_Eliminar = "DELETE FROM CuerpoDeAgua WHERE id="+id+";" ;
       conect.Agregar(Query_Eliminar);  
    }    
    
    //Necesitamos llenar el array para poder procesar todos los datos, por medio de sus metodos.
    public void LlenarArray() throws SQLException{
       CuerposDeAgua.clear();
       String sentencia = "SELECT * FROM CuerpoDeAgua;";
       ResultSet Resultado = conect.consultar(sentencia); 
       while(Resultado.next()){
        CuerpoDeAgua cuerpoagua = new CuerpoDeAgua(Resultado.getString("nombre"),Integer.parseInt(Resultado.getString("id")),Resultado.getString("municipio"),Float.parseFloat(Resultado.getString("Irca")),Resultado.getString("TipoAgua"),Resultado.getString("TipoCuerpoAgua"));
        CuerposDeAgua.add(cuerpoagua);
       }
    }
    
    
    public String menor(){ 
        String datos_menor="";
        float menor = CuerposDeAgua.get(0).getIrca();
        String nomb = CuerposDeAgua.get(0).getNombre();
        int cod = CuerposDeAgua.get(0).getCodigo();
        for (int i = 0; i < CuerposDeAgua.size(); i++) {
             if (CuerposDeAgua.get(i).getIrca() < menor){
                 menor = CuerposDeAgua.get(i).getIrca();
                 nomb = CuerposDeAgua.get(i).getNombre();
                 cod = CuerposDeAgua.get(i).getCodigo();
             }
        }
       datos_menor = nomb + " " + cod;  
       return datos_menor;
   }
    public String nivel(){
        String riesgo="";
        
        for (int i = 0; i < CuerposDeAgua.size(); i++) {
            riesgo += CuerposDeAgua.get(i).nivel() + "\n";
        }
        
      return riesgo;
    
    }
    public String medios(){
        String medios ="NA";
        for (int i = 0; i < CuerposDeAgua.size(); i++) {
            if("MEDIO".equals(CuerposDeAgua.get(i).nivel())){
                medios += CuerposDeAgua.get(i).getNombre() + "\n";
            }
        }
        if(medios.length() > 2){
            medios = medios.replace("NA","");
        }else{
            medios += "\n";
        }
        return medios;
    }
    
    
    public String menorDeMedio(){
        String menores;
        int contador = 0;
        for (int i = 0; i < CuerposDeAgua.size(); i++) {
            if(CuerposDeAgua.get(i).getIrca() >= 0 && CuerposDeAgua.get(i).getIrca() <= 35){
                contador++;
            }
        }
        menores = contador + "\n";
        return menores;
    }
    
 
   
    
    
}
