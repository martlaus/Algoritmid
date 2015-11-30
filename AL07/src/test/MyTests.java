package test;

import org.junit.Test;
import stamps.Stamps;

import java.util.List;

/**
 * Created by mart on 30.11.15.
 */
public class MyTests {

    @Test
    public void testSum() {

        Stamps stamps = new Stamps();
        List<Integer> res = stamps.findstamps(100, new int[]{1, 10, 24, 30, 33, 36});
        System.out.println("Res: ");

        for (Integer i : res) {
            System.out.println(i);
        }

    }
}
