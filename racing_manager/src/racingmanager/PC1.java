package racingmanager;   

import java.io.Serializable;
import java.util.Random;

public class PC1 extends Carro implements Serializable{
    public PC1(String marca, String modelo, int cilindrada, int potencia){
        super(marca, modelo, cilindrada, potencia);
    }
    
    public PC1(PC1 pc1)
    {
        super(pc1);
    }
    
    @Override
    public double tempoProximaVolta(Piloto p, Circuito c, int voltas){
        double tempo= new Random().nextDouble()*200-100;
        if(tempo>=0){
            tempo=tempo-p.getQualidade();
        }
        else{
            tempo=tempo+p.getQualidade();
        }
        if(tempo>=96 || tempo <=-96){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.15-p.getQualidade()*0.25;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.15-(p.getQualidade()+p.getFac_chuva()*0.25);
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.05-p.getQualidade()*0.05;
        }
        else{
            return tempo*0.05-(p.getQualidade()+p.getFac_chuva())*0.05;
        }
    }
    
    @Override
    public PC1 clone()
    {
        return new PC1(this);
    }
    
    public boolean equals(PC1 pc1)
    {
        return super.equals(pc1);
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }
    
    /*boolean checkcil(int cil){
        if(cil==6000) {
            return true;
        }
        else {
            return false;
        }
    }*/
}
