/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package racingmanager;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Samuel
 */
public class CompareDecr implements Comparator<Map.Entry<Equipa, Double>>, Serializable{

    @Override
    public int compare(Entry<Equipa, Double> e1, Entry<Equipa, Double> e2) {
        if(e1.getValue()>e2.getValue()){
            return -1;
        }
        else if(e1.getValue()<e2.getValue()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
}
