package racingmanager;

import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;

public class Utilizador
{
    private String nick;
    private String nome;
    private String morada;
    private String pass;
    double saldo;
    double lucro;
    private LinkedList<Double> conta;
    private LinkedList<Aposta> historico;
    private HashSet<Aposta> lapostas;
    
    public Utilizador()
    {
        this.nick = new String();
        this.nome = new String();
        this.morada = new String();
        this.pass = new String();
        this.saldo = 0.0;
        this.lucro = 0.0;
        this.conta = new LinkedList<Double>();
        this.historico = new LinkedList<Aposta>();
        this.lapostas = new HashSet<Aposta>();
    }
    
    public Utilizador(String nick, String nome, String morada, String pass)
    {
        this.nick = nick;
        this.nome = nome;
        this.morada = morada;
        this.pass = pass;
        this.saldo = 0.0;
        this.lucro = 0.0;
        this.conta = new LinkedList<Double>();
        this.historico = new LinkedList<Aposta>();
        this.lapostas = new HashSet<Aposta>();
    }
    
    public Utilizador(Utilizador u)
    {
        this.nick = u.getNick();
        this.nome = u.getNome();
        this.morada = u.getMorada();
        this.pass = u.getPass();
        this.saldo = u.getSaldo();
        this.lucro = u.getLucro();
        this.conta = u.getConta();
        this.historico = u.getHistorico();
        this.lapostas = u.getLapostas();
    }
    
    public String getNick()
    {
        return this.nick;
    }
    
    public void setNick(String nick)
    {
        this.nick = nick;
    }
    
    public double getLucro()
    {
        return this.lucro;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public String getMorada()
    {
        return this.morada;
    }
    
    public void setMorada(String morada)
    {
        this.morada = morada;
    }
    
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    
    public double getSaldo()
    {
        return this.saldo;
    }
    
    public String getPass()
    {
        return this.pass;
    }
    
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    
    public void setConta(LinkedList<Double> conta)
    {
        for(Double d : conta)
        {
            this.conta.add(d);
        }
    }
    
    public LinkedList<Double> getConta()
    {
        LinkedList<Double> c = new LinkedList<Double>();
        c.addAll(this.conta);
        return c;
    }
    
    public LinkedList<Aposta> getHistorico()
    {
        LinkedList<Aposta> ap1 = new LinkedList<Aposta>();
        for(Aposta ap2 : this.historico)
        {
            ap1.add(ap2.clone());
        }
        return ap1;
    }
    
    public void setHistorico(LinkedList<Aposta> historico)
    {
        for(Aposta ap : historico)
        {
            this.historico.add(ap.clone());
        }
    }
    
    public HashSet<Aposta> getLapostas()
    {
        HashSet<Aposta> ap1 = new HashSet<Aposta>();
        for(Aposta ap2 : this.lapostas)
        {
            ap1.add(ap2.clone());
        }
        return ap1;
    }
    
    public void setLapostas(HashSet<Aposta> lapostas)
    {
        for(Aposta ap : lapostas)
        {
            this.lapostas.add(ap.clone());
        }
    }
    
    
    public void listaContaCorrente()
    {
        System.out.println("\n:::Movimentos da conta corrente:::");
        System.out.println("**********************************");
        System.out.println("Utilizador: "+this.getNick());
        System.out.println("----------------------------------");
        for(Double d : this.conta)
        {
            if(d >= 0)
                System.out.println("+"+d);
            else
                System.out.println(d);
        }
    }
    
    public void actualizaApostasVigor(Circuito c)
    { 
        System.out.println("cir que entrou: "+c.getNrCorrida());
        for (Iterator<Aposta> i = this.lapostas.iterator(); i.hasNext();)
        {
            Aposta aposta = i.next();
            if(aposta.getNrCircuito() == c.getNrCorrida())
            {
                if(aposta.getClassificacao() == procuraPosicao(c.getClassificacaoCircuito(),aposta.getNomeEquipa()))
                {   
                    aposta = aposta.clone();
                    aposta.setResultado(1); //ganhou a aposta
                    actualizaHistorico(aposta);
                    i.remove();
                    
                }
                else
                {
                    aposta = aposta.clone();
                    aposta.setResultado(-1); //perdeu a aposta
                    actualizaHistorico(aposta);
                    i.remove();
                }       
            }
        }
    }
    
    private void actualizaHistorico(Aposta ap)
    {
        double odd = ap.getOdd();
        double valor = ap.getValor();
        this.historico.add(ap);
        if(ap.getResultado() == 1)
        {
            this.saldo = this.saldo+(valor*odd);
            this.lucro = this.lucro+(valor*odd);
            this.conta.add(valor*odd);
        }
    }
    
    //metodo que procura o nome de uma equipa na classificacao do circuito e dá a posicao na tabela classificativa
    private int procuraPosicao(ArrayList<Map.Entry<Equipa, Double>> tabela, String nome)
    {   
        int i = 1;
        for(Map.Entry<Equipa, Double> entry : tabela)
        {
            if(entry.getKey().getNome().equals(nome)){return i;}
            i++;
        }
        return -1;
    }
    
    public void fazAposta(Aposta ap, Campeonato camp) throws SaldoInsuficienteException, ClassificacaoErradaException, NaoExisteNomeEquipaException, NaoExisteCircuitoException, CorridaJaExecutadaException
    {
        double valor = ap.getValor();
        int classificacao = ap.getClassificacao();
        if(camp.getNcorrida() <= ap.getNrCircuito())
        {
            if(camp.getCircuitos().containsKey(ap.getNrCircuito()))
            {
                if(camp.getEquipas().containsKey(ap.getNomeEquipa()))
                {
                    if(classificacao == 1 || classificacao == 2 || classificacao == 3)
                    {
                        if( this.saldo-valor >= 0 )
                        {
                            this.saldo = this.saldo-valor;
                            this.lucro = this.lucro-valor;
                            this.conta.add(-valor);
                            this.lapostas.add(ap);
                        }
                        else
                        {
                            throw new SaldoInsuficienteException("Erro! Saldo insuficiente para a aposta em questão.\n"); 
                        }
                    }
                    else
                    {
                        throw new ClassificacaoErradaException("Erro! Aposte apenas para as três primeiras classificações.\n");
                    }
                }
                else
                {
                    throw new NaoExisteNomeEquipaException("Erro! Não existe a Equipa em que deseja apostar.\n");
                }
            }
            else
            {
                throw new NaoExisteCircuitoException("Erro! Não existe o Circuito em que deseja apostar.\n");  
            }
        }
        else
        {
            throw new CorridaJaExecutadaException("Erro! O Circuito para o qual deseja apostar já foi concluído.\n");
        }
    }
    
    /**
    * Método que retorna a informação do seu histórico de apostas
    * 
    */
    public void infoHistorico()
    {
        System.out.println("--------------------------");
        for(Aposta a : this.historico)
        {
            System.out.println(a.toString());
            System.out.println("--------------------------");
        }
    }
    
    /**
    * Método que retorna a informação das apostas ainda em vigor
    * 
    */
    public void infoApostasVigor()
    {
        System.out.println("--------------------------");
        for(Aposta a : this.lapostas)
        {   
            System.out.println(a.toString());
            System.out.println("--------------------------");
        }
    }
    
    public void transferirSaldo(double saldo) throws LimiteTransferenciaException
    {
        if(saldo > 300)
            throw new LimiteTransferenciaException("Erro! Transferência máxima de saldo excedida (300 €).");
        else
            setSaldo(getSaldo()+saldo);
    }
    
    private boolean equalsConta(LinkedList<Double> lista1, LinkedList<Double> lista2)
    {
        Iterator<Double> it1 = lista1.iterator();
        Iterator<Double> it2 = lista2.iterator();
        if(lista1.size() != lista2.size())
            return false;
        else
        {
            while(it1.hasNext() && it2.hasNext())
            {
                if(it1.next() != it2.next())
                    return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        
        Utilizador ut = (Utilizador) obj;
        return this.nick.equals(ut.getNick()) && this.nome.equals(ut.getNome()) && this.pass.equals(ut.getPass()) && equalsConta(this.conta, ut.getConta())
                && this.morada.equals(ut.getMorada()) && this.historico.equals(ut.getHistorico()) && this.lapostas.equals(ut.getLapostas());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nick);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.morada);
        hash = 47 * hash + Objects.hashCode(this.pass);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.saldo) ^ (Double.doubleToLongBits(this.saldo) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.lucro) ^ (Double.doubleToLongBits(this.lucro) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.conta);
        hash = 47 * hash + Objects.hashCode(this.historico);
        hash = 47 * hash + Objects.hashCode(this.lapostas);
        return hash;
    }

 

    @Override
    public String toString(){
        return "Nickname: "+this.nick+"\nNome de Utilizador: "+this.nome+"\nMorada: "+this.morada+"\nPassword: "+this.pass+"\nSaldo: "+this.saldo+"\nLucro: "+this.lucro;
    }
    
    @Override
    public Utilizador clone()
    {
        return new Utilizador(this);
    }
}
