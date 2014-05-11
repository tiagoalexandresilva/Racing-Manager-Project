package racingmanager;  

public class NaoExisteFileException extends Exception
{
    public NaoExisteFileException(){super();}
    public NaoExisteFileException(String palavra){super(palavra);}
}
