package racingmanager; 

public class NaoExisteNomeEquipaException extends Exception
{
    public NaoExisteNomeEquipaException(){super();}
    public NaoExisteNomeEquipaException(String palavra){super(palavra);}
}
