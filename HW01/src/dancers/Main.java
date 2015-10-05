package dancers;

import java.util.AbstractMap;
import java.util.List;

/**
 * Created by mart on 4.10.15.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("SORTED LIST");
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
        System.out.println(dancers);

        System.out.println("First search");
        AbstractMap.SimpleEntry<IDancer, IDancer> entry = dancers.findPartnerFor(new Dancer(10, false, 190));
        System.out.println(entry.getKey() + " = " + entry.getValue());
        System.out.println(dancers);

        System.out.println("Second search");
        System.out.println(dancers.findPartnerFor(new Dancer(11, true, 190)));
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


}
