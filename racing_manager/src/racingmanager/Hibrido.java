package racingmanager;


public interface Hibrido
{
    public abstract double tempoProximaVolta(Piloto p, Circuito c, int voltas);
    public abstract int getPotenciaMotorElectrico();
    public abstract void setPotenciaMotorElectrico(int a);
    
}
