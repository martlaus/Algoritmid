package fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by mart on 7.09.15.
 */
public class Fibonacci {


    public static void main(String[] args) {
        System.out.println("Anwser " + find_index(5));
    }

    /**
     * @param n
     * @return Computes the n-th number in Fibonacci series
     */
    public static BigInteger fib(int n) {
        int prev1=0, prev2=1;

        for(int i=0; i<n; i++) {
            int savePrev1 = prev1;
            prev1 = prev2;
            prev2 = savePrev1 + prev2;
        }

        return BigInteger.valueOf(prev1);
    }

    public static int find_index(int precision) {
        BigDecimal goldenRatio = BigDecimal.valueOf(1.6180339887498949025);
        BigDecimal ratio = goldenRatio.setScale(precision, RoundingMode.UP);
        int n = 0;
        while (true) {
            BigDecimal result;
            BigDecimal a = new BigDecimal(fib(n));
            BigDecimal b = new BigDecimal(fib(n-1));

            if(n > 2){
                result = a.divide(b, precision, BigDecimal.ROUND_UP );

                if (result.equals(ratio)) {
                    return n;
                }
            }

            n++;
        }
    }
}
