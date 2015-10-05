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
    public List<Dancer> equalHeightDancers;
    public IDancer originalIDancer;
    int ID = 0;
    boolean male;
    boolean inList = false;

    public Dancer(int ID, boolean male, int height) {
        this.ID = ID;
        this.male = male;
        this.height = height;
        left = right = parent = null;
        balance = 0;
        this.inList = false;
        this.originalIDancer = this;
    }

    public Dancer() {
        left = right = parent = null;
        balance = 0;
        this.originalIDancer = this;
    }

    public Dancer(int ID, int height, boolean male) {
        this.ID = ID;
        this.male = male;
        this.height = height;
        left = right = parent = null;
        balance = 0;
        this.inList = false;
        this.originalIDancer = this;
    }

    public Dancer(Dancer dancer) {
        this.left = dancer.left;
        this.right = dancer.right;
        this.parent = dancer.parent;
        this.height = dancer.getHeight();
        this.balance = dancer.balance;
        this.ID = dancer.ID;
        this.male = dancer.male;
        this.inList = dancer.inList;
        this.equalHeightDancers = dancer.equalHeightDancers;
        this.originalIDancer = dancer.originalIDancer;
    }

    public IDancer getOriginalIDancer() {
        return originalIDancer;
    }

    public void setOriginalIDancer(IDancer originalIDancer) {
        this.originalIDancer = originalIDancer;
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
        try {
            setID(replacement.getID());

        } catch (Exception e) {
            setID(0);
        }

        try {
            setMale(replacement.isMale());

        } catch (Exception e) {
            setMale(false);
        }

        try {
            setHeight(replacement.getHeight());

        } catch (Exception e) {
            setHeight(0);
        }
        setLeft(this.left);
        setRight(this.right);
        setParent(this.parent);
        setOriginalIDancer(replacement.originalIDancer);
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
