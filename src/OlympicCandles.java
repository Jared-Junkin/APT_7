import java.util.Arrays;
import java.util.Comparator;

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

    public int numberOfNights(int[] candles){
        int count = 0;
        int dex = candles.length -1;
        while(CountZeros(candles) < candles.length - count){
            count++;
            dex--;
            Arrays.sort(candles);
            for(int j = candles.length - 1; j > dex ; j--){
                candles[j]--;
            }
        }
       return count;
    }
}