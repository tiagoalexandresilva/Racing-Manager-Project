package racingmanager;
 

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public class ComparePar implements Comparator<Map.Entry<Equipa, Double>>, Serializable{
    @Override
    public int compare(Map.Entry<Equipa, Double> e1, Map.Entry<Equipa, Double> e2) {
            return e1.getValue().compareTo(e2.getValue());
    }
}
