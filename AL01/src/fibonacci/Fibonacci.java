package fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by mart on 7.09.15.
 */
public class Fibonacci {
    public static BigDecimal goldenRatio = BigDecimal.valueOf(1.6180339887498949025);


    public static void main(String[] args) {
        System.out.println("Starting... " + find_index(5));
        System.out.println("YAY" + find_index(10));
//        int n = 0;
//        while(true) {
//            System.out.println(fib(n));
//            n++;
//        }

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
        BigDecimal ratio = goldenRatio.setScale(precision, RoundingMode.HALF_UP);
        System.out.println("Golden ratio: " + goldenRatio.setScale(precision, RoundingMode.HALF_UP));
        int n = 0;
        while (true) {
            BigDecimal result = null;
            BigDecimal a = new BigDecimal(fib(n));
            System.out.println("a " + a);

            BigDecimal b = new BigDecimal(fib(n-1));
            System.out.println("b " + b);


            if(n > 2){
                result = a.divide(b, precision, BigDecimal.ROUND_HALF_UP );
                //result = new BigDecimal(fib(n).divide(fib(n-1)));
                System.out.println("res " + result);
                System.out.println("ratio " + ratio);

                if (result.equals(ratio)) {
                    System.out.println("Vastus " + n++);
                    return n;
                }
            }

            n++;
        }
    }
}
