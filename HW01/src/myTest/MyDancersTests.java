package myTest;

import dancers.Dancer;
import dancers.Dancers;
import dancers.IDancer;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by mart on 4.10.15.
 */
public class MyDancersTests {

    @Test
    public void equalsTest() {
        Dancers dancers = new Dancers();
        dancers.addDancer(new Dancer(1, true, 175));
        dancers.addDancer(new Dancer(2, false, 175));
        dancers.addDancer(new Dancer(3, true, 180));
        dancers.addDancer(new Dancer(4, true, 190));
        dancers.addDancer(new Dancer(5, false, 200));
        dancers.addDancer(new Dancer(6, true, 210));
        dancers.addDancer(new Dancer(7, true, 185));
        dancers.addDancer(new Dancer(8, false, 190));
        dancers.addDancer(new Dancer(9, true, 200));
        assertEquals(9, dancers.returnWaitingList().size());

    }

    @Test
    public void testWithWomen() {
        Dancers dancers = new Dancers();
        dancers.addDancer(new Dancer(1, true, 175));
        dancers.addDancer(new Dancer(2, false, 175));
        dancers.addDancer(new Dancer(3, true, 180));
        dancers.addDancer(new Dancer(4, true, 190));
        dancers.addDancer(new Dancer(5, false, 200));
        dancers.addDancer(new Dancer(6, true, 210));
        dancers.addDancer(new Dancer(7, true, 185));
        dancers.addDancer(new Dancer(8, false, 190));
        dancers.addDancer(new Dancer(9, true, 200));

        List<IDancer> list = dancers.returnWaitingList();
        System.out.println("Waiting: ");
        for (IDancer d : list) {
//            System.out.println();
//            System.out.println("---------------------------------------------------");

            dancers.getAvlTree().debug((Dancer) d);
            System.out.println(d.toString());
        }
        System.out.println(list.size());


        assertEquals(9, dancers.returnWaitingList().size());

        AbstractMap.SimpleEntry<IDancer, IDancer> entry1 = dancers.findPartnerFor(new Dancer(1, false, 190));

        List<IDancer> list2 = dancers.returnWaitingList();
        System.out.println("Waiting 2: ");
        for (IDancer d : list2) {
//            System.out.println();
//            System.out.println("---------------------------------------------------");

            dancers.getAvlTree().debug((Dancer) d);
            System.out.println(d.toString());
        }


        assertEquals(8, dancers.returnWaitingList().size());
        assertEquals(190, entry1.getKey().getHeight());
        assertFalse(entry1.getKey().isMale());
        assertEquals(200, entry1.getValue().getHeight());
        assertTrue(entry1.getValue().isMale());

        System.out.println("SECOND Partner find try");
        AbstractMap.SimpleEntry<IDancer, IDancer> entry2 = dancers.findPartnerFor(new Dancer(1, true, 190));
        assertEquals(7, dancers.returnWaitingList().size());
        assertEquals(190, entry2.getKey().getHeight());
        assertTrue(entry2.getKey().isMale());
        assertEquals(175, entry2.getValue().getHeight());
        assertFalse(entry2.getValue().isMale());

    }

    @Test
    public void testFindPartnerFor() throws Exception {
        Dancers dancers = new Dancers();

        Dancer male1 = new Dancer(1, true, 190);
        Dancer male2 = new Dancer(1, true, 185);
        Dancer male3 = new Dancer(1, true, 178);

        Dancer female1 = new Dancer(1, false, 180);
        Dancer female2 = new Dancer(1, false, 177);
        Dancer female3 = new Dancer(1, false, 162);

        assertNull("there should be no match for male1", dancers.findPartnerFor(male1));
        assertThat("list should contain male1", dancers.returnWaitingList(), is(Arrays.asList(male1)));

        assertThat("there should be a match for female1 (male1)",
                dancers.findPartnerFor(female1),
                is(new AbstractMap.SimpleEntry<>(female1, male1)));

        assertEquals("list should be empty", dancers.returnWaitingList().size(), 0);

        assertNull("there should be no match for male2", dancers.findPartnerFor(male2));
        assertNull("there should be no match for male3", dancers.findPartnerFor(male3));

        assertThat("there should be a match for female2 (male3)",
                dancers.findPartnerFor(female2),
                is(new AbstractMap.SimpleEntry<>(female2, male3)));
        assertThat("there should be a match for female3 (male2)",
                dancers.findPartnerFor(female3),
                is(new AbstractMap.SimpleEntry<>(female3, male2)));
    }


    @Test
    public void testOnlyMenDance() {
        Dancers dancers = new Dancers();

        dancers.addDancer(new Dancer(1, true, 175));
        dancers.addDancer(new Dancer(1, true, 175));
        dancers.addDancer(new Dancer(1, true, 180));
        dancers.addDancer(new Dancer(1, true, 190));
        dancers.addDancer(new Dancer(1, true, 200));
        dancers.addDancer(new Dancer(1, true, 210));
        dancers.addDancer(new Dancer(1, true, 185));
        dancers.addDancer(new Dancer(1, true, 190));
        dancers.addDancer(new Dancer(1, true, 200));

        assertEquals(9, dancers.returnWaitingList().size());

        System.out.println(dancers.findPartnerFor(new Dancer(1, true, 150)));

        assertEquals(10, dancers.returnWaitingList().size());

        System.out.println(dancers.findPartnerFor(new Dancer(1, true, 250)));

        assertEquals(11, dancers.returnWaitingList().size());


    }
}
