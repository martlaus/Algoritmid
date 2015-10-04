package dancers;

/**
 * Created by mart on 2.10.15.
 */
public class Dancer implements IDancer, Comparable<Dancer> {
    public Dancer left;
    public Dancer right;
    public Dancer parent;
    public int key;
    public int balance;
    int height;
    int id = 0;
    boolean male;


    public Dancer(boolean male, int height) {
        this.male = male;
        this.key = height;
        this.height = height;
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
        return height;
    }

    public int getKey() {
        if (male) {
            return key - 10000;
        } else {
            return key;
        }
    }

    public void setKey(int height) {
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

    @Override
    public String toString() {
        return "{ " + this.isMale() + " " + this.getHeight() + " }";
    }
}
