package racingmanager;  

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

public class Equipa implements Comparable, Serializable{
    private String nome;
    private Piloto p1;
    private Piloto p2;
    private Carro car;
    
    public Equipa(){
        this.nome="Por Definir";
        this.p1=new Piloto();
        this.p2=new Piloto();
        this.car=new GT("Por Definir","Por Definir",0,0);
    }
    public Equipa(  String nomeEquipa,
                    String nomePil1,        String nomePil2,
                    String nacionalidade1,  String nacionalidade2,
                    int palmares,
                    int qualidade1,         int qualidade2,
                    double fac_chuva1,      double fac_chuva2,
                    String categoria, String marca, String modelo,int potencia){
        this.nome= nomeEquipa;
        this.p1= new Piloto(nomePil1,nacionalidade1,palmares,qualidade1,fac_chuva1);
        this.p2= new Piloto(nomePil2,nacionalidade2,palmares,qualidade2,fac_chuva2);
        Random ran = new Random();
        if(categoria.equals("PC1")){
            this.car= new PC1(marca,modelo,6000,potencia);
        }
        if(categoria.equals("PC2")){
            this.car= new PC2(marca,modelo,4000,potencia);
        }
        if(categoria.equals("GT")){
            this.car = new GT(marca,modelo,ran.nextInt(1500)+3000,potencia);
        }
        if(categoria.equals("SC")){
            this.car = new SC(marca,modelo,2500,potencia);
        }
    } 
    public Equipa(String nomeEquipa, Piloto p1,Piloto p2,String tipo, String marca, String modelo,int potencia){
        this.nome=nomeEquipa;
        this.p1= new Piloto(p1);
        this.p2= new Piloto(p2);
        Random ran = new Random();
        if(tipo.equals("PC1")){
            car= new PC1(marca,modelo,6000,potencia);
        }
        if(tipo.equals("PC2")){
            car= new PC2(marca,modelo,4000,potencia);
        }
        if(tipo.equals("GT")){
            car = new GT(marca,modelo,ran.nextInt(1500)+3000,potencia);
        }
        if(tipo.equals("SC")){
            car = new SC(marca,modelo,2500,potencia);
        }
    }
    public Equipa(String nomeEquipa, Piloto p1, Piloto p2, Carro c){
        this.nome=nomeEquipa;
        this.p1= new Piloto(p1);
        this.p2= new Piloto(p2);
        this.car= c;
    }
    public Equipa(String catCarro){
        String[] nomeEquipa = {"Equipa1","Equipa2","Equipa3","Equipa4","Equipa5"};
        String[] nome = {"Rodrigo","Joao","Martim","Afonso","Tomás","Goncalo","Francisco","Tiago","Diogo","Guilherme","Pedro","Miguel","Rafael","Gabriel","Santiago","Dinis","David","Duarte","Jose","Simao"};
        String[] apelido ={"Silva","Lima","Santos","Oliveira","Pinto","Ribeiro","Sousa","Almeida","Alves","Costa","Fernandes","Ferreira","Gomes","Gonçalves","Jesus","Marques","Moreira","Monteiro","Mendes","Gaspar"};
        String[] nacionalidade ={"Portugues","Espanhol","Frances","Ingles","Alemao","Americano","Canadiense"};
        String[] marca = {"Ferrari","Mercedes","Renault","Fiat","Lotus"};
        String[] modelo = {"Modelo1","Modelo2","Modelo3","Modelo4","Modelo4"};
        //Piloto p1,p2;
        String nome1,nome2,nac1,nac2,mar,mod;
        Random ran = new Random();
        this.nome=nomeEquipa[ran.nextInt(nomeEquipa.length)];
        //dados pilotos
        nome1 = nome[ran.nextInt(nome.length)] + " " + apelido[ran.nextInt(apelido.length)];
        nome2 = nome[ran.nextInt(nome.length)] + " " + apelido[ran.nextInt(apelido.length)];
        nac1 = nacionalidade[ran.nextInt(nacionalidade.length)];
        nac2 = nacionalidade[ran.nextInt(nacionalidade.length)];
        int palmares=ran.nextInt(20);
        int qualidade1=ran.nextInt(9)+1;
        int qualidade2=ran.nextInt(9)+1;
        double fac_chuva1 = ran.nextDouble();
        double fac_chuva2 = ran.nextDouble();
        p1= new Piloto(nome1,nac1,palmares,qualidade1,fac_chuva1);
        p2= new Piloto(nome2,nac2,palmares,qualidade2,fac_chuva2);
        //dados carro
        mar = marca[ran.nextInt(marca.length)];
        mod = modelo[ran.nextInt(modelo.length)];
        int potencia = ran.nextInt(150);
        if(catCarro.equals("PC1")){
            car= new PC1(mar,mod,6000,potencia+150);
        }
        if(catCarro.equals("PC2")){
            car= new PC2(mar,mod,4000,potencia+150);
        }
        if(catCarro.equals("GT")){
            car = new GT(mar,mod,ran.nextInt(1500)+3000,potencia+150);
        }
        if(catCarro.equals("SC")){
            car = new SC(mar,mod,2500,potencia+150);
        }
    }
    public Equipa(Equipa eq){
        this.nome = eq.getNome();
        this.p1 = eq.getPiloto1();
        this.p2 = eq.getPiloto2();
        this.car = eq.getCar();
    }
    public TreeMap<String,Equipa> leFicEquipas(ArrayList<Piloto> lPilotos, ArrayList<Carro> lCarros){
        TreeMap<String,Equipa> lista = new TreeMap<String,Equipa>();
        String linha;
        FileInputStream fstream = null;
        Iterator itPil = lPilotos.iterator();
        Iterator itCar = lCarros.iterator();
        Piloto p1;
        Piloto p2;
        Carro c;
        Equipa e;
        try
        {
            fstream = new FileInputStream("equipas.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
                   
            //Read File Line By Line
            while ((linha = br.readLine()) != null && itPil.hasNext() && itCar.hasNext())   {
                p1= (Piloto )itPil.next();
                if(itPil.hasNext()){
                    p2= (Piloto)itPil.next();
                    c= (Carro)itCar.next();
                    System.out.println(linha);
                    e = new Equipa(linha,p1,p2,c);
                    lista.put(linha,e);
                }
            }
            //Close the input stream
            in.close();
        }
        catch(IOException x)
        {
            
        }
        System.out.println(lista.toString());
        return lista;
    };   
    public void teste(){
        Piloto p= new Piloto();
        Carro c= new PC1("marca123","modelo123",2500,200);
        leFicEquipas(p.leFicPilotos(),c.leFicCarros());
    }
    public void setNome(String a){
        this.nome=a;
    }
    public String getNome(){
        return this.nome;
    }
    public void setPiloto1(Piloto p){
        this.p1=p;
    }
    public void setPiloto2(Piloto p){
        this.p2=p;
    }
    public Piloto getPiloto1(){
        return new Piloto(this.p1);
    }
    public Piloto getPiloto2(){
        return new Piloto(this.p2);
    }
    public Carro getCar() {
        return car;
    }
    public void setCar(Carro car) {
        this.car = car;
    }
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Equipa eq = (Equipa) obj;
        if (!this.nome.equals(eq.nome)) {
            return false;
        }
        if (!this.p1.equals(eq.p1)) {
            return false;
        }
        if (!this.p2.equals(eq.p2)) {
            return false;
        }
        if (!this.car.equals(eq.car)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        return "Dados da equipa "+this.nome+":\n   "+this.p1.toString()+"\n   "+this.p2.toString()+"\n   "+this.car.toString();
        
    }
    @Override
    public Equipa clone(){
        return new Equipa(this);
    }
    @Override
    public int compareTo(Object o) {
        Equipa e= (Equipa) o;
        return this.nome.compareTo(e.getNome());
    }
}
