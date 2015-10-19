package dancers;

import java.util.List;

/**
 * Created by mart on 2.10.15.
 */
public class MyDancerNode implements IDancer, Comparable<MyDancerNode> {
    public MyDancerNode left;
    public MyDancerNode right;
    public MyDancerNode parent;
    public int height;
    public int balance;
    public List<MyDancerNode> equalHeightMyDancerNodes;
    public IDancer originalIDancer;
    int ID = 0;
    boolean male;
    boolean inList = false;

    public MyDancerNode() {
        left = right = parent = null;
        balance = 0;
    }

    public MyDancerNode(int ID, int height, boolean male) {
        this.ID = ID;
        this.male = male;
        this.height = height;
        left = right = parent = null;
        balance = 0;
        this.inList = false;
    }

    public MyDancerNode(MyDancerNode myDancerNode) {
        this.left = myDancerNode.left;
        this.right = myDancerNode.right;
        this.parent = myDancerNode.parent;
        this.height = myDancerNode.getHeight();
        this.balance = myDancerNode.balance;
        this.ID = myDancerNode.ID;
        this.male = myDancerNode.male;
        this.inList = myDancerNode.inList;
        this.equalHeightMyDancerNodes = myDancerNode.equalHeightMyDancerNodes;
        this.originalIDancer = myDancerNode.originalIDancer;
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
    public int compareTo(MyDancerNode o) {
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

    public MyDancerNode replace(MyDancerNode replacement) {
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

    public void setLeft(MyDancerNode left) {
        this.left = left;
    }

    public void setRight(MyDancerNode right) {
        this.right = right;
    }

    public void setParent(MyDancerNode parent) {
        this.parent = parent;
    }

    public List<MyDancerNode> getEqualHeightMyDancerNodes() {
        return equalHeightMyDancerNodes;
    }

    public void setEqualHeightMyDancerNodes(List<MyDancerNode> equalHeightMyDancerNodes) {
        this.equalHeightMyDancerNodes = equalHeightMyDancerNodes;
    }


//    @Override
//    public String toString() {
//        return "{ " + this.getID() + " " + this.isMale() + " " + this.getHeight() + " }";
//    }
}
