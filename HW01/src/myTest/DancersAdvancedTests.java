package myTest;

import dancers.Dancers;
import dancers.MyDancerNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mart on 5.10.15.
 */
public class DancersAdvancedTests {


    @Test
    public void testBeginListEmpty() throws Exception {
        Dancers dancers = new Dancers();
        assertEquals(0, dancers.returnWaitingList().size());
    }

    @Test
    public void testFindParntersComplexTreeSequenceShouldEnedWithZero() {
        Dancers dancers = new Dancers();

        for (int i : new int[]{4, 9, 2, 45, 29, 19}) {
            MyDancerNode t = new MyDancerNode(i, i, true);
            assertNull(dancers.findPartnerFor(t));
//            System.out.println(dancers.maleDancers.root);
        }
        assertEquals(6, dancers.returnWaitingList().size());
        int x = 6;
        for (int i : new int[]{18, 28, 44, 1, 8, 3}) {

            MyDancerNode t = new MyDancerNode(i, i, false);
            assertNotNull(dancers.findPartnerFor(t));
            x--;
            assertEquals(x, dancers.returnWaitingList().size());

        }

        assertEquals(0, dancers.returnWaitingList().size());
    }

    @Test
    public void testFindPartnersNoPartner() throws Exception {
        Dancers dancers = new Dancers();

        MyDancerNode myDancerNode = new MyDancerNode(1, 180, false);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 180, true);
        MyDancerNode myDancerNode3 = new MyDancerNode(3, 178, true);
        MyDancerNode myDancerNode4 = new MyDancerNode(4, 181, false);
        MyDancerNode myDancerNode5 = new MyDancerNode(5, 182, false);
        MyDancerNode myDancerNode6 = new MyDancerNode(6, 183, false);

        assertNull(dancers.findPartnerFor(myDancerNode));
        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertNull(dancers.findPartnerFor(myDancerNode3));
        assertNull(dancers.findPartnerFor(myDancerNode4));
        assertNull(dancers.findPartnerFor(myDancerNode5));
        assertNull(dancers.findPartnerFor(myDancerNode6));

    }


    @Test
    public void testFindPartnersOnePartner() throws Exception {
        Dancers dancers = new Dancers();

        MyDancerNode myDancerNode = new MyDancerNode(1, 180, false);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 180, true);
        MyDancerNode myDancerNode3 = new MyDancerNode(3, 178, true);
        MyDancerNode myDancerNode4 = new MyDancerNode(4, 181, false);
        MyDancerNode myDancerNode5 = new MyDancerNode(5, 182, false);
        MyDancerNode myDancerNode6 = new MyDancerNode(6, 183, false);
        MyDancerNode myDancerNode7 = new MyDancerNode(7, 179, false);

        assertNull(dancers.findPartnerFor(myDancerNode));
        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertNull(dancers.findPartnerFor(myDancerNode3));
        assertNull(dancers.findPartnerFor(myDancerNode4));
        assertNull(dancers.findPartnerFor(myDancerNode5));
        assertNull(dancers.findPartnerFor(myDancerNode6));

//        System.out.println(dancers.maleDancers.root);
        assertEquals(myDancerNode2, dancers.findPartnerFor(myDancerNode7).getValue());

    }

    @Test
    public void testFindPartnersOnePartnerAnotherWay() throws Exception {
        Dancers dancers = new Dancers();

        MyDancerNode myDancerNode = new MyDancerNode(1, 180, false);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 180, true);
        MyDancerNode myDancerNode3 = new MyDancerNode(3, 178, true);
        MyDancerNode myDancerNode4 = new MyDancerNode(4, 181, false);
        MyDancerNode myDancerNode5 = new MyDancerNode(5, 182, false);
        MyDancerNode myDancerNode6 = new MyDancerNode(6, 183, false);
        MyDancerNode myDancerNode7 = new MyDancerNode(7, 181, true);

        assertNull(dancers.findPartnerFor(myDancerNode));
        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertNull(dancers.findPartnerFor(myDancerNode3));
        assertNull(dancers.findPartnerFor(myDancerNode4));
        assertNull(dancers.findPartnerFor(myDancerNode5));
        assertNull(dancers.findPartnerFor(myDancerNode6));
        assertEquals(myDancerNode, dancers.findPartnerFor(myDancerNode7).getValue());

    }

    /**
     *
     */
    @Test
    public void testSinglePair() throws Exception {
        Dancers dancers = new Dancers();

        MyDancerNode myDancerNode = new MyDancerNode(1, 180, false);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 181, true);
        assertNull(dancers.findPartnerFor(myDancerNode));
        assertEquals(myDancerNode, dancers.findPartnerFor(myDancerNode2).getValue());


        assertTrue(dancers.returnWaitingList().size() == 0);
    }

    /**
     *
     */
    @Test
    public void testManyWithSameHeightOtherWay() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(i, i, true)));
            assertNull(dancers.findPartnerFor(new MyDancerNode(i * 7, i, true)));
        }

        for (int j = 50; j < 100; j++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(j * 13, j, false)));
            assertNull(dancers.findPartnerFor(new MyDancerNode(j * 29, j, false)));
        }
    }

    /**
     *
     */
    @Test
    public void testManyWithSameHeight() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(i, i, true)));
        }

        for (int j = 50; j < 100; j++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(j * 50, j, false)));
        }

//        System.out.println(dancers.femaleDancers.root);
        assertTrue(dancers.findPartnerFor(new MyDancerNode(41923, 51, true)).getValue().getHeight() == 50);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSinglePairFemaleVariation() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(i, 52, true)));
        }
        dancers.findPartnerFor(new MyDancerNode(51, 51, true));

        assertTrue(dancers.findPartnerFor(new MyDancerNode(52, 50, false)).getValue().getHeight() == 51);

    }

    /**
     * @throws Exception
     */
    @Test
    public void testSinglePairMaleVariation() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new MyDancerNode(i, 50, false)));
        }
        dancers.findPartnerFor(new MyDancerNode(51, 49, false));

        assertTrue(dancers.findPartnerFor(new MyDancerNode(52, 50, true)).getValue().getHeight() == 49);
    }

    @Test
    public void testFindPartnersMultiplePossibilitiesFemale() throws Exception {
        Dancers dancers = new Dancers();

        // First one should be null
        MyDancerNode myDancerNode = new MyDancerNode(1, 180, false);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 181, true);
        MyDancerNode myDancerNode3 = new MyDancerNode(3, 182, true);
        MyDancerNode myDancerNode4 = new MyDancerNode(4, 183, true);
        MyDancerNode myDancerNode5 = new MyDancerNode(5, 184, true);

        assertNull(dancers.findPartnerFor(myDancerNode5));
        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertNull(dancers.findPartnerFor(myDancerNode4));
        assertNull(dancers.findPartnerFor(myDancerNode3));
        assertEquals(4, dancers.returnWaitingList().size());


        assertEquals(myDancerNode2, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(3, dancers.returnWaitingList().size());

        assertEquals(myDancerNode3, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(2, dancers.returnWaitingList().size());

        assertEquals(myDancerNode4, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(1, dancers.returnWaitingList().size());

        assertEquals(myDancerNode5, dancers.findPartnerFor(myDancerNode).getValue());
    }

    @Test
    public void testFindPartnerMultiplePossibilities() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        MyDancerNode myDancerNode = new MyDancerNode(1, 180, true);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 179, false);
        MyDancerNode myDancerNode5 = new MyDancerNode(3, 176, false);
        MyDancerNode myDancerNode3 = new MyDancerNode(4, 178, false);
        MyDancerNode myDancerNode4 = new MyDancerNode(5, 177, false);

        assertNull(dancers.findPartnerFor(myDancerNode5));
        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertNull(dancers.findPartnerFor(myDancerNode4));
        assertNull(dancers.findPartnerFor(myDancerNode3));
        assertEquals(myDancerNode2, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(myDancerNode3, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(myDancerNode4, dancers.findPartnerFor(myDancerNode).getValue());
        assertEquals(myDancerNode5, dancers.findPartnerFor(myDancerNode).getValue());
    }


    @Test
    public void testFindPartnerForFemaleMalePair() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        MyDancerNode myDancerNode = new MyDancerNode(1, 180, true);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 179, false);

        assertNull(dancers.findPartnerFor(myDancerNode));
        assertEquals(myDancerNode, dancers.findPartnerFor(myDancerNode2).getValue());
    }

    @Test
    public void testFindPartnerForFemaleMalePairAnotherWay() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        MyDancerNode myDancerNode = new MyDancerNode(1, 180, true);
        MyDancerNode myDancerNode2 = new MyDancerNode(2, 179, false);

        assertNull(dancers.findPartnerFor(myDancerNode2));
        assertEquals(myDancerNode2, dancers.findPartnerFor(myDancerNode).getValue());
    }

    @Test
    public void testLongLinearLastMale() throws Exception {

        Dancers dancers = new Dancers();

        for (int i = 0; i <= 4; i++) {
            MyDancerNode myDancerNode = new MyDancerNode(i, i, true);
            assertNull(dancers.findPartnerFor(myDancerNode));
        }
        // First one should be null
        MyDancerNode myDancerNode2 = new MyDancerNode(11, 3, false);

        //System.out.println(dancers.maleDancers.root);
        assertTrue(dancers.findPartnerFor(myDancerNode2).getValue().getHeight() == 4);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testFindPartnerForNullTest() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        MyDancerNode myDancerNode = new MyDancerNode(1, 180, true);

        assertNull(dancers.findPartnerFor(myDancerNode));
    }

    /**
     * failib...
     */
    @Test
    public void testLongLinearFindLastFemale() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 10000; i++) {
            MyDancerNode myDancerNode = new MyDancerNode(i, i, false);
            assertNull(dancers.findPartnerFor(myDancerNode));
        }
        // First one should be null
        MyDancerNode myDancerNode2 = new MyDancerNode(1000001, 1000001, true);

        assertTrue(dancers.findPartnerFor(myDancerNode2).getValue().getHeight() == 10000);
    }
}
