package dancers;

import java.util.Comparator;

/**
 * Created by mart on 4.10.15.
 */
public class MyComparator implements Comparator<IDancer> {
    @Override
    public int compare(IDancer o1, IDancer o2) {
        Dancer d1 = (Dancer) o1;
        Dancer d2 = (Dancer) o2;
        return d1.compareTo(d2);
    }
}
