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
        System.out.println(find_index(5));
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

        for (int n = 0; n < 100; n++) {
            BigDecimal result = null;
            BigDecimal a = new BigDecimal(fib(n));
            System.out.println("a " + a);

            BigDecimal b = new BigDecimal(fib(n-1));
            System.out.println("b " + b);


            if(n > 2){

                result = a.divide(b, precision);
                //result = new BigDecimal(fib(n).divide(fib(n-1)));
                System.out.println("res " + result);

            }
                if (result != null && result.equals(ratio)) {
                    System.out.println("vastus " + n);
                    return n;
                }

        }
    }
}
