package myTest;

import dancers.Dancer;
import dancers.Dancers;
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
            Dancer t = new Dancer(i, i, true);
            assertNull(dancers.findPartnerFor(t));
//            System.out.println(dancers.maleDancers.root);
        }
        assertEquals(6, dancers.returnWaitingList().size());
        int x = 6;
        for (int i : new int[]{18, 28, 44, 1, 8, 3}) {

            Dancer t = new Dancer(i, i, false);
            assertNotNull(dancers.findPartnerFor(t));
            x--;
            assertEquals(x, dancers.returnWaitingList().size());

        }

        assertEquals(0, dancers.returnWaitingList().size());
    }

    @Test
    public void testFindPartnersNoPartner() throws Exception {
        Dancers dancers = new Dancers();

        Dancer dancer = new Dancer(1, 180, false);
        Dancer dancer2 = new Dancer(2, 180, true);
        Dancer dancer3 = new Dancer(3, 178, true);
        Dancer dancer4 = new Dancer(4, 181, false);
        Dancer dancer5 = new Dancer(5, 182, false);
        Dancer dancer6 = new Dancer(6, 183, false);

        assertNull(dancers.findPartnerFor(dancer));
        assertNull(dancers.findPartnerFor(dancer2));
        assertNull(dancers.findPartnerFor(dancer3));
        assertNull(dancers.findPartnerFor(dancer4));
        assertNull(dancers.findPartnerFor(dancer5));
        assertNull(dancers.findPartnerFor(dancer6));

    }


    @Test
    public void testFindPartnersOnePartner() throws Exception {
        Dancers dancers = new Dancers();

        Dancer dancer = new Dancer(1, 180, false);
        Dancer dancer2 = new Dancer(2, 180, true);
        Dancer dancer3 = new Dancer(3, 178, true);
        Dancer dancer4 = new Dancer(4, 181, false);
        Dancer dancer5 = new Dancer(5, 182, false);
        Dancer dancer6 = new Dancer(6, 183, false);
        Dancer dancer7 = new Dancer(7, 179, false);

        assertNull(dancers.findPartnerFor(dancer));
        assertNull(dancers.findPartnerFor(dancer2));
        assertNull(dancers.findPartnerFor(dancer3));
        assertNull(dancers.findPartnerFor(dancer4));
        assertNull(dancers.findPartnerFor(dancer5));
        assertNull(dancers.findPartnerFor(dancer6));

//        System.out.println(dancers.maleDancers.root);
        assertEquals(dancer2, dancers.findPartnerFor(dancer7).getValue());

    }

    @Test
    public void testFindPartnersOnePartnerAnotherWay() throws Exception {
        Dancers dancers = new Dancers();

        Dancer dancer = new Dancer(1, 180, false);
        Dancer dancer2 = new Dancer(2, 180, true);
        Dancer dancer3 = new Dancer(3, 178, true);
        Dancer dancer4 = new Dancer(4, 181, false);
        Dancer dancer5 = new Dancer(5, 182, false);
        Dancer dancer6 = new Dancer(6, 183, false);
        Dancer dancer7 = new Dancer(7, 181, true);

        assertNull(dancers.findPartnerFor(dancer));
        assertNull(dancers.findPartnerFor(dancer2));
        assertNull(dancers.findPartnerFor(dancer3));
        assertNull(dancers.findPartnerFor(dancer4));
        assertNull(dancers.findPartnerFor(dancer5));
        assertNull(dancers.findPartnerFor(dancer6));
        assertEquals(dancer, dancers.findPartnerFor(dancer7).getValue());

    }

    /**
     *
     */
    @Test
    public void testSinglePair() throws Exception {
        Dancers dancers = new Dancers();

        Dancer dancer = new Dancer(1, 180, false);
        Dancer dancer2 = new Dancer(2, 181, true);
        assertNull(dancers.findPartnerFor(dancer));
        assertEquals(dancer, dancers.findPartnerFor(dancer2).getValue());


        assertTrue(dancers.returnWaitingList().size() == 0);
    }

    /**
     *
     */
    @Test
    public void testManyWithSameHeightOtherWay() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new Dancer(i, i, true)));
            assertNull(dancers.findPartnerFor(new Dancer(i * 7, i, true)));
        }

        for (int j = 50; j < 100; j++) {
            assertNull(dancers.findPartnerFor(new Dancer(j * 13, j, false)));
            assertNull(dancers.findPartnerFor(new Dancer(j * 29, j, false)));
        }
    }

    /**
     *
     */
    @Test
    public void testManyWithSameHeight() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new Dancer(i, i, true)));
        }

        for (int j = 50; j < 100; j++) {
            assertNull(dancers.findPartnerFor(new Dancer(j * 50, j, false)));
        }

//        System.out.println(dancers.femaleDancers.root);
        assertTrue(dancers.findPartnerFor(new Dancer(41923, 51, true)).getValue().getHeight() == 50);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSinglePairFemaleVariation() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new Dancer(i, 52, true)));
        }
        dancers.findPartnerFor(new Dancer(51, 51, true));

        assertTrue(dancers.findPartnerFor(new Dancer(52, 50, false)).getValue().getHeight() == 51);

    }

    /**
     * @throws Exception
     */
    @Test
    public void testSinglePairMaleVariation() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 50; i++) {
            assertNull(dancers.findPartnerFor(new Dancer(i, 50, false)));
        }
        dancers.findPartnerFor(new Dancer(51, 49, false));

        assertTrue(dancers.findPartnerFor(new Dancer(52, 50, true)).getValue().getHeight() == 49);
    }

    @Test
    public void testFindPartnersMultiplePossibilitiesFemale() throws Exception {
        Dancers dancers = new Dancers();

        // First one should be null
        Dancer dancer = new Dancer(1, 180, false);
        Dancer dancer2 = new Dancer(2, 181, true);
        Dancer dancer3 = new Dancer(3, 182, true);
        Dancer dancer4 = new Dancer(4, 183, true);
        Dancer dancer5 = new Dancer(5, 184, true);

        assertNull(dancers.findPartnerFor(dancer5));
        assertNull(dancers.findPartnerFor(dancer2));
        assertNull(dancers.findPartnerFor(dancer4));
        assertNull(dancers.findPartnerFor(dancer3));
        assertEquals(4, dancers.returnWaitingList().size());


        assertEquals(dancer2, dancers.findPartnerFor(dancer).getValue());
        assertEquals(3, dancers.returnWaitingList().size());

        assertEquals(dancer3, dancers.findPartnerFor(dancer).getValue());
        assertEquals(2, dancers.returnWaitingList().size());

        assertEquals(dancer4, dancers.findPartnerFor(dancer).getValue());
        assertEquals(1, dancers.returnWaitingList().size());

        assertEquals(dancer5, dancers.findPartnerFor(dancer).getValue());
    }

    @Test
    public void testFindPartnerMultiplePossibilities() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        Dancer dancer = new Dancer(1, 180, true);
        Dancer dancer2 = new Dancer(2, 179, false);
        Dancer dancer5 = new Dancer(3, 176, false);
        Dancer dancer3 = new Dancer(4, 178, false);
        Dancer dancer4 = new Dancer(5, 177, false);

        assertNull(dancers.findPartnerFor(dancer5));
        assertNull(dancers.findPartnerFor(dancer2));
        assertNull(dancers.findPartnerFor(dancer4));
        assertNull(dancers.findPartnerFor(dancer3));
        assertEquals(dancer2, dancers.findPartnerFor(dancer).getValue());
        assertEquals(dancer3, dancers.findPartnerFor(dancer).getValue());
        assertEquals(dancer4, dancers.findPartnerFor(dancer).getValue());
        assertEquals(dancer5, dancers.findPartnerFor(dancer).getValue());
    }


    @Test
    public void testFindPartnerForFemaleMalePair() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        Dancer dancer = new Dancer(1, 180, true);
        Dancer dancer2 = new Dancer(2, 179, false);

        assertNull(dancers.findPartnerFor(dancer));
        assertEquals(dancer, dancers.findPartnerFor(dancer2).getValue());
    }

    @Test
    public void testFindPartnerForFemaleMalePairAnotherWay() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        Dancer dancer = new Dancer(1, 180, true);
        Dancer dancer2 = new Dancer(2, 179, false);

        assertNull(dancers.findPartnerFor(dancer2));
        assertEquals(dancer2, dancers.findPartnerFor(dancer).getValue());
    }

    @Test
    public void testLongLinearLastMale() throws Exception {

        Dancers dancers = new Dancers();

        for (int i = 0; i <= 4; i++) {
            Dancer dancer = new Dancer(i, i, true);
            assertNull(dancers.findPartnerFor(dancer));
        }
        // First one should be null
        Dancer dancer2 = new Dancer(11, 3, false);

        //System.out.println(dancers.maleDancers.root);
        assertTrue(dancers.findPartnerFor(dancer2).getValue().getHeight() == 4);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testFindPartnerForNullTest() throws Exception {

        Dancers dancers = new Dancers();

        // First one should be null
        Dancer dancer = new Dancer(1, 180, true);

        assertNull(dancers.findPartnerFor(dancer));
    }

    /**
     * failib...
     */
    @Test
    public void testLongLinearFindLastFemale() throws Exception {
        Dancers dancers = new Dancers();

        for (int i = 0; i <= 10000; i++) {
            Dancer dancer = new Dancer(i, i, false);
            assertNull(dancers.findPartnerFor(dancer));
        }
        // First one should be null
        Dancer dancer2 = new Dancer(1000001, 1000001, true);

        assertTrue(dancers.findPartnerFor(dancer2).getValue().getHeight() == 10000);
    }
}
