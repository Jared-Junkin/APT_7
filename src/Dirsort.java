import java.util.Arrays;
import java.util.Comparator;

public class Dirsort {
    private class DirComp implements Comparator<String>{

        @Override
        public int compare(String a, String b) {
            String[] arr = a.split("/");
            String[] brr = b.split("/");
            if(arr.length > brr.length) return 1;
            if(arr.length < brr.length) return -1;
            for(int k = 0; k < arr.length; k++){
                if(arr[k].compareTo(brr[k]) != 0) return arr[k].compareTo(brr[k]);
            }
            return 0;
        }
    }
    public String[] sort(String[] dirs) {
        Arrays.sort(dirs, new DirComp());
        return dirs;
    }
}