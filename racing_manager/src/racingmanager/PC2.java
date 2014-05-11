package racingmanager;   

import java.io.Serializable;
import java.util.Random;

public class PC2 extends Carro implements Serializable{
    private final double mec= new Random().nextDouble()*10;
    
    public PC2(String marca, String modelo, int cilindrada, int potencia){
        super(marca, modelo, cilindrada, potencia);
    }   
    public PC2(PC2 pc2){
        super(pc2);
    }    
    @Override
    public double tempoProximaVolta(Piloto p, Circuito c, int voltas){
        double tempo= (new Random().nextDouble()*200-100)*0.7;
        double cil=20-(this.cilindrada*20/6000);
        
        if(tempo>=0){
            tempo=tempo+this.getMec()+cil;
        }
        else{
            tempo=tempo-this.getMec()-cil;
        }
        if(tempo>=82 || tempo <=-82){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.10-p.getQualidade()*0.25;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.10-(p.getQualidade()+p.getFac_chuva()*0.25);
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.04-p.getQualidade()*0.06;
        }
        else{
            return tempo*0.04-(p.getQualidade()+p.getFac_chuva())*0.06;
        }
    }   
    @Override
    public PC2 clone() {
        return new PC2(this);
    }
    public double getMec(){
        return this.mec;
    }
    public boolean equals(PC2 pc2){
        return super.equals(pc2);
    }
    @Override
    public String toString() {
        return super.toString();
    }
    
}
