package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int i;
        Map<Integer,Integer> map = new HashMap<>();
        for(i=0;i<oomages.size();i++){
            int hashcode =  (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            Integer count = map.get(hashcode);
            if(count==null){
                map.put(hashcode,1);
            }
            else{
                map.put(hashcode,count+1);
            }
        }
        for(i=0;i<M;i++){
            int count = map.get(i);
            if(count<oomages.size()/50){
                return false;
            }
            else if(count>oomages.size()/2.5){
                return false;
            }
        }
        return true;
    }
}
