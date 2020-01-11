import java.util.HashMap;

public class HuffmanDecoding {
    StringBuilder ret = new StringBuilder();
    private HashMap<String, String> makeDict(String[] dictionary){
        String letDict = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String, String> ret = new HashMap<>();
        for(int k = 0; k < dictionary.length; k++){
            ret.put(dictionary[k], letDict.substring(k, k+1)); //the binary, then the letter.
        }
        return ret;
    }
    public void buildString(String archive, HashMap<String, String> myMap){
        if(archive.length() < 1) return;
        for(int i = 1; i < archive.length() + 1; i++){ //changing from archive.length to archive.length + 1 made all the difference.
            //I think that's because the last i value was archive.length - 1.
            //substring(0, string.length) would return the entire string, becaus the upper bounds are exclusive.
            // Since the last i value my code originally produced was archive.length - 1 (and this is the INDEX of the last character)
            //the interval substring(0, archive.length - 1) actually misses out on the last character.
            String decoding = archive.substring(0, i);
            if(myMap.containsKey(decoding)) { //if the binary sequence decoding is in myMap...
                ret.append(myMap.get(decoding));
                buildString(archive.substring(i, archive.length()), myMap);
                break;
            }
        }
    }
    public String decode(String archive, String[] dictionary) {
        HashMap<String, String> myMap = makeDict(dictionary);
        buildString(archive, myMap);
        return ret.toString();
    }
}