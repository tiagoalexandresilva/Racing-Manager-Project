package racingmanager;  

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Campeonato implements Serializable{
    private TreeMap<String,Equipa> equipas;
    private TreeMap<Integer,Circuito> circuitos;
    private TreeMap<Equipa, Integer> classificacao; //classificacao geral
    private int ncorrida;
    
    public Campeonato(){
        Piloto p= new Piloto();
        Carro c= new PC1("marca123","modelo123",2500,200);
        Equipa e= new Equipa();
        this.equipas = e.leFicEquipas(p.leFicPilotos(),c.leFicCarros());
        this.circuitos = leFicCircuitos();
        this.classificacao = new TreeMap<>();
        this.ncorrida=1;
    }    
    public Campeonato(Campeonato cp){
        
    }

    public int getNcorrida() {
        return ncorrida;
    }
    public void setNcorrida(int ncorrida) {
        this.ncorrida = ncorrida;
    }
    public TreeMap<String,Equipa> getEquipas(){
        TreeMap<String,Equipa> lequipas = new TreeMap<>();
        for(Equipa eq : this.equipas.values()){
            lequipas.put(eq.getNome(),eq.clone());
        }
        return lequipas;
    }
    public void geraEquipas() throws NaoExisteFileException{
        // Open the file
        Piloto pil;
        FileInputStream fstream = null;
        //System.out.println("\n\n\n AQUI \n");
        try
        {
            fstream = new FileInputStream("pilotos.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String strLine;
        
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
              //System.out.println("LINHA" +strLine);
              pil= new Piloto(strLine);
              //System.out.println(pil.toString());
            }
        
            //Close the input stream
            in.close();
        }
        catch(IOException e)
        {
            
        }
        
        
        //while(in.hasNextLine()){
        //    piloto=in.nextLine();
        //    System.out.println("Linha "+ piloto);
        //}
    }    
    public TreeMap<Integer,Circuito> leFicCircuitos(){
        Circuito cir = new Circuito();
        FileInputStream fstream = null;
        TreeMap<Integer,Circuito> map = new TreeMap<>();
        int contador=0;
        try
        {
            fstream = new FileInputStream("circuitos.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String linha;
        
            //Read File Line By Line
            while ((linha = br.readLine()) != null)   {
              // Print the content on the console
              //System.out.println("LINHA" + linha);
              cir= new Circuito(linha,contador);
              map.put(contador,cir);
              contador++;
            }
        
            //Close the input stream
            in.close();
        }
        catch(IOException e)
        {
            
        }
       // System.out.println(cir.toString()+"\n"+map.toString());
        
        return map;
    }
    public void inicializaClass(){
        for(Map.Entry<String, Equipa> entry : this.equipas.entrySet()){
            this.classificacao.put(entry.getValue(), 0);
        }
    }
     public TreeMap<Integer,Circuito> getCircuitos(){
        TreeMap<Integer,Circuito> lcircuitos = new TreeMap<>();
        for (Map.Entry<Integer, Circuito> entry : this.circuitos.entrySet()) {
            lcircuitos.put(entry.getKey(),entry.getValue().clone());
        }
        return lcircuitos;
    }
    public void addPontos(Equipa e, int i){
        if(i<=5){
            int pontos=this.classificacao.get(e);
            switch(i){
                case 1: 
                    this.classificacao.put(e, pontos+12);
                    e.getPiloto1().incPalmares();
                    e.getPiloto2().incPalmares();
                    break;
                case 2:
                    this.classificacao.put(e, pontos+8);
                    break;
                case 3:
                    this.classificacao.put(e, pontos+5);
                    break;
                case 4:
                    this.classificacao.put(e, pontos+3);
                    break;
                case 5:
                    this.classificacao.put(e, pontos+2);
                    break;
            }
        }
    }    
    public void addEquipacl(Equipa e){
        this.classificacao.put(e, 0);
    }
    public void addEquipa(Equipa e){
        this.equipas.put(e.getNome(), e);
    }
    public void addCircuito(Circuito c){
        this.circuitos.put(this.circuitos.size()+1, c);
    }
    public void printClassificacao(){
        int i=1;
        int[] pos = {1,1,1,1};
        System.out.println("\n:::::Classificação do Campeonato:::");
        System.out.println("-------------------------------------");
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for (Map.Entry<Equipa, Integer> entry : classord){
            switch (entry.getKey().getCar().getClass().getSimpleName()) {
                case "PC1": case "PC1_Hibrido":
                    System.out.println(i+"º ("+pos[0]+"º)"+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+entry.getValue());
                    pos[0]++;
                    break;
                case "PC2": case "PC2_Hibrido":
                    System.out.println(i+"º ("+pos[1]+"º)"+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+entry.getValue());
                    pos[1]++;
                    break;
                case "GT": case "GT_Hibrido":
                    System.out.println(i+"º ("+pos[2]+"º)"+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+entry.getValue());
                    pos[2]++;
                    break;
                default:
                    System.out.println(i+"º ("+pos[3]+"º)"+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+entry.getValue());
                    pos[3]++;
                    break;
            }
            i++;
        }
    }
    public void printEquipas(){
        System.out.println("\n:::::Equipas Participantes:::::");
        System.out.println("---------------------------------");
        for(Map.Entry<String, Equipa> entry : this.equipas.entrySet()){
            System.out.println(entry.getValue().toString());
        }
    }
    public void printCircuitos(){
        System.out.println("\n::::Circuitos do Campeonato::::");
        System.out.println("--------------------------------");
        for(Map.Entry<Integer, Circuito> entry : this.circuitos.entrySet()){
            System.out.println(entry.getKey()+"º "+entry.getValue().toString());
        }
    }
    public void printClassCircuitoX(int x){
        if(x<1 || x>this.circuitos.size()){
            System.out.println("Número de Circuito Inválido!");
        }
        else if(x>=this.ncorrida){
            System.out.println("Corrida ainda não realizada!");
        }
        else{
            getCircuitoX(x).printClassificacao();
        }
    }
    public String proxCircuito(int x){
        if(x>this.circuitos.size()){
            return "Não existem mais corridas";
        }
        else{    
            return "A próxima corrida será a nº "+this.ncorrida+" "+this.getCircuitoX(this.ncorrida).getNome();
        }
    }
    public Circuito getCircuitoX(int x){
        return this.circuitos.get(x);
    }
    public ArrayList<Map.Entry<Equipa, Integer>> getClassificaoGeral() {
        int i = 1;
        ArrayList<Map.Entry<Equipa, Integer>> classf = new ArrayList();
        ArrayList<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for (Map.Entry<Equipa, Integer> entry : this.classificacao.entrySet()) {
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());

        for (Map.Entry<Equipa, Integer> entry : classord) {
            entry.setValue(i);
            classf.add(entry);
            i++;
        }
        return classf;
    }
    public boolean simulaCorrida(){
        
        if(this.ncorrida<=this.circuitos.size()){
            Circuito circ= this.circuitos.get(this.ncorrida);
            int[] pos = {1,1,1,1};
            int i=1;
            double tempo;
            for(Map.Entry<String, Equipa> entry : this.equipas.entrySet()){
                tempo=circ.tempoCorrida(entry.getValue());
                circ.addClassificacao(tempo, entry.getValue());
            }
            Collections.sort(circ.classificacao, new CompareCres());
            Collections.sort(circ.desistencias, new CompareDecr());
            for (Map.Entry<Equipa, Double> entry : circ.classificacao){
                switch (entry.getKey().getCar().getClass().getSimpleName()) {
                    case "PC1": case "PC1_Hibrido":
                        this.addPontos(entry.getKey(), pos[0]);
                        pos[0]++;
                        break;
                    case "PC2": case "PC2_Hibrido":
                        this.addPontos(entry.getKey(), pos[1]);
                        pos[1]++;
                        break;
                    case "GT": case "GT_Hibrido":
                        this.addPontos(entry.getKey(), pos[2]);
                        pos[2]++;
                        break;
                    default:
                        this.addPontos(entry.getKey(), pos[3]);
                        pos[3]++;
                        break;
                }
                i++;
            }
            for(Map.Entry<Equipa, Double> entry: circ.desistencias){
                switch (entry.getKey().getCar().getClass().getSimpleName()) {
                    case "PC1":
                        this.addPontos(entry.getKey(), pos[0]);
                        pos[0]++;
                        break;
                    case "PC2":
                        this.addPontos(entry.getKey(), pos[1]);
                        pos[1]++;
                        break;
                    case "GT":
                        this.addPontos(entry.getKey(), pos[2]);
                        pos[2]++;
                        break;
                    default:
                        this.addPontos(entry.getKey(), pos[3]);
                        pos[3]++;
                        break;
                }
                i++;
            }
            circ.printClassificacao();
            setNcorrida(getNcorrida()+1);
            return true;
        }
        else{
            return false;
        }
    }
    public void printClassCampPC1(){
        int i=1;
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for(Map.Entry<Equipa, Integer> entry: classord){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC1") ||
               entry.getKey().getCar().getClass().getSimpleName().equals("PC1_Hibrido")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+entry.getValue());
                i++;
            }
        }
    }
    public void printClassCampPC2(){
        int i=1;
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for(Map.Entry<Equipa, Integer> entry: classord){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC2") ||
               entry.getKey().getCar().getClass().getSimpleName().equals("PC2_Hibrido")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+entry.getValue());
                i++;
            }
        }
    }
    public void printClassCampGT(){
        int i=1;
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for(Map.Entry<Equipa, Integer> entry: classord){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("GT") ||
               entry.getKey().getCar().getClass().getSimpleName().equals("GT_Hibrido")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+entry.getValue());
                i++;
            }
        }
    }
    public void printClassCampSC(){
        int i=1;
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for(Map.Entry<Equipa, Integer> entry: classord){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("SC")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+entry.getValue());
                i++;
            }
        }
    }
    public void printClassCampHib(){
        int i=1;
        List<Map.Entry<Equipa, Integer>> classord = new ArrayList();
        for(Map.Entry<Equipa, Integer> entry: this.classificacao.entrySet()){
            classord.add(entry);
        }
        Collections.sort(classord, new CompareDecrCamp());
        for(Map.Entry<Equipa, Integer> entry: classord){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC1_Hibrido") ||
               entry.getKey().getCar().getClass().getSimpleName().equals("PC2_Hibrido") ||
               entry.getKey().getCar().getClass().getSimpleName().equals("GT_Hibrido")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+entry.getValue());
                i++;
            }
        }
    }
   
    public void guardaCampeonato(String fich) throws IOException {
        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fich))) {
            oout.writeObject(this);
            oout.flush();
            oout.close();
        }
    }

    public Campeonato carregaCampeonato(String fich) throws IOException, ClassNotFoundException {
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fich))) {
            return (Campeonato) oin.readObject();
        }
    }
}
    