package racingmanager; 

public class NaoExistePassException extends Exception
{
    public NaoExistePassException(){super();}
    public NaoExistePassException(String palavra){super(palavra);}
}
