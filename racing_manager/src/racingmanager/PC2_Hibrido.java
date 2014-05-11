package racingmanager;

import java.io.*;
import java.util.Random;

public class PC2_Hibrido extends PC2 implements Serializable, Hibrido
{
    int potencia_elec;
    
    public PC2_Hibrido(String marca, String modelo, int cilindrada, int potencia, int potencia_elec)
    {
        super(marca, modelo, cilindrada, potencia);
        this.potencia_elec= potencia_elec;
    }
    
    public PC2_Hibrido(PC2_Hibrido pc2){
        super(pc2);
        this.potencia_elec=pc2.getPotenciaMotorElectrico();
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
        double tempo= (new Random().nextDouble()*200-100)*0.72;
        double cil=20-(this.cilindrada*0.00333);
        
        if(tempo>=0){
            tempo=tempo+this.getMec()+cil+voltas*0.0775;
        }
        else{
            tempo=tempo-this.getMec()-cil-voltas*0.0775;
        }
        if(tempo>=79.5 || tempo <=-79.5){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.10-p.getQualidade()*0.25-this.getPotenciaMotorElectrico()*0.15;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.10-(p.getQualidade()+p.getFac_chuva()*0.25)-this.getPotenciaMotorElectrico()*0.15;
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.04-p.getQualidade()*0.06-this.getPotenciaMotorElectrico()*0.1;
        }
        else{
            return tempo*0.04-(p.getQualidade()+p.getFac_chuva())*0.06-this.getPotenciaMotorElectrico()*0.1;
        }
    }
    @Override
    public String toString() {
        return "Marca: " + this.marca + "\nModelo: " + this.modelo + "\nCilindrada: " + this.cilindrada + " cc\nPotência: "
                + this.potencia + " cvs\nPotência Motor Eléctrico: " + this.potencia_elec+" kw";
    }
}
