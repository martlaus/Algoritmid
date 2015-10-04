package dancers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mart on 2.10.15.
 */
public class Dancer implements IDancer{
    int id = 0;
    boolean male;
    int height;
    AtomicInteger atomicInteger = new AtomicInteger();

    public Dancer() {}

    public Dancer(boolean male, int height) {
        this.id = atomicInteger.incrementAndGet();
        this.male = male;
        this.height = height;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public boolean isMale() {
        return male;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
