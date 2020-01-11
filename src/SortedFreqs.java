import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortedFreqs {
    public int[] freqs(String[] data) {
        Map<String, Integer> dict = new HashMap<>();
        for(String thing : data){
            if(!dict.containsKey(thing)){
                dict.put(thing, 0);
            }
            dict.put(thing, dict.get(thing) + 1);
        }
        String[] lexikized = dict.keySet().toArray(new String[0]);
        Arrays.sort(lexikized);
        int[] ret = new int[lexikized.length];
        for(int k = 0; k < ret.length; k++){
            ret[k] = dict.get(lexikized[k]);
        }
        return ret;
    }
}