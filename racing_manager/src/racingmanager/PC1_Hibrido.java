package racingmanager;

import java.io.*;
import java.util.Random;


public class PC1_Hibrido extends PC1 implements Serializable, Hibrido{
    int potencia_elec;
    
    public PC1_Hibrido(String marca, String modelo, int cilindrada, int potencia, int potencia_elec)
    {
        super(marca, modelo, cilindrada, potencia);
        this.potencia_elec= potencia_elec;
    }
    
    public PC1_Hibrido(PC1_Hibrido pc1){
        super(pc1);
        this.potencia_elec =pc1.getPotenciaMotorElectrico();
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
        double tempo= (new Random().nextDouble()*200-100)*0.98;
        if(tempo>=0){
            tempo=tempo+voltas*0.1-p.getQualidade();
        }
        else{
            tempo=tempo-voltas*0.1+p.getQualidade();
        }
        if(tempo>=96 || tempo <=-96){
            return 1000;
        }
        else if(tempo>0 && c.getChuva()==false){
            return tempo*0.15-p.getQualidade()*0.25-this.getPotenciaMotorElectrico()*0.15;
        }
        else if(tempo>0 && c.getChuva()==true){
            return tempo*0.15-(p.getQualidade()+p.getFac_chuva()*0.25-this.getPotenciaMotorElectrico()*0.15);
        }
        else if(tempo<0 && c.getChuva()==false){
            return tempo*0.05-p.getQualidade()*0.05-this.getPotenciaMotorElectrico()*0.1;
        }
        else{
            return tempo*0.05-(p.getQualidade()+p.getFac_chuva())*0.05-this.getPotenciaMotorElectrico()*0.1;
        }
    }
    
    @Override
    public String toString() {
        return "Marca: " + this.marca + "\nModelo: " + this.modelo + "\nCilindrada: " + this.cilindrada + " cc\nPotência: "
                + this.potencia + " cvs\nPotência Motor Eléctrico: " + this.potencia_elec+" kw";
    }
}
