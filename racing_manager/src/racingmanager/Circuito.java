package racingmanager;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Circuito implements Serializable{
    private String nome;
    private int nrcorrida; //Ordem no campeonato
    private int numvoltas;
    private int dist;
    private double[] tempomed; //tempo medio por volta, por categoria do carro
    private double[] tempdesvio; //tempo de desvio por carro, com o tempo molhado
    private double temprec; 
    private String pilrec;
    private double tempbox;
    private boolean chuva;
    public List<Map.Entry<Equipa, Double>> classificacao; //classificao  por circuito  
    public List<Map.Entry<Equipa, Double>> desistencias; //lista de desistencias
    
    public Circuito(){
        this.dist=0;
        this.numvoltas=0;
        this.pilrec=new String();
        this.temprec=0;
        this.tempbox=0;
        this.tempomed=new double[] {0,0,0,0};
        this.tempdesvio= new double[] {0,0,0,0};
        this.nome=new String();
        this.nrcorrida=0;
        this.chuva=false;
        this.classificacao=new ArrayList<>();
        this.desistencias=new ArrayList<>();
    }
    public Circuito(String s, int ordem) {
        this.tempomed = new double[]{0, 0, 0, 0};
        this.tempdesvio = new double[]{0, 0, 0, 0};
        String[] res = s.split(",");
        this.nome = res[0];
        this.numvoltas = Integer.parseInt(res[1]);
        this.dist = Integer.parseInt(res[2]);
        this.temprec = Double.parseDouble(res[3]);
        this.pilrec = res[4];
        this.nrcorrida = ordem; //Ordem no campeonato
        this.tempomed[0] = Double.parseDouble(res[5]); //PC1
        this.tempomed[1] = Double.parseDouble(res[6]); //PC2
        this.tempomed[2] = Double.parseDouble(res[7]); //GT
        this.tempomed[3] = Double.parseDouble(res[8]); //SC
        this.tempdesvio[0] = Double.parseDouble(res[9]);
        this.tempdesvio[1] = Double.parseDouble(res[10]);
        this.tempdesvio[2] = Double.parseDouble(res[11]);
        this.tempdesvio[3] = Double.parseDouble(res[12]);
        this.tempbox = Double.parseDouble(res[13]);
        this.chuva = false;
        this.classificacao = new ArrayList<>();
        this.desistencias = new ArrayList<>();
    }
    public Circuito(int dist, double[] tempomed, double[] tempdesvio, int numvoltas, double temprec, double tempbox, String pilrec, String nome, boolean chuva, int nrcorrida) {
        this.dist = dist;
        this.setTempoMed(tempomed);
        this.setTempDesvio(tempdesvio);
        this.numvoltas = numvoltas;
        this.temprec = temprec;
        this.tempbox = tempbox;
        this.pilrec = pilrec;
        this.nome=nome;
        this.chuva=chuva;
        this.nrcorrida=nrcorrida;
        this.classificacao=new ArrayList<>();
        this.desistencias=new ArrayList<>();
    }   
    public boolean getChuva(){
        return chuva;
    } 
    public void setChuva(boolean chuva){
        this.chuva=chuva;
    }
    public int getNrCorrida(){
        return nrcorrida;
    }
    public void setNrCorrida(int nrcorrida){
        this.nrcorrida=nrcorrida;
    }   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getDist() {
        return dist;
    }
    public void setDist(int dist) {
        this.dist = dist;
    }
    public double[] getTempoMed() {
        return tempomed;
    }
    private void setTempoMed(double[] tempomed) {
        int i=0;
        this.tempomed=new double[4];
        while(i<4){
            this.tempomed[i]= tempomed[i];
            i++;
        }
    }
    public int getNumVoltas() {
        return numvoltas;
    }
    public void setNumVoltas(int numvoltas) {
        this.numvoltas = numvoltas;
    }
    public double[] getTempDesvio() {
        return tempdesvio;
    }
    private void setTempDesvio(double[] tempdesvio) {
        int i=0;
        this.tempdesvio=new double[4];
        while(i<4){
            this.tempdesvio[i]= tempdesvio[i];
            i++;
        }
    }
    public double getTempRec() {
        return temprec;
    }
    public void setTempRec(double temprec) {
        this.temprec = temprec;
    }
    public double getTempBox() {
        return tempbox;
    }
    public void setTempbox(double tempbox) {
        this.tempbox = tempbox;
    }
    public String getPilrec() {
        return pilrec;
    }
    public void setPilrec(String pilrec) {
        this.pilrec = pilrec;
    }
    private boolean equar(double[] ar1, double[] ar2){
        int i=0;
        boolean flag=true;
        if(ar1.length!=ar2.length){
            return false;
        }
        else{
            while(i<ar1.length){
                if(ar1[i]!=ar2[i]){
                    flag=false;
                }
                i++;
            }
            return flag;
        }
    } //verifica se 2 arrays são iguais   
    @Override
    public String toString() {
        return "Circuito: "+nome+"\nDistância: "+dist+"\nNúmero de voltas: "+numvoltas+"\nTempo record: "+defTempo(temprec)+
                "\nPiloto recordista: "+pilrec+"\n";
    }
    @Override
    public boolean equals(Object c){
        if(this==c){
            return true;
        }
        
        if((c==null) || (c.getClass()!=this.getClass())){
            return false;
        }
        else{
            Circuito cir= (Circuito)c;
            return this.nome.equals(cir.getNome()) && this.pilrec.equals(cir.getPilrec()) && this.dist==cir.getDist() 
                    && this.numvoltas==cir.getNumVoltas() && this.temprec==cir.getTempRec() && this.tempbox==cir.getTempBox()
                    && equar(this.tempomed, cir.getTempoMed()) && equar(this.tempdesvio, cir.getTempDesvio()) && this.chuva == cir.getChuva() && this.nrcorrida == cir.getNrCorrida();
        }
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + this.nrcorrida;
        hash = 83 * hash + this.numvoltas;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.dist) ^ (Double.doubleToLongBits(this.dist) >>> 32));
        hash = 83 * hash + Arrays.hashCode(this.tempomed);
        hash = 83 * hash + Arrays.hashCode(this.tempdesvio);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.temprec) ^ (Double.doubleToLongBits(this.temprec) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.pilrec);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.tempbox) ^ (Double.doubleToLongBits(this.tempbox) >>> 32));
        hash = 83 * hash + (this.chuva ? 1 : 0);
        return hash;
    }   
    @Override
    public Circuito clone(){
         return new Circuito(this.getDist(),this.getTempoMed(),this.getTempDesvio(),this.getNumVoltas(),this.getTempRec(),this.getTempBox(),this.getPilrec(),this.getNome(),this.getChuva(),this.getNrCorrida());
    }   
    public double tempoCorrida(Equipa e){
        double tempovol, tempocor=0, aux;
        int npiloto=1, teste1=0, teste2=0;
        switch (e.getCar().getClass().getSimpleName()) {
            case "PC1": case "PC1_Hibrido":
                if(this.chuva==true){
                    tempovol=this.tempomed[0]+this.tempdesvio[0];
                }
                else{
                    tempovol=this.tempomed[0];
                }
                break;
            case "PC2": case "PC2_Hibrido":
                if(this.chuva==true){
                    tempovol=this.tempomed[1]+this.tempdesvio[1];
                }
                else{
                    tempovol=this.tempomed[1];
                }
                break;
            case "GT": case "GT_Hibrido":
                if(this.chuva==true){
                    tempovol=this.tempomed[2]+this.tempdesvio[2];
                }
                else{
                    tempovol=this.tempomed[2];
                }
                break;
            default:
                if(this.chuva==true){
                    tempovol=this.tempomed[3]+this.tempdesvio[3];
                }
                else{
                    tempovol=this.tempomed[3];
                }
                break;
        }
        for(int i=1; i<=this.numvoltas; i++){
            if(npiloto<=this.numvoltas/2){
                aux=tempovol;
                aux+=e.getCar().tempoProximaVolta(e.getPiloto1(), this, i);
                checkTempRec(aux, e.getPiloto1());
                teste1++;
            }
            else{
                aux=tempovol;
                aux+=e.getCar().tempoProximaVolta(e.getPiloto2(), this, i);
                checkTempRec(aux, e.getPiloto2());
                teste2++;
            }
            if(aux>=1000){
                return i;
            }
            else{
                tempocor+=aux;
            }
            npiloto++;
        }
        return tempocor+this.tempbox;
    }
    public void addClassificacao(double tempo, Equipa e){
        Map.Entry<Equipa, Double> par=new AbstractMap.SimpleEntry<>(e,tempo);
        if(tempo<120){
            this.desistencias.add(par);
            
        }
        else{
            this.classificacao.add(par);
        }
    }
    public void printClassificacao(){
        int i=1;
        for (Entry<Equipa, Double> entry : this.classificacao){
            System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
            i++;
        }
        
        for(Map.Entry<Equipa, Double> entry: this.desistencias){
            System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                    +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
            i++;
        }
    }
    public ArrayList<Map.Entry<Equipa, Double>> getClassificacaoCircuito(){
        ArrayList<Map.Entry<Equipa, Double>> classfinal = new ArrayList(this.classificacao);
        int i=1;
        classfinal.addAll(this.desistencias);
        return classfinal;
    }
    public String defTempo(double tempo){
           DecimalFormat formatador = new DecimalFormat();
           formatador.setDecimalSeparatorAlwaysShown(false);
           if(tempo<100){
               return "DNF Volta: "+formatador.format(tempo);
           }
           else{
               tempo=(tempo*1000);
               double horas =(int)((tempo/1000)/60/60);
               tempo= tempo-(horas*1000*60*60);
               double minutos=(int)(tempo/1000)/60;
               tempo=tempo-(minutos*1000*60);
               double segundos=(int)tempo/1000;
               tempo=tempo-(segundos*1000);
               double milisegundos=(int)tempo;
               return formatador.format(horas)+":"+formatador.format(minutos)+":"+formatador.format(segundos)+"."+formatador.format(milisegundos);
           }
        }
    public void checkTempRec(Double tempo, Piloto p){
        if(tempo<this.getTempRec()){
                    this.setTempRec(tempo);
                    this.setPilrec(p.getNome());
        }
    }
    public void getClassCirPC1(){
        int i=1;
        for(Map.Entry<Equipa, Double> entry: this.classificacao){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC1")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
        for(Map.Entry<Equipa, Double> entry: this.desistencias){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC1")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
    }
    public void getClassCirPC2(){
        int i=1;
        for(Map.Entry<Equipa, Double> entry: this.classificacao){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC2")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
        for(Map.Entry<Equipa, Double> entry: this.desistencias){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("PC2")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
    }
    public void getClassCirGT(){
        int i=1;
        for(Map.Entry<Equipa, Double> entry: this.classificacao){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("GT")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
        for(Map.Entry<Equipa, Double> entry: this.desistencias){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("GT")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
    }
    public void getClassCirSC(){
        int i=1;
        for(Map.Entry<Equipa, Double> entry: this.classificacao){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("SC")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
        for(Map.Entry<Equipa, Double> entry: this.desistencias){
            if(entry.getKey().getCar().getClass().getSimpleName().equals("SC")){
                System.out.println(i+"º "+entry.getKey().getCar().getClass().getSimpleName()+" "
                        +entry.getKey().getNome()+" "+defTempo(entry.getValue()));
                i++;
            }
        }
    }
    
}