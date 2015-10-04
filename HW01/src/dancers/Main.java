package dancers;

import java.util.AbstractMap;
import java.util.List;

/**
 * Created by mart on 4.10.15.
 */
public class Main {
    public static void main(String[] args) {

//        Dancers myDancers = new Dancers();
//        AbstractMap.SimpleEntry<IDancer, IDancer> d1 = myDancers.findPartnerFor(new Dancer(true, 196));
//        printString(d1);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d2 = myDancers.findPartnerFor(new Dancer(false, 180));
//        printString(d2);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d3 = myDancers.findPartnerFor(new Dancer(true, 170));
//        printString(d3);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d4 = myDancers.findPartnerFor(new Dancer(true, 155));
//        printString(d4);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d5 = myDancers.findPartnerFor(new Dancer(false, 150));
//        printString(d5);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d6 = myDancers.findPartnerFor(new Dancer(false, 160));
//        printString(d6);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d7 = myDancers.findPartnerFor(new Dancer(true, 200));
//        printString(d7);
//
//        AbstractMap.SimpleEntry<IDancer, IDancer> d8 = myDancers.findPartnerFor(new Dancer(true, 150));
//        printString(d8);
//        List<IDancer> list = myDancers.returnWaitingList();
//        for (IDancer d : list) {
//            System.out.println();
//            System.out.println("---------------------------------------------------");
//
//            myDancers.getAvlTree().debug((Dancer) d);
//            System.out.println(d.isMale() + " tantsija " + d.getHeight());
//        }
//        System.out.println(list.size());

        System.out.println("SORTED LIST");
        Dancers dancers = new Dancers();
        dancers.addDancer(new Dancer(true, 175));
        dancers.addDancer(new Dancer(false, 175));
        dancers.addDancer(new Dancer(true, 180));
        dancers.addDancer(new Dancer(true, 190));
        dancers.addDancer(new Dancer(false, 200));
        dancers.addDancer(new Dancer(true, 210));
        dancers.addDancer(new Dancer(true, 185));
        dancers.addDancer(new Dancer(false, 190));
        dancers.addDancer(new Dancer(true, 200));
        System.out.println(dancers);

        System.out.println("First search");
        AbstractMap.SimpleEntry<IDancer, IDancer> entry = dancers.findPartnerFor(new Dancer(false, 190));
        System.out.println(entry.getKey() + " = " + entry.getValue());
        System.out.println(dancers);

        System.out.println("Second search");
        System.out.println(dancers.findPartnerFor(new Dancer(true, 190)));
        System.out.println(dancers);


        List<IDancer> list = dancers.returnWaitingList();
        for (IDancer d : list) {
//            System.out.println();
//            System.out.println("---------------------------------------------------");

            dancers.getAvlTree().debug((Dancer) d);
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
