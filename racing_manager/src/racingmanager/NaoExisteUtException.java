package racingmanager; 

//Excepcao a ser usada quando se procurar um utilizador e ele não existir.

public class NaoExisteUtException extends Exception
{
    public NaoExisteUtException(){super();}
    public NaoExisteUtException(String palavra){super(palavra);}
}
