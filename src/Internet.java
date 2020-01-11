
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Internet {
    private TreeMap<String, Set<String>> myGraph = new TreeMap<>();

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

    private void makeGraph(String[] pubs){
        //this code creates a graph containing the routers each router is connected with.
        //Be very careful you're making the right graph. Each index in this represents the router, and the thing in the router represents its connections
        for(int k = 0; k < pubs.length; k++){
            String[] names = pubs[k].split(" ");
            myGraph.put("" + k, makeNamesSet(names, "" + k));
        }
    }

    private int connectedFinds(TreeMap<String, Set<String>> TempGraph, String router){
        TempGraph.remove(router);
        String first = "0";
        if(router.equals(first)){
            first = "1";
        }
        Set<String> visited = new TreeSet<>();
        Stack<String> qu = new Stack<>();
        visited.add(first);
        qu.push(first);

        while(qu.size() > 0){
            String v = qu.pop();
            for(String node : TempGraph.get(v)){
                if(!node.equals(router)){ //if this if statement isn't in there, the code will generate a
                    // null pointer exception because router will be in the connections for the other nodes, and it
                    // will be added to the queue, and when you call TempGraph.get(null) you're fucked.
                    if(!visited.contains(node)){
                        visited.add(node);
                        qu.push(node);
                    }
                }
            }
        }
        return visited.size();
    }
    public int articulationPoints(String[] routers) {
        if(routers == null) return 0;
        if(routers.length == 1) return 0;
        //if(routers.length == 2) return 0; //Why did I think I needed this?
        // Because I was worried my base connectedfinds wouldn't correctly account for the definition of an articulartion point
        //when the graph was short, but it does.
        makeGraph(routers);
        int myTot = myGraph.keySet().size();
        int artPoints = 0;
        for(String router : myGraph.keySet()){
            TreeMap<String, Set<String>> TempGraph = (TreeMap<String, Set<String>>) myGraph.clone();
            int connections = connectedFinds(TempGraph, router); //will I need to copy this?
            if(connections < myTot - 1) artPoints++;
        }
        return artPoints;
    }
    public static void main(String[] args){
        String[] routers =  {"2", "2 3", "0 1 3 4", "1 2", "2 5 6", "4 6", "4 5"};
        Internet e = new Internet();
        int out = e.articulationPoints(routers);
        System.out.println(out);
    }
}