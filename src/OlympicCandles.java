import java.util.*;

public class OlympicCandles{
    public int CountZeros(int[] candles){
        int count = 0;
        for(int x : candles){
            if(x == 0){
                count++;
            }
        }
        return count;
    }

    public int reverseCompare(int a, int b){
        //I suppose it's implicit that a is this and b is the second one. 
        if(a >= b) return -1;
        return 1;
    }

    private PriorityQueue<Integer> makeQueue(int[] candles){
        PriorityQueue<Integer> ret = new PriorityQueue<>(this::reverseCompare);
        for(int candle : candles){
            ret.add(candle);
        }
        return ret;
    }

    public int numberOfNights(int[] candles){
        PriorityQueue<Integer> pq = makeQueue(candles);
        int count = 0;
        while(true){
            List<Integer> tempList = new ArrayList<>();
            for(int k = 0; k <= count; k++){
                if(pq.size() <= 0) return count;
                int temp = pq.remove();
                if(temp == 0) return count;
                temp--;
                tempList.add(temp);
            }
            pq.addAll(tempList);
            count++;
        }
    }
}