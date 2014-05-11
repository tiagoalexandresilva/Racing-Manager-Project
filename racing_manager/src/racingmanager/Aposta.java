package racingmanager;          

import java.util.Objects;

public class Aposta
{
    int circuito;
    String nomeequipa;
    double valor;
    double odd;
    int resultado;
    int classificacao;
    
    public Aposta()
    {
        this.circuito = 0;
        this.nomeequipa = new String();
        this.valor = 0.0;
        this.odd = 0.0;
        this.resultado = 0;
        this.classificacao = 0;
    }
    
    public Aposta(int circuito, String nomeequipa, double valor, double odd, int resultado,int classificacao)
    {
        this.circuito = circuito;
        this.nomeequipa = nomeequipa;
        this.valor = valor;
        this.odd = odd;
        this.resultado = resultado;
        this.classificacao = classificacao;
    }
    
    public Aposta(Aposta a)
    {
        this.circuito = a.getNrCircuito();
        this.nomeequipa = a.getNomeEquipa();
        this.valor = a.getValor();
        this.odd = a.getOdd();
        this.resultado = a.getResultado();
        this.classificacao = a.getClassificacao();
    }
    
    public int getNrCircuito()
    {
        return this.circuito;
    }
    
    public String getNomeEquipa()
    {
        return this.nomeequipa;
    }
    
    public double getValor()
    {
        return this.valor;
    }
    
    public double getOdd()
    {
        return this.odd;
    }
    
    public int getClassificacao()
    {
        return this.classificacao;
    }
    
    public int getResultado()
    {
        return this.resultado;
    }
    
    public void setResultado(int resultado)
    {
        this.resultado = resultado;
    }
    
    @Override
    public Aposta clone()
    {
        return new Aposta(this);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        
        Aposta ap = (Aposta) obj;
        return this.circuito == ap.getNrCircuito() && this.nomeequipa.equals(ap.getNomeEquipa()) 
                && this.valor == ap.getValor() && this.odd == ap.getOdd() && this.resultado == ap.getResultado() && this.classificacao == ap.getClassificacao();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.circuito;
        hash = 79 * hash + Objects.hashCode(this.nomeequipa);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.odd) ^ (Double.doubleToLongBits(this.odd) >>> 32));
        hash = 79 * hash + this.resultado;
        hash = 79 * hash + this.classificacao;
        return hash;
    }



    @Override
    public String toString(){
        String res = new String();
        if(this.resultado == 1)
            res = "Ganhou a aposta";
        if(this.resultado == -1)
            res = "Perdeu a aposta";
        if(this.resultado == 0)
            res = "Aposta em vigor";
        
        return "Circuito: "+this.circuito+"\nNome da Equipa: "+this.nomeequipa+"\nValor da aposta: "+this.valor+"\nValor da odd: "+this.odd+"\nResultado: "+res+"\nClassificação: "+this.classificacao+"º lugar";
    }
}
