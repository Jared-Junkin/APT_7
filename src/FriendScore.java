
import java.util.*;

public class FriendScore {
    //note. This can be done using a helper method or with the operation twoFriends performs inside the highestScore loop.
    //it wasn't working earlier because I was adding things to the same Set I was iterating through, and that was messing things up.
    //It also can be done using a hashSet or a treeSet. Either works.

    Map<Integer, Set<Integer>> myMap = new HashMap<>();
    public void makeMap(String[] friends){
        for(int k = 0; k < friends.length ; k++){
            myMap.put(k, new HashSet<>());
            for(int j = 0; j <friends.length; j++){
                if(friends[k].substring(j, j + 1).equals("Y")){
                    myMap.get(k).add(j);
                }
            }
        }
    }
    private Set<Integer> twoFriends(int index){ //returns set of all friends + all mutual friends
        Set<Integer> mutuals = new HashSet<>();
        Set<Integer> friends = myMap.get(index);
        mutuals.addAll(friends);
        for(int friend : friends){
            mutuals.addAll(myMap.get(friend));
        }
        mutuals.remove(index);
        return mutuals;
    }
    public int highestScore(String[] friends) {
        makeMap(friends);
        int max = 0;
        for(int key : myMap.keySet()){
            Set<Integer> myFriends = myMap.get(key);
            Set<Integer> tot = new HashSet<>();
            tot.addAll(myFriends);
            for(int friend : myFriends){
                tot.addAll(myMap.get(friend));
            }
            tot.remove(key);
            int total = tot.size();
            //int total = twoFriends(key).size();
            if(total > max){
                max = total;
            }
        }
        return max;
    }
}
