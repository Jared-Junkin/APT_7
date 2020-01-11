import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TheBestName {
    class letter{
        String myValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String myLet;
        int myWeight;

        public letter(String x){
            myLet = x;
            myWeight = this.getWeight();
        }
        public int getWeight(){
            return myValues.indexOf(myLet) + 1;
        }
    }
    class name{
        int myTotalWeight; //If you initialize an Integer, the default is null, not zero. That's why this was generating a null pointer exception
        String myName;

        public name(String hancock){
            myName = hancock;
        }
        public String getMyName(){
            return myName;
        }
        public int getJohn(){
            if(this.myName.equals("JOHN")) return 1;
            return 0;
        }

        public int getTotalWeight(){
            myTotalWeight = 0;
            String[] Lets = this.myName.split(""); //also, I know I could do this recursively. What would that look like?
            for(String let : Lets){
                letter thisOne = new letter(let);
                myTotalWeight += thisOne.getWeight();
            }
            return myTotalWeight;
        }
    }

    public String[] sort(String[] names) {
        name[] special = new name[names.length];
        for(int k = 0; k < names.length; k++){
            special[k] = new name(names[k]);
        }
        Arrays.sort(special, Comparator.comparing(name::getTotalWeight).reversed().thenComparing(name::getMyName));
        Arrays.sort(special, Comparator.comparing(name::getJohn).reversed()); //default sort returns values lowest to highest
        String[] ret = new String[special.length];
        for(int j = 0; j < special.length; j++){
            ret[j] = special[j].myName;
        }
        return ret;
    }
}
//this triggers a null pointer exception every time. Line 34. I'm not sure why. I find it absolutely fucking infuriating that I'm still, even now, unable to tell when my code is going to generate a fucking null pointer exception
//Arrays.sort(special, Comparator.comparing(name::getJohn));
//["94", "210", "177" ]