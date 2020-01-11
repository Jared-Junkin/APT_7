import java.util.*;

public class ClientsList {
    private class getter{
        String myFirst;
        String myLast;
        public getter(String person){
            myFirst = person.split(" ")[0];
            myLast = person.split(" ")[1];
        }
        public String getFirst(){
            return this.myFirst;
        }
        public String getLast(){
            return this.myLast;
        }
        @Override
        public String toString(){
            return myFirst + " " + myLast;
        }
    }
    public String[] dataCleanup(String[] names) {
        List<getter> people = new ArrayList<>();
        for(String name : names){
            if(name.indexOf(",") != -1){
                String newName = name.split(", ")[1] + " " + name.split(", ")[0];
                getter you = new getter(newName);
                if(!people.contains(you)){
                    people.add(you);
                }
            }
            else{
                getter you = new getter(name);
                if(!people.contains(you)){
                    people.add(you);
                }
            }
        }
        getter[] x = people.toArray(new getter[0]); //Does this not fucking convert the list to an array
        Arrays.sort(x, Comparator.comparing(getter::getLast).thenComparing(getter::getFirst));
        String[] ret = new String[x.length];
        for(int k = 0 ; k < ret.length; k++){
            ret[k] = x[k].toString();
        }
        return ret;
    }
    public static void main(String[] args){
        String[] names = {"Banks, Cody", "Cody Banks", "Tod Wilson"};
        ClientsList c = new ClientsList();
        System.out.println(c.dataCleanup(names));

    }
}