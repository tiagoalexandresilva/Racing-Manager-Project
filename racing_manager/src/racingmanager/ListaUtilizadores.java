package racingmanager; 

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class ListaUtilizadores
{
    private HashMap<String,Utilizador> lut; //lista de utilizadores registados no sistema
    private TreeMap<String,Double> lodds;
    private TreeMap<String,Double> scoreboard;
        
    public ListaUtilizadores()
    {
        this.lut = new HashMap<String,Utilizador>();
        this.lodds = new TreeMap<String,Double>();
        this.scoreboard = new TreeMap<String,Double>();
    }
    
    public ListaUtilizadores(ListaUtilizadores lutilizadores)
    {
        this.lut = new HashMap<String,Utilizador>();
        this.lodds = new TreeMap<String,Double>();
        this.scoreboard = new TreeMap<String,Double>();

        for(Utilizador u : lutilizadores.lut.values())
        {
            this.lut.put(u.getNick(),u.clone());
        }

        for(String nome : lutilizadores.lodds.keySet())
        {
            Double odd = lutilizadores.lodds.get(nome);
            this.lodds.put(nome, odd);
        }

        for(String entry : lutilizadores.scoreboard.keySet())
        {
            Double valor = lutilizadores.scoreboard.get(entry);
            this.scoreboard.put(entry,valor);
        }
    }
    
    public ListaUtilizadores(HashMap<String,Utilizador> lutilizadores, TreeMap<String,Double> odds, TreeMap<String,Double> scoreboard)
    {
        this.lut = new HashMap<String,Utilizador>();
        this.lodds = new TreeMap<String,Double>();
        this.scoreboard = new TreeMap<String,Double>();

        for(Utilizador u : lutilizadores.values())
        {
            this.lut.put(u.getNick(),u.clone());
        }

        for(String nome : odds.keySet())
        {
            Double odd = odds.get(nome);
            this.lodds.put(nome, odd);
        }

        for(String entry : scoreboard.keySet())
        {
            Double valor = scoreboard.get(entry);
            this.scoreboard.put(entry,valor);
        }
    }
    
    public void inicializaListaOdds(TreeMap<String,Equipa> equipas)
    {
        for(Equipa e : equipas.values())
        {
             this.lodds.put(e.getNome(), geraOddEquipa(e,false));
        }
    }
    
   
    
    public void novoRegisto(Utilizador ut) throws NickJaExisteException
    {
        
        if(this.lut.containsKey(ut.getNick()))
            throw new NickJaExisteException("Erro de registo! Nickname já existe.");
        else
        {
            this.lut.put(ut.getNick(),ut);
        }
    }
    
    public Utilizador login(String nick, String pass) throws NaoExisteUtException, PassErradaException
    {
        Utilizador ut;
        if(this.lut.containsKey(nick))
        {
            ut = this.lut.get(nick);
            if(pass.equals(ut.getPass()))
            {
                return ut;
            }
            else
            {
                throw new PassErradaException("Login errado! Password errada.");
            }
        }
        else
        {
            throw new NaoExisteUtException("Login errado! Utilizador não existe.");
        }
    }
    
    public TreeMap<String,Double> getScoreboard(){
        TreeMap<String,Double> t = new TreeMap<String,Double>();
        for(String key : scoreboard.keySet()){
            double value = scoreboard.get(key);
            t.put(key,value);
        }
        return t;
    }
    
     public void printScoreboard(TreeMap<String,Double> sb)
    {   System.out.println("\n:::Scoreboard:::");
        System.out.println("*******************");
		int i=0;
		Set<Map.Entry<String,Double>> lista1 = sb.entrySet();
		List<Map.Entry<String,Double>> lista = new ArrayList<Map.Entry<String,Double>>();
		lista.addAll(lista1);
		Collections.sort(lista,new CompareDecr2());
        for(Map.Entry<String,Double> entry  : lista)
        {
            if(i<3){
			System.out.println("Utilizador: "+entry.getKey()+" | Lucro: "+entry.getValue());
			i++;
			}
        }
    }   
    
    public void inicializaScoreboard()
    {
        for(Utilizador ut : this.lut.values())
        {
            this.scoreboard.put(ut.getNome(), 0.0);
        }
    }
    
    public void actualizaScoreboard()
    {
        for(Utilizador ut : this.lut.values()){ 
            if(scoreboard.get(ut.getNome())!=ut.getLucro()){
                scoreboard.put(ut.getNome(),ut.getLucro());
            }
        }
    }
    
    public void infoHistoricoTotal()
    {
        System.out.println("\n:::Histórico de apostas Total:::");
        for(Utilizador u : this.lut.values())
        {
            System.out.println("**************************");
            System.out.println("Utilizador: "+u.getNick());
            u.infoHistorico();
        }
    }
    
    public void infoApostasVigorTotal()
    {
       System.out.println("\n:::::Apostas em vigor Total::::");
       for(Utilizador u : this.lut.values())
       {
            System.out.println("**************************");
            System.out.println("Utilizador: "+u.getNick());
            u.infoApostasVigor();
       } 
    }
    
    public void removeRegisto(String nick)
    {
        this.lut.remove(nick);
    }
    
    public void printListaOdds()
    {
        System.out.println("\n::::Odd's das Equipas::::");
        System.out.println("**************************");
        for (String nome : this.lodds.keySet()){
            Double odd = this.lodds.get(nome);
            System.out.println("Equipa: "+nome+" | Odd: "+odd);
        }
    }
    
    public Double getOddEquipa(String nomeequipa)
    {
        double odd;
        if(this.lodds.containsKey(nomeequipa))
            odd = this.lodds.get(nomeequipa);
        else
            odd = 0.0;
        
        return odd;
    }
    
    public void actualizaOddsFinais(ArrayList<Map.Entry<Equipa, Integer>> classgeral, boolean chuva, int nrcorrida)
    {   
        if(nrcorrida != 1)
        {
            for(Map.Entry<Equipa,Integer> entry : classgeral)
            {
                if(!this.lodds.containsKey(entry.getKey().getNome()))
                    this.lodds.put( (entry.getKey().getNome()), ( (entry.getValue()*0.5) + (geraOddEquipa(entry.getKey(),chuva)) ));
                else{
                    this.lodds.remove(entry.getKey().getNome());
                    this.lodds.put( (entry.getKey().getNome()), ( (entry.getValue()*0.5) + (geraOddEquipa(entry.getKey(),chuva)) ));
                }
            }
        }
        else
        {
            for(Map.Entry<Equipa,Integer> entry : classgeral)
            {
                if(!this.lodds.containsKey(entry.getKey().getNome()))
                    this.lodds.put( (entry.getKey().getNome()), ( (geraOddEquipa(entry.getKey(),chuva)) ));
                else{
                    this.lodds.remove(entry.getKey().getNome());
                    this.lodds.put( (entry.getKey().getNome()), ( (geraOddEquipa(entry.getKey(),chuva)) ));
                }
            }
        }
    }
    
    private double geraOddEquipa(Equipa eq, boolean chuva){
        double oddp1;
        double oddp2;
      
        Piloto p1 = eq.getPiloto1();
        Piloto p2 = eq.getPiloto2();
        
        if(!chuva){
            oddp1 = (Math.pow(0.005,(1.0/p1.getQualidade()))) + (10.0 - p1.getQualidade());
            oddp2 = (Math.pow(0.005,(1.0/p2.getQualidade()))) + (10.0 - p2.getQualidade());
        }
        else{
            oddp1 = (Math.pow(0.005,(1.0/p1.getQualidade()))) + (10.0 - p1.getQualidade())+(10.0*(0.2/p1.getFac_chuva()));
            oddp2 = (Math.pow(0.005,(1.0/p2.getQualidade()))) + (10.0 - p2.getQualidade())+(10.0*(0.2/p2.getFac_chuva()));
        }
        return (0.5*oddp1) + (0.5*oddp2);
    }
    
    //Para um dado circuito que ja tenha terminado a corrida, vamos actualizar as apostas em vigor de cada utilizador (e passa-las para o historico) que haviam para esse circuito.
    public void actualizaApostasVigorTotais(Circuito c)
    {
        for (Iterator<Utilizador> it = this.lut.values().iterator(); it.hasNext();) {
            Utilizador ut = it.next();
            ut.actualizaApostasVigor(c);
        }
    }
    
    @Override
    public ListaUtilizadores clone()
    {
        return new ListaUtilizadores(this);
    }
}
