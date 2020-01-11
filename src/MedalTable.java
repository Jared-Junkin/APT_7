import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MedalTable {
    public class MedalCountry{
        int gold;
        int silver;
        int bronze;
        String name;
        public MedalCountry(String country){
            name = country;
        }
        public int getGold(){
            return gold;
        }
        public int getBronze(){
            return bronze;
        }
        public int getSilver(){
            return silver;
        }
        public String getCountry(){
            return name;
        }
        public void putGold(){
            gold ++;
        }
        public void putSilver(){
            silver ++;
        }
        public void putBronze(){
            bronze ++;
        }
        @Override
        public String toString() {
            return "" + getCountry() + " " + getGold() + " " + getSilver() + " " + getBronze();
        }
    }
    public String[] generate(String[] results) {
        Map<String, MedalCountry> map = new HashMap<>();
        for(String s : results){
            String[] data = s.split(" ");
            for(int k = 0; k < 3; k++){
                map.putIfAbsent(data[k], new MedalCountry(data[k]));
            }
            MedalCountry goldCountry = map.get(data[0]);
            goldCountry.putGold();
            map.put(data[0], goldCountry);

            MedalCountry silverCountry = map.get(data[1]);
            silverCountry.putSilver();
            map.put(data[1], silverCountry);

            MedalCountry bronzeCountry = map.get(data[2]);
            bronzeCountry.putBronze();
            map.put(data[2], bronzeCountry);
        }

        MedalCountry[] meds = map.values().toArray(new MedalCountry[0]);
        Arrays.sort(meds, Comparator.comparing(MedalCountry::getGold)
                .thenComparing(MedalCountry::getSilver) // can you explain why, without the reverse, it returns #'s from lowest to highest?
                .thenComparing(MedalCountry::getBronze)
                .reversed()
                .thenComparing(MedalCountry::getCountry));
        String[] ret = new String[meds.length];
        for(int j = 0; j < meds.length; j++){
            ret[j] = meds[j].toString();
        }
        return ret;
    }
}