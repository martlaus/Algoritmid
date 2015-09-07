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
        //System.out.println(Fibonacci.fib(7));
        fib(7);
//        int index = 0;
//        while(true) {
//            System.out.println(Fibonacci.fib(index));
//            index++;
//        }
    }

    /**
     * @param n
     * @return Computes the n-th number in Fibonacci series
     */
    public static BigInteger fib(int n) {

        int f1 = 0;
        int f2 = 1;
        int fn = 0;
        for ( int i = 2; i < n; i++ )
        {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
            System.out.println(fn);
        }

//        if(n == 0) return BigInteger.valueOf(0);
//        if(n <= 2) return BigInteger.valueOf(1);
//
//        return fib(n - 1).add(fib(n - 2));
        return null;
    }

    public static int find_index(int precision) {
        System.out.println("Golden ratio: " + goldenRatio.setScale(5, RoundingMode.HALF_UP));

        return precision;
    }
}
