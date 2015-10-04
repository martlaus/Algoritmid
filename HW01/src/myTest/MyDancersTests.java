package myTest;

import dancers.Dancer;
import dancers.Dancers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mart on 4.10.15.
 */
public class MyDancersTests {

    @Test
    public void testOnlyMenDance() {
        Dancers dancers = new Dancers();

        dancers.addDancer(new Dancer(true, 175));
        dancers.addDancer(new Dancer(true, 175));
        dancers.addDancer(new Dancer(true, 180));
        dancers.addDancer(new Dancer(true, 190));
        dancers.addDancer(new Dancer(true, 200));
        dancers.addDancer(new Dancer(true, 210));
        dancers.addDancer(new Dancer(true, 185));
        dancers.addDancer(new Dancer(true, 190));
        dancers.addDancer(new Dancer(true, 200));

        assertEquals(9, dancers.returnWaitingList().size());
    }
}
