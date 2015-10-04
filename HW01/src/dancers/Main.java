package dancers;

/**
 * Created by mart on 4.10.15.
 */
public class Main {
    public static void main(String[] args) {

        Dancers dancers = new Dancers();
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(true, 196)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(true, 155)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(true, 175)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(true, 200)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(false, 380)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(false, 370)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(false, 360)));
        System.out.println("Found partner " + dancers.findPartnerFor(new Dancer(false, 350)));

        for (IDancer d : dancers.returnWaitingList()) {

            System.out.println(d.isMale() + " tantsija " + d.getHeight());
        }

    }

}
