package racingmanager;   

import java.io.Serializable;
import java.util.Random;

public class GT extends Carro implements Serializable{
    private final double taxadesgaste=new Random().nextDouble()*0.5;
    
    public GT(String marca, String modelo, int cilindrada, int potencia){
        super(marca, modelo, cilindrada, potencia);
    }
    
    public GT(GT gt)
    {
        super(gt);
    }

    @Override
    public double tempoProximaVolta(Piloto p, Circuito c, int voltas){
        double tempo= new Random().nextDouble()*200-100;
        if(tempo>=0){
            tempo=((tempo+voltas*taxadesgaste)*0.85)+(this.cilindrada*15/4500);
        }
        else{
            tempo=((tempo-voltas*taxadesgaste)*0.85)-(this.cilindrada*15/4500);
        }
        if(tempo>=98 || tempo <=-98){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.08-p.getQualidade()*0.30;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.08-(p.getQualidade()+p.getFac_chuva()*0.30);
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.03-p.getQualidade()*0.07;
        }
        else{
            return tempo*0.03-(p.getQualidade()+p.getFac_chuva())*0.07;
        }
    }
    
    @Override
    public GT clone()
    {
        return new GT(this);
    }
    
    public boolean equals(GT gt)
    {
        return super.equals(gt);
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }

}
