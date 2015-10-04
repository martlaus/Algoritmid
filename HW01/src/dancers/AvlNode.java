package dancers;

/** Here is the AVL-Node class for Completeness **/
public class AvlNode {
    public AvlNode left;
    public AvlNode right;
    public AvlNode parent;
    public int key;
    public int balance;
    public Dancer dancer;

    public AvlNode(int k) {
        left = right = parent = null;
        balance = 0;
        key = k;
    }

    public Dancer getDancer() {
        return dancer;
    }

    public void setDancer(Dancer dancer) {
        this.dancer = dancer;
    }

    public String toString() {
        return "" + key;
    }

}
