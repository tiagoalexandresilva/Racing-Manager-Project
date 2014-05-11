package racingmanager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Carro implements Serializable{
    String marca, modelo;
    int cilindrada, potencia;
    
    public abstract double tempoProximaVolta(Piloto p, Circuito c, int voltas);
    
    public Carro(String marca, String modelo, int cilindrada, int potencia){
        this.marca=marca;
        this.modelo=modelo;
        this.cilindrada=cilindrada;
        this.potencia=potencia;
    }
    
    public Carro(Carro c)
    {
        this.marca = c.getMarca();
        this.modelo = c.getModelo();
        this.cilindrada = c.getCilindrada();
        this.potencia = c.getPotencia();
    }
    
    public int getCilindrada()
    {
        return this.cilindrada;
    }
    
    public int getPotencia()
    {
        return this.potencia;
    }
    
    public String getMarca()
    {
        return this.marca;
    }
    
    public String getModelo()
    {
        return this.modelo;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        
        if((obj==null) || (obj.getClass()!=this.getClass())){
            return false;
        }
        else{
            Carro car = (Carro)obj;
            return this.marca.equals(car.getMarca()) && this.modelo.equals(car.getModelo()) 
                    && this.cilindrada==car.getCilindrada() && this.potencia==car.getPotencia();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.marca);
        hash = 19 * hash + Objects.hashCode(this.modelo);
        hash = 19 * hash + this.cilindrada;
        hash = 19 * hash + this.potencia;
        return hash;
    }
    
    @Override
    public String toString(){
        return "Marca: "+this.marca+"\nModelo: "+this.modelo+"\nCilindrada: "+this.cilindrada+"\nPotencia: "+this.potencia;
    }

    public ArrayList<Carro> leFicCarros(){
        ArrayList<Carro> lista = new ArrayList<>();
        Carro car = new PC2("marcaX", "modeloX", 3111, 111);
        String linha;
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("carros.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String[] res;

            String marca, modelo, tipo;
            int cilindrada = 0, potencia = 0;
            //Read File Line By Line
            while ((linha = br.readLine()) != null) {
                res = linha.split(",");
                tipo = res[0];
                marca = res[1];
                modelo = res[2];
                cilindrada = Integer.parseInt(res[3]);
                potencia = Integer.parseInt(res[4]);

                if (tipo.equals("PC1")) {
                    car = new PC1(marca, modelo, cilindrada, potencia);
                }
                if (tipo.equals("PC2")) {
                    car = new PC2(marca, modelo, cilindrada, potencia);
                }
                if (tipo.equals("GT")) {
                    car = new GT(marca, modelo, cilindrada, potencia);
                }
                if (tipo.equals("SC")) {
                    car = new SC(marca, modelo, cilindrada, potencia);
                }
                lista.add(car);
            }
//Close the input stream
            in.close();
        } catch (IOException e) {
        }
        return lista;
    }
 
}
