package myTest;

import dancers.Dancers;
import dancers.IDancer;
import dancers.MyDancerNode;
import org.junit.Test;

import java.util.AbstractMap;

import static org.junit.Assert.*;

/**
 * Created by mart on 4.10.15.
 */
public class MyDancersTests {

    @Test
    public void equalsTest() {
        Dancers dancers = new Dancers();
        dancers.addDancer(new MyDancerNode(1, true, 175));
        dancers.addDancer(new MyDancerNode(2, false, 175));
        dancers.addDancer(new MyDancerNode(3, true, 180));
        dancers.addDancer(new MyDancerNode(4, true, 190));
        dancers.addDancer(new MyDancerNode(5, false, 200));
        dancers.addDancer(new MyDancerNode(6, true, 210));
        dancers.addDancer(new MyDancerNode(7, true, 185));
        dancers.addDancer(new MyDancerNode(8, false, 190));
        dancers.addDancer(new MyDancerNode(9, true, 200));
        assertEquals(9, dancers.returnWaitingList().size());

    }

//    @Test
//    public void testFindPartnerFor() throws Exception {
//        Dancers dancers = new Dancers();
//
//        MyDancerNode male1 = new MyDancerNode(1, true, 190);
//        MyDancerNode male2 = new MyDancerNode(1, true, 185);
//        MyDancerNode male3 = new MyDancerNode(1, true, 178);
//
//        MyDancerNode female1 = new MyDancerNode(1, false, 180);
//        MyDancerNode female2 = new MyDancerNode(1, false, 177);
//        MyDancerNode female3 = new MyDancerNode(1, false, 162);
//
//        assertNull("there should be no match for male1", dancers.findPartnerFor(male1));
//        List<IDancer> list1 = dancers.returnWaitingList();
//        List<IDancer> list2 = Arrays.asList(male1);
//
//        assertThat("list should contain male1", dancers.returnWaitingList(), is(Arrays.asList(male1)));
//
//        assertThat("there should be a match for female1 (male1)",
//                dancers.findPartnerFor(female1),
//                is(new AbstractMap.SimpleEntry<>(female1, male1)));
//
//        assertEquals("list should be empty", dancers.returnWaitingList().size(), 0);
//
//        assertNull("there should be no match for male2", dancers.findPartnerFor(male2));
//        assertNull("there should be no match for male3", dancers.findPartnerFor(male3));
//
//        assertThat("there should be a match for female2 (male3)",
//                dancers.findPartnerFor(female2),
//                is(new AbstractMap.SimpleEntry<>(female2, male3)));
//        assertThat("there should be a match for female3 (male2)",
//                dancers.findPartnerFor(female3),
//                is(new AbstractMap.SimpleEntry<>(female3, male2)));
//    }


    @Test
    public void testOnlyMenDance() {
        Dancers dancers = new Dancers();

        dancers.addDancer(new MyDancerNode(1, true, 175));
        dancers.addDancer(new MyDancerNode(1, true, 175));
        dancers.addDancer(new MyDancerNode(1, true, 180));
        dancers.addDancer(new MyDancerNode(1, true, 190));
        dancers.addDancer(new MyDancerNode(1, true, 200));
        dancers.addDancer(new MyDancerNode(1, true, 210));
        dancers.addDancer(new MyDancerNode(1, true, 185));
        dancers.addDancer(new MyDancerNode(1, true, 190));
        dancers.addDancer(new MyDancerNode(1, true, 200));

        assertEquals(9, dancers.returnWaitingList().size());

        dancers.findPartnerFor(new MyDancerNode(1, true, 150));
        assertEquals(10, dancers.returnWaitingList().size());

        dancers.findPartnerFor(new MyDancerNode(1, true, 250));
        assertEquals(11, dancers.returnWaitingList().size());
    }

    @Test
    public void testWithWomen() {
        Dancers dancers = new Dancers();
        MyDancerNode d1 = new MyDancerNode(1, true, 175);
        MyDancerNode d2 = new MyDancerNode(2, false, 175);
        MyDancerNode d3 = new MyDancerNode(3, true, 180);
        MyDancerNode d4 = new MyDancerNode(4, true, 190);
        MyDancerNode d5 = new MyDancerNode(5, false, 200);
        MyDancerNode d6 = new MyDancerNode(6, true, 210);
        MyDancerNode d7 = new MyDancerNode(7, true, 185);
        MyDancerNode d8 = new MyDancerNode(8, false, 190);
        MyDancerNode d9 = new MyDancerNode(9, true, 200);
        dancers.addDancer(d1);
        dancers.addDancer(d2);
        dancers.addDancer(d3);
        dancers.addDancer(d4);
        dancers.addDancer(d5);
        dancers.addDancer(d6);
        dancers.addDancer(d7);
        dancers.addDancer(d8);
        dancers.addDancer(d9);


        assertEquals(9, dancers.returnWaitingList().size());

        AbstractMap.SimpleEntry<IDancer, IDancer> entry1 = dancers.findPartnerFor(new MyDancerNode(1, false, 190));

        assertEquals(8, dancers.returnWaitingList().size());
        assertEquals(190, entry1.getKey().getHeight());
        assertFalse(entry1.getKey().isMale());
        assertEquals(200, entry1.getValue().getHeight());
        assertTrue(entry1.getValue().isMale());

        AbstractMap.SimpleEntry<IDancer, IDancer> entry2 = dancers.findPartnerFor(new MyDancerNode(1, true, 190));
        assertEquals(7, dancers.returnWaitingList().size());
        assertEquals(190, entry2.getKey().getHeight());
        assertTrue(entry2.getKey().isMale());
        assertEquals(175, entry2.getValue().getHeight());
        assertFalse(entry2.getValue().isMale());

    }

}
