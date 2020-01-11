import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByFreqs {
    public class Fruit{
        String fruitname;
        int count;

        public Fruit(String name){
            fruitname = name;
        }
        public String getname(){
            return fruitname;
        }
        public int getCount(){
            return count;
        }
        public void setCount(int newCount){
            count = newCount;
        }
    }
    public String[] sort(String[] data) {
        Map<String, Integer> fruitMap = new HashMap<>();
        for(String instance : data){
            if(!fruitMap.containsKey(instance)){
                fruitMap.put(instance, 0);
            }
            fruitMap.put(instance, fruitMap.get(instance) + 1);
        }
        Fruit[] fruity = new Fruit[fruitMap.keySet().size()];
        int k = 0;
        for(String curr : fruitMap.keySet()){
            Fruit a = new Fruit(curr);
            a.setCount(fruitMap.get(curr));
            fruity[k] = a;
            k++;
        }
        Arrays.sort(fruity, Comparator.comparing(Fruit::getCount)
                .reversed()
                .thenComparing(Fruit::getname));
        String[] ret = new String[fruity.length];
        for(int j = 0; j < fruity.length; j++){
            ret[j] = fruity[j].getname();
        }
        return ret;
    }
}