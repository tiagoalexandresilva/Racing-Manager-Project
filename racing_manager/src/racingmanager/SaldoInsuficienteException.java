package racingmanager;   

public class SaldoInsuficienteException extends Exception
{
    public SaldoInsuficienteException(){super();}
    public SaldoInsuficienteException(String palavra){super(palavra);}
}
