package dancers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 18.10.15.
 */
public class AVLTree {

    public boolean deletedAlready = false;
    private MyNode root;
    private boolean foundEqual = false;
    private MyNode equalNode = null;

    public MyNode insert(IDancer searcher) {
        MyNode foundPartner = null;

        if (root == null)
            root = new MyNode(searcher, null);
        else {
            MyNode currentNode = root;
            MyNode parent;
            while (true) {
                if (currentNode.getKey() == searcher.getHeight()) {
                    //System.out.println("EQUAL NODE ADDED");
                    equalNode = currentNode;
                    foundEqual = true;
                }

                parent = currentNode;
                boolean goLeft;
                if (searcher.isMale()) {
                    goLeft = currentNode.getKey() >= searcher.getHeight();

                } else {
                    goLeft = currentNode.getKey() > searcher.getHeight();

                }

                //male/female check
                IDancer currentDancer = currentNode.getOriginalIDancer();
                if (searcher.isMale() && (searcher.getHeight() > currentDancer.getHeight())) { //man searcing
                    if (foundPartner == null) {
                        foundPartner = getPartnerFirstTime(searcher, currentNode, currentDancer);
                    } else if (currentDancer.getHeight() > foundPartner.getOriginalIDancer().getHeight()) {
                        foundPartner = getPartnerSecond(searcher, foundPartner, currentNode, currentDancer);
                    }

                } else if (!searcher.isMale() && (searcher.getHeight() < currentDancer.getHeight())) { //woman searching
                    if (foundPartner == null) {
                        foundPartner = getPartnerFirstTime(searcher, currentNode, currentDancer);
                    } else if (currentDancer.getHeight() < foundPartner.getOriginalIDancer().getHeight()) {
                        foundPartner = getPartnerSecond(searcher, foundPartner, currentNode, currentDancer);
                    }

                }

                //male/female check

                currentNode = goLeft ? currentNode.getLeft() : currentNode.getRight();

                if (currentNode == null && foundPartner == null) {
                    if (goLeft) {
                        parent.setLeft(new MyNode(searcher, parent));
                    } else {
                        parent.setRight(new MyNode(searcher, parent));
                    }
                    rebalance(parent);
                    break;
                } else if (currentNode == null) {
                    break;
                }
            }
            if (foundEqual) {
                equalNode.addEqualNode(new MyNode(searcher, equalNode.getParent()));
                foundEqual = false;
            }
        }

        return foundPartner;
    }

    private MyNode getPartnerSecond(IDancer searcher, MyNode foundPartner, MyNode currentNode, IDancer currentDancer) {
        if (currentNode.getEqualNodes().size() == 0) {
            if (currentNode.getOriginalIDancer().isMale() != searcher.isMale()) {
                foundPartner.setOriginalIDancer(currentDancer);
            }

        } else if (currentNode.getOriginalIDancer().isMale() != searcher.isMale()) {//right guy, but has equals
            foundPartner.setOriginalIDancer(currentDancer);

        } else {
            for (MyNode myNode : currentNode.getEqualNodes()) { //loop list
                if (myNode.getOriginalIDancer().isMale() != searcher.isMale()) {
                    foundPartner.setOriginalIDancer(myNode.getOriginalIDancer());
                }
            }

        }

        return foundPartner;
    }

    private MyNode getPartnerFirstTime(IDancer searcher, MyNode currentNode, IDancer currentDancer) {
        MyNode foundPartner = null;
        if (currentNode.getEqualNodes().size() == 0) {
            if (currentNode.getOriginalIDancer().isMale() != searcher.isMale()) {
                foundPartner = new MyNode(currentDancer, null);
            }
        } else if (currentNode.getOriginalIDancer().isMale() != searcher.isMale()) {//right guy, but has equals
            foundPartner = new MyNode(currentDancer, null);

        } else {
            for (MyNode myNode : currentNode.getEqualNodes()) { //loop list
                if (myNode.getOriginalIDancer().isMale() != searcher.isMale()) {
                    foundPartner = new MyNode(myNode.getOriginalIDancer(), null);
                }
            }
        }
        return foundPartner;
    }

    public void delete(MyNode nodeToDelete) {
        if (root == null)
            return;
        MyNode n = root;
        MyNode parent = root;
        MyNode delMyNode = null;
        MyNode child = root;

        while (child != null) {
            parent = n;
            n = child;
            child = nodeToDelete.getKey() >= n.getKey() ? n.getRight() : n.getLeft();
            if (nodeToDelete.getKey() == n.getKey()) {
                if (n.getEqualNodes().size() == 0 && nodeToDelete.getOriginalIDancer().isMale() == n.getOriginalIDancer().isMale()) { //simple case, no list, correct gender
                    delMyNode = n;

                }

                if (n.getEqualNodes().size() > 0 && nodeToDelete.getOriginalIDancer().isMale() != n.getOriginalIDancer().isMale()) { //loop list of equals

                    //find correct gender partner from n
                    for (MyNode myNode : n.getEqualNodes()) {
                        if (myNode.getOriginalIDancer().isMale() == nodeToDelete.getOriginalIDancer().isMale()) { //delete correct gender dancer
                            n.removeEqualNode(nodeToDelete);
                            deletedAlready = true;
                        }
                    }
                } else if (n.getEqualNodes().size() > 0 && nodeToDelete.getOriginalIDancer().isMale() == n.getOriginalIDancer().isMale()) { //need to delete guy with list of equals and set a list member as new listowner
                    List<MyNode> myNodes = n.getEqualNodes();
                    n.setOriginalIDancer(myNodes.get(0).getOriginalIDancer());
                    myNodes.remove(0);
                    n.setEqualNodes(myNodes);
                    deletedAlready = true;
                }

            }
        }

        if (delMyNode != null && !deletedAlready) {
            delMyNode.setKey(n.getKey());
            delMyNode.setEqualNodes(n.getEqualNodes());
            delMyNode.setOriginalIDancer(n.getOriginalIDancer());

            child = n.getLeft() != null ? n.getLeft() : n.getRight();

            if (root.getKey() == nodeToDelete.getKey()) {
                root = child;
            } else {
                if (parent.getLeft() == n) { //komparaator?
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
                rebalance(parent);
            }
        }
    }

    private void rebalance(MyNode n) {
        setBalance(n);

        if (n.getBalance() == -2) {
            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight()))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.getBalance() == 2) {
            if (height(n.getRight().getRight()) >= height(n.getRight().getLeft()))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.getParent() != null) {
            rebalance(n.getParent());
        } else {
            root = n;
        }
    }

    private MyNode rotateLeft(MyNode a) {

        MyNode b = a.getRight();
        b.setParent(a.getParent());

        a.setRight(b.getLeft());

        if (a.getRight() != null)
            a.getRight().setParent(a);

        b.setLeft(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private MyNode rotateRight(MyNode a) {

        MyNode b = a.getLeft();
        b.setParent(a.getParent());

        a.setLeft(b.getRight());

        if (a.getLeft() != null)
            a.getLeft().setParent(a);

        b.setRight(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private MyNode rotateLeftThenRight(MyNode n) {
        n.setLeft(rotateLeft(n.getLeft()));
        return rotateRight(n);
    }

    private MyNode rotateRightThenLeft(MyNode n) {
        n.setRight(rotateRight(n.getRight()));
        return rotateLeft(n);
    }

    private int height(MyNode n) {
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }

    private void setBalance(MyNode... nodes) {
        for (MyNode n : nodes)
            n.setBalance(height(n.getRight()) - height(n.getLeft()));
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(MyNode n) {
        if (n != null) {
            printBalance(n.getLeft());
            System.out.printf("%s ", n.getBalance());
            printBalance(n.getRight());
        }
    }


    final protected List<IDancer> inorder() {
        ArrayList<IDancer> ret = new ArrayList<>();
        inorder(root, ret);
        return ret;
    }

    /**
     * Function to calculate inorder recursivly.
     *
     * @param n  The current node.
     * @param io The list to save the inorder traversal.
     */
    final protected void inorder(MyNode n, ArrayList<IDancer> io) {
        if (n == null) {
            return;
        }
        inorder(n.getLeft(), io);
        io.add(n.getOriginalIDancer());

        try {
            List<MyNode> myDancerNodes = n.getEqualNodes();
            if (myDancerNodes.size() > 0) {
                for (MyNode d : myDancerNodes) {
                    io.add(d.getOriginalIDancer());
                }
            }

        } catch (Exception e) {
            //no left element
        }


        inorder(n.getRight(), io);
    }
}
