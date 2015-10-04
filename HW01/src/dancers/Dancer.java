package dancers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mart on 2.10.15.
 */
public class Dancer implements IDancer, Comparable<Dancer> {
    public Dancer left;
    public Dancer right;
    public Dancer parent;
    public int key;
    public int balance;
    int id = 0;
    boolean male;
    AtomicInteger atomicInteger = new AtomicInteger();


    public Dancer(boolean male, int height) {
        this.id = atomicInteger.incrementAndGet();
        this.male = male;
        this.key = height;
        left = right = parent = null;
        balance = 0;
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
        if (male) {
            return key - 10000;
        } else {
            return key;
        }
    }

    public void setHeight(int height) {
        this.key = height;
    }


    @Override
    public int compareTo(Dancer o) {
        if (this.getHeight() == o.getHeight()) {
            if (o.isMale()) {
                return 1;
            } else return -1;
        } else {
            return this.getHeight() - o.getHeight();
        }
    }
}
