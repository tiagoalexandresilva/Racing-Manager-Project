package racingmanager;   

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;



public class Piloto implements Serializable{
    private String nome;
    private String nacionalidade;
    private int palmares; 
    private int qualidade;
    private double fac_chuva;
    
    public Piloto(){
        this.nome=new String();
        this.nacionalidade= new String();
        this.palmares=0;
        this.qualidade=0;
        this.fac_chuva=0;
    }
    
    public Piloto(String nome, String nacionalidade, int palmares, int qualidade, double fac_chuva){
        this.nome=nome;
        this.nacionalidade=nacionalidade;
        this.palmares=palmares;
        this.qualidade=qualidade;
        this.fac_chuva=fac_chuva;
    }
    
    public Piloto(Piloto p){
        this.nome=p.getNome();
        this.nacionalidade=p.getNacionalidade();
        this.palmares=p.getPalmares();
        this.qualidade=p.getQualidade();
        this.fac_chuva=p.getFac_chuva();
    }
    
    // lucas,portugues,5,7,0.1
    public Piloto(String s){
        String[] res= s.split(",");
        int i=0;
        while(i<res.length){
            if(i==0)
                this.nome=res[0];
            if(i==1)
                this.nacionalidade=res[1];
            if(i==2)
                this.palmares=Integer.parseInt(res[2]);
            if(i==3)
                this.qualidade=Integer.parseInt(res[3]);
            if(i==4)
                this.fac_chuva=Double.parseDouble(res[4]);
            i++;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getPalmares() {
        return palmares;
    }

    public void setPalmares(int palmares) {
        this.palmares = palmares;
    }

    public int getQualidade() {
        return qualidade;
    }

    public void setQualidade(int qualidade) {
        this.qualidade = qualidade;
    }

    public double getFac_chuva() {
        return fac_chuva;
    }

    public void setFac_chuva(double fac_chuva) {
        this.fac_chuva = fac_chuva;
    }
    
    public ArrayList<Piloto> leFicPilotos(){
        ArrayList<Piloto> lista = new ArrayList<Piloto>();
        Piloto pil;
        String linha;
        FileInputStream fstream = null;
        try
        {
            fstream = new FileInputStream("pilotos.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
                   
            //Read File Line By Line
            while ((linha = br.readLine()) != null)   {
              pil= new Piloto(linha);
              System.out.println(pil.toString());
              lista.add(pil);
            }
            //Close the input stream
            in.close();
        }
        catch(IOException e)
        {
            
        }
        System.out.println(lista.toString());
        return lista;
    };
    
    @Override
    public Piloto clone(){
        return new Piloto(this);
    }
    
    @Override
    public String toString(){
        return "Nome: "+this.nome+"\nNacionalidade: "+this.nacionalidade+"\nPalmarés: "+this.palmares+"\nQualidade: "+this.qualidade+"\nFactor chuva: "+this.fac_chuva;
    }
    
    @Override
    public boolean equals(Object p){
        if(this==p){
            return true;
        }
        
        if((p==null) || (p.getClass()!=this.getClass())){
            return false;
        }
        else{
            Piloto pil= (Piloto)p;
            return this.nome.equals(pil.getNome()) && this.nacionalidade.equals(pil.getNacionalidade()) 
                    && this.qualidade==pil.getQualidade() && this.palmares==pil.getPalmares() && this.fac_chuva==pil.getFac_chuva();
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.nacionalidade);
        hash = 89 * hash + this.palmares;
        hash = 89 * hash + this.qualidade;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.fac_chuva) ^ (Double.doubleToLongBits(this.fac_chuva) >>> 32));
        return hash;
    }

    private void printf(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public void incPalmares(){
       this.palmares++;
   }
}