package racingmanager;

import java.io.*;
import java.util.Random;

public class GT_Hibrido extends GT implements Serializable, Hibrido
{
    int potencia_elec;
    
    public GT_Hibrido(String marca, String modelo, int cilindrada, int potencia, int potencia_elec)
    {
        super(marca, modelo, cilindrada, potencia);
        this.potencia_elec= potencia_elec;
    }
    
    public GT_Hibrido(GT_Hibrido GT){
        super(GT);
        this.potencia_elec =GT.getPotenciaMotorElectrico();
    }
    
    @Override
    public int getPotenciaMotorElectrico()
    {
        return this.potencia_elec;
    }
    
    @Override
    public void setPotenciaMotorElectrico(int a){
        this.potencia_elec=a;
    }
    
    @Override
    public double tempoProximaVolta(Piloto p, Circuito c, int voltas){
        double tempo= new Random().nextDouble()*200-100;
        if(tempo>=0){
            tempo=((tempo+voltas*0.2)*0.8)+(this.cilindrada*15/4500);
        }
        else{
            tempo=((tempo-voltas*0.2)*0.8)-(this.cilindrada*15/4500);
        }
        if(tempo>=95 || tempo <=-95){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.08-p.getQualidade()*0.30-this.getPotenciaMotorElectrico()*0.015;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.08-(p.getQualidade()+p.getFac_chuva()*0.30-this.getPotenciaMotorElectrico()*0.015);
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.03-p.getQualidade()*0.07-this.getPotenciaMotorElectrico()*0.01;
        }
        else{
            return tempo*0.03-(p.getQualidade()+p.getFac_chuva())*0.07-this.getPotenciaMotorElectrico()*0.01;
        }
    }
    
    @Override
    public String toString() {
        return "Marca: " + this.marca + "\nModelo: " + this.modelo + "\nCilindrada: " + this.cilindrada + " cc\nPotência: "
                + this.potencia + " cvs\nPotência Motor Eléctrico: " + this.potencia_elec+" kw";
    }
}
    