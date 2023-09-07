package Modelo;

public class CuerpoDeAgua extends ObjetoGeografico{

    public CuerpoDeAgua(String nombre, int codigo, String municipio, float irca, String TipoDeAgua, String TipoCuerpoAgua) {
        super(nombre, codigo, municipio, irca, TipoDeAgua, TipoCuerpoAgua);
    }
   
    public String nivel(){
        
        String riesgo="No se ecuentra dentro de los estandares";
        if(getIrca() >80 && getIrca() <=100){
            riesgo="INVIABLE SANITARIAMENTE";
        }
        else if(getIrca() >35 && getIrca() <=80){
            riesgo="ALTO";
        }
        else if(getIrca()>14 && getIrca() <=35){
            riesgo="MEDIO";
        }
        else if(getIrca() >5 && getIrca() <=14){
            riesgo="BAJO";
        }
        else if(getIrca() >0 && getIrca() <=5){
            riesgo="SIN RIESGO";
        }
        
        return riesgo;
    }
    
}
