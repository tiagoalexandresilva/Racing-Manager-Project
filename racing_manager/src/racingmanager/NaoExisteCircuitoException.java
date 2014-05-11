package racingmanager; 

public class NaoExisteCircuitoException extends Exception
{
    public NaoExisteCircuitoException(){super();}
    public NaoExisteCircuitoException(String palavra){super(palavra);}
}
