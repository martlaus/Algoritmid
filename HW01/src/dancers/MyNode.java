package dancers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 18.10.15.
 */
public class MyNode {

    private int key;
    private int balance;
    private MyNode left, right, parent;
    private IDancer originalIDancer;
    private List<MyNode> equalNodes;

    public MyNode(IDancer dancer, MyNode parent) {
        this.key = dancer.getHeight();
        this.parent = parent;
        this.originalIDancer = dancer;
        this.equalNodes = new ArrayList<>();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public IDancer getOriginalIDancer() {
        return originalIDancer;
    }

    public void setOriginalIDancer(IDancer originalIDancer) {
        this.key = originalIDancer.getHeight();
        this.originalIDancer = originalIDancer;
    }

    public MyNode getLeft() {
        return left;
    }

    public void setLeft(MyNode left) {
        this.left = left;
    }

    public MyNode getRight() {
        return right;
    }

    public void setRight(MyNode right) {
        this.right = right;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public MyNode getParent() {
        return parent;
    }

    public void setParent(MyNode parent) {
        this.parent = parent;
    }

    public List<MyNode> getEqualNodes() {
        return equalNodes;
    }

    public void setEqualNodes(List<MyNode> equalNodes) {
        this.equalNodes = equalNodes;
    }

    public void addEqualNode(MyNode equalNode) {
        equalNodes.add(equalNode);
    }


    public void removeEqualNode(MyNode equalNode) {
        equalNodes.remove(equalNode);
    }
}
