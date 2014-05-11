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
public class CompareDecrCamp implements Comparator<Map.Entry<Equipa, Integer>>, Serializable{
    @Override
    public int compare(Entry<Equipa, Integer> e1, Entry<Equipa, Integer> e2) {
        if(e1.getValue()>e2.getValue()){
            return -1;
        }
        else if(e1.getValue()==e2.getValue()){
            int qual1, qual2;
            qual1=e1.getKey().getPiloto1().getQualidade()+e1.getKey().getPiloto2().getQualidade();
            qual2=e2.getKey().getPiloto1().getQualidade()+e2.getKey().getPiloto2().getQualidade();
            if(qual1<qual2){
                return -1;
            }
            else if(qual1>qual2){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return 1;
        }
    }
    
}
