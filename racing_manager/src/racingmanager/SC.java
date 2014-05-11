package racingmanager;   

import java.io.Serializable;
import java.util.Random;

public class SC extends Carro implements Serializable{
    
    public SC(String marca, String modelo, int cilindrada, int potencia){
        super(marca, modelo, cilindrada, potencia);
    }
    
    public SC(SC sc)
    {
        super(sc);
    }

    @Override
    public double tempoProximaVolta(Piloto p, Circuito c, int voltas){
        double tempo= (new Random().nextDouble()*200-100);
        double cil=(this.cilindrada*20/4500);
        if(tempo>=0){
            tempo=(tempo*0.5)-cil- p.getQualidade()*0.3;
        }
        else{
            tempo=(tempo*0.5)+cil+ p.getQualidade()*0.3;
        }
        int prob= new Random().nextInt(100);
        if(prob>66){
            tempo=tempo*(-1);
        }
        if(tempo>=36.5 || tempo <=-36.5){
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
    public SC clone()
    {
        return new SC(this);
    }
    
    public boolean equals(SC sc)
    {
        return super.equals(sc);
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }

}
