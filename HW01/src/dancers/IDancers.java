package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * API specification for the 
 * functional call to be tested.
 * IMPORTANT! You *HAVE* to implement
 * the class named Dancers implementing
 * this interface in your solution.
 */
public interface IDancers {
    SimpleEntry findPartnerFor(IDancer d)
            throws IllegalArgumentException;

    /*
     * Returns waiting list as an array (both men and women)
     * Ordered shortest --> longest
     * If man and woman are having the same height, 
     * then ordering should be man, woman
     */
    List<IDancer> returnWaitingList();

}