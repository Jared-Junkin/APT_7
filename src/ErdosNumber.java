import javafx.scene.transform.Scale;

import java.util.*;

public class ErdosNumber {
    TreeMap<String, Set<String>> myGraph = new TreeMap<>();
    TreeMap<String, Integer> myDistance = new TreeMap<>();

    private Set<String> makeNamesSet(String[] names, String person){
        //this function takes in a string of names from a publication and returns a new set containing
        //all the coauthors in the paper.
        Set<String> ret = new TreeSet<>();
        for(String name : names){
            if(!name.equals(person)){
                ret.add(name);
            }
        }
        return ret;
    }
    //["ERDOS A", "A B", "B AA C" ]
    private void makeGraph(String[] pubs){
        //this code creates a graph containing the name of each person and a set containing everyone they have ever written a paper with
        for(String paper : pubs){
            String[] names = paper.split(" ");
            for(String person : names){
                if(!myGraph.containsKey(person)){
                    myGraph.put(person, makeNamesSet(names, person));
                }
                else{
                    for(String colleague: makeNamesSet(names, person)){
                        myGraph.get(person).add(colleague);
                        //I can't figure out how to use the addall command here, and I'd like to.
                    }
                }
            }
        }
    }

    private void bfsErdos(){
        Queue<String> qu = new LinkedList<>();
        Set<String> visited = new TreeSet<>();
        String str = "ERDOS";
        myDistance.put(str, 0);
        visited.add(str);
        qu.add(str);
        while(qu.size() > 0){
            String curr = qu.remove();
            for(String person : myGraph.get(curr)){
                if(!visited.contains(person)){
                    qu.add(person);
                    visited.add(person);
                    myDistance.put(person,myDistance.get(curr) + 1);
                }
            }
        }
    }

    public String[] calculateNumbers(String[] pubs) {
        makeGraph(pubs);
        ArrayList<String> list = new ArrayList<>();
        bfsErdos();
        for(String s : myGraph.keySet()){
            if(myDistance.containsKey(s)){
                s = s + " " + myDistance.get(s);
            }
            list.add(s);
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args){

        String[] pubs = {"JBGL YZRDPWRXZ TITDUEUTEV OTUNEYSHGJ REPDK PGN", "DL JFFYUOOAD XGN AGERJ HMPJGZSYLK", "KDQ YTNXNJO FNUIW UCSXVIRS GOXEYSG XUHIUB GYENEB", "HMPJGZSYLK YZRDPWRXZ VQ", "GWSIH GWFQ GKQSTCX PGN HMPJGZSYLK", "WJSJHJMU HLWFEDMDJN AICDM OWQIHOZWA", "DZTDQAIYRD UCSXVIRS HHCJL LEDLN JBGL OJPTQNV RF", "GOXEYSG WJSJHJMU DS JFFYUOOAD", "ZJW WALMZWHJO REPDK RDLEGYR HLWFEDMDJN AGERJ LKJZ", "MSP FZVTKQLW OTUNEYSHGJ DZTDQAIYRD", "GKBYHH SH ZTJSQQMWQ WJSJHJMU HHCJL SKTB", "WWYG ZJW OWQIHOZWA OCFLTDUVSQ GKBYHH", "RXRVLXV DS MOD OCFLTDUVSQ PQCYYURX", "HLWFEDMDJN QKZTVSHNZG NHWONQBEW", "HLWFEDMDJN OTUNEYSHGJ NHWONQBEW UZLWGBTAO", "OJPTQNV JYAWYBZ ERDOS JBGL PGN", "ZTJSQQMWQ EDHSYPG JYAWYBZ GYENEB", "RNEIRXBZVC DL UZLWGBTAO OJPTQNV GOXEYSG JIG XGN", "UCSXVIRS GKBYHH KDQ COSENI SF PQCYYURX PGN ZDR", "DZTDQAIYRD JMH GKQSTCX LMELIXMM", "GWFQ HLWFEDMDJN GJYXHQGA PGN SHMXQTMDPK", "ERDOS OWQIHOZWA RNEIRXBZVC MSP ZDR TJFOFOG JYAWYBZ", "DZTDQAIYRD ABBSY ZJW AZGMTN OWQIHOZWA MSP WWYG", "WYXPX GOJOQUUZDC MOD OWQIHOZWA SLHKUXU REPDK MSP", "HHCJL FRN KDQ WJSJHJMU RF GOJOQUUZDC", "REPDK WAAFQ GOXEYSG HLWFEDMDJN BECEBCVXVY JMH", "YTNXNJO RXRVLXV TITDUEUTEV MOD REPDK QKZTVSHNZG", "GKQSTCX QKZTVSHNZG BJET UFNJ", "GKBYHH EDHSYPG AICDM REPDK DL AGERJ GOXEYSG MOD", "RXRVLXV YTNXNJO JIG SKTB COSENI EDHSYPG NHWONQBEW", "EDHSYPG FA OJPTQNV TJFOFOG SLHKUXU MSP YTNXNJO"};
        ErdosNumber e = new ErdosNumber();
        String[] out = e.calculateNumbers(pubs);
        System.out.println(Arrays.toString(out));
        //prints [Ljava.lang.String;@56f4468b because there's no real easy way of printing an array. Must call toStrng.
    }
}