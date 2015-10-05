package dancers;

import java.util.List;

/**
 * Created by mart on 2.10.15.
 */
public class Dancer implements IDancer, Comparable<Dancer> {
    public Dancer left;
    public Dancer right;
    public Dancer parent;
    public int height;
    public int balance;
    int ID = 0;
    boolean male;
    boolean inList = false;
    List<Dancer> equalHeightDancers;

    public Dancer(int ID, boolean male, int height) {
        this.ID = ID;
        this.male = male;
        this.height = height;
        left = right = parent = null;
        balance = 0;
        this.inList = false;
    }

    public Dancer() {
        left = right = parent = null;
        balance = 0;
    }

    @Override
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Dancer o) {
        if (getHeight() < o.getHeight())
            return -1;
        else if (getHeight() == o.getHeight()) {
            if (isMale() == o.isMale())
                return 0;
            else if (isMale())
                return -1;
            else
                return 1;
        } else {
            return 1;
        }
    }

    public boolean isInList() {
        return inList;
    }

    public void setInList(boolean inList) {
        this.inList = inList;
    }

    public Dancer replace(Dancer replacement) {
        setID(replacement.getID());
        setMale(replacement.isMale());
        setHeight(replacement.getHeight());
        setLeft(this.left);
        setRight(this.right);
        setParent(this.parent);

        return this;
    }

    public void setLeft(Dancer left) {
        this.left = left;
    }

    public void setRight(Dancer right) {
        this.right = right;
    }

    public void setParent(Dancer parent) {
        this.parent = parent;
    }

    public List<Dancer> getEqualHeightDancers() {
        return equalHeightDancers;
    }

    public void setEqualHeightDancers(List<Dancer> equalHeightDancers) {
        this.equalHeightDancers = equalHeightDancers;
    }
    //    @Override
//    public int compareTo(Dancer o) {
//        if (this.getHeight() == o.getHeight()) {
//            if (o.isMale()) {
//                return -1;
//            } else return -1;
//        } else {
//            return this.getHeight() - o.getHeight();
//        }
//    }

    @Override
    public String toString() {
        return "{ " + this.getID() + " " + this.isMale() + " " + this.getHeight() + " }";
    }
}
