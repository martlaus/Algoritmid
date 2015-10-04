package dancers;

import java.util.AbstractMap;
import java.util.List;

/**
 * Created by mart on 4.10.15.
 */
public class Main {
    public static void main(String[] args) {

        Dancers dancers = new Dancers();
        AbstractMap.SimpleEntry<IDancer, IDancer> d1 = dancers.findPartnerFor(new Dancer(true, 196));
        printString(d1);

        AbstractMap.SimpleEntry<IDancer, IDancer> d2 = dancers.findPartnerFor(new Dancer(false, 180));
        printString(d2);

        AbstractMap.SimpleEntry<IDancer, IDancer> d3 = dancers.findPartnerFor(new Dancer(true, 170));
        printString(d3);

        AbstractMap.SimpleEntry<IDancer, IDancer> d4 = dancers.findPartnerFor(new Dancer(true, 155));
        printString(d4);

        AbstractMap.SimpleEntry<IDancer, IDancer> d5 = dancers.findPartnerFor(new Dancer(false, 150));
        printString(d5);

        AbstractMap.SimpleEntry<IDancer, IDancer> d6 = dancers.findPartnerFor(new Dancer(false, 160));
        printString(d6);

        AbstractMap.SimpleEntry<IDancer, IDancer> d7 = dancers.findPartnerFor(new Dancer(true, 200));
        printString(d7);

        AbstractMap.SimpleEntry<IDancer, IDancer> d8 = dancers.findPartnerFor(new Dancer(true, 150));
        printString(d8);
        List<IDancer> list = dancers.returnWaitingList();
        for (IDancer d : list) {
            System.out.println();
            System.out.println("---------------------------------------------------");

            //dancers.getAvlTree().debug((Dancer) d);
            System.out.println(d.isMale() + " tantsija " + d.getHeight());
        }
        System.out.println(list.size());


    }


    public static void printString(AbstractMap.SimpleEntry<IDancer, IDancer> d1) {
        try {

            System.out.println("Found partner " + d1.getKey().isMale() + ": " + d1.getKey().getHeight()
                    + d1.getValue().isMale() + " :" + d1.getValue().getHeight());

        } catch (Exception e) {

        }
    }

}
