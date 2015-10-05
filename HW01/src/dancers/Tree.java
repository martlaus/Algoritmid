package dancers;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the complete and tested implementation of an AVL-tree.
 */
public class Tree {

    protected Dancer root; // the root node
    protected int diff;
    protected Dancer res;
    Dancer deletedElementRoot = null;

    public void insert(Dancer d) {
        // create new node
        // start recursive procedure for inserting the node
        insertAVL(this.root, d);
    }

    /**
     * Recursive method to insert a node into a tree.
     *
     * @param root      The node currently compared, usually you start with the root.
     * @param newDancer The node to be inserted.
     */
    public void insertAVL(Dancer root, Dancer newDancer) {
        // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
        if (root == null) {
            this.root = newDancer;
        } else {

            // If compare node is smaller, continue with the left node
            if (newDancer.height < root.height) {
                if (root.left == null) {
                    root.left = newDancer;
                    newDancer.parent = root;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(root);
                } else {

                    insertAVL(root.left, newDancer);
                }

                //if new node is greater add to right
            } else if (newDancer.height > root.height) {
                if (root.right == null) {
                    root.right = newDancer;
                    newDancer.parent = root;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(root);
                } else {
                    insertAVL(root.right, newDancer);
                }

                //if they are equal, the male should be first
            } else if (newDancer.height == root.height) {
                //change male to first
                if (!root.isMale() && newDancer.isMale()) {
                    Dancer temp = new Dancer();
                    temp.replace(root);
                    temp.setEqualHeightDancers(root.getEqualHeightDancers());
                    root.replace(newDancer);

                    List<Dancer> dancers = temp.getEqualHeightDancers();
                    if (dancers == null) dancers = new ArrayList<>();
                    temp.setInList(true);
                    dancers.add(temp);
                    root.setEqualHeightDancers(dancers);
                } else {
                    List<Dancer> dancers = root.getEqualHeightDancers();
                    if (dancers == null) dancers = new ArrayList<>();
                    newDancer.setInList(true);
                    dancers.add(newDancer);
                    root.setEqualHeightDancers(dancers);
                }

            }
        }
    }

    /**
     * Check the balance for each node recursivly and call required methods for balancing the tree until the root is reached.
     *
     * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
     */
    public void recursiveBalance(Dancer cur) {

        // we do not use the balance in this class, but the store it anyway
        setBalance(cur);
        int balance = cur.balance;

        // check the balance
        if (balance == -2) {

            if (height(cur.left.left) >= height(cur.left.right)) {
                cur = rotateRight(cur);
            } else {
                cur = doubleRotateLeftRight(cur);
            }
        } else if (balance == 2) {
            if (height(cur.right.right) >= height(cur.right.left)) {
                cur = rotateLeft(cur);
            } else {
                cur = doubleRotateRightLeft(cur);
            }
        }

        // we did not reach the root yet
        if (cur.parent != null) {
            recursiveBalance(cur.parent);
        } else {
            System.out.println(cur);
            this.root = cur;
            System.out.println("------------ Balancing finished " + root.isMale() + " " + root.getHeight() + " ----------------");
        }
    }

    /**
     * Removes a node from the tree, if it is existent.
     */
    public void remove(int k) {
        // First we must find the node, after this we can delete it.
        removeAVL(this.root, k);
    }

    /**
     * Finds a node and calls a method to remove the node.
     *
     * @param p The node to start the search.
     * @param q The KEY of node to remove.
     */
    public void removeAVL(Dancer p, int q) {
        if (p == null) {
            return;
        } else {
            if (p.height > q) {
                removeAVL(p.left, q);
            } else if (p.height < q) {
                removeAVL(p.right, q);
            } else if (p.height == q) {
                // we found the node in the tree.. now lets go on!
                removeFoundNode(p);
            }
        }
    }

    public Dancer searchAndRemoveWomen(int height) {
        diff = Integer.MAX_VALUE;
        res = null;
        Dancer back = searchAndRemoveWomen(this.root, height);
        if (deletedElementRoot != null) {
            deletedElementRoot.getEqualHeightDancers().remove(back);
        }
        if (res != null) {
            //pane lapsed listist tagasi kustutamisel
            Dancer node = new Dancer();
            List<Dancer> dancers = res.getEqualHeightDancers();
            if (dancers != null) {
                for (Dancer d : dancers) {
                    if (d.isMale()) {
                        node.replace(d);
                        dancers.remove(d);
                        break;
                    }


                    if (node.getHeight() == 0) {
                        Dancer dd = dancers.get(0);
                        node.replace(dd);
                        dancers.remove(dd);
                    }
                    List<Dancer> dancers1 = new ArrayList<>();
                    for (Dancer dancer : dancers) {
                        dancers1.add(dancer);
                    }
                    node.setEqualHeightDancers(dancers1);
                    removeFoundNode(res);
                    insert(node);
                }


            } else {
                removeFoundNode(res);
            }
        }
        return back;
    }

    public Dancer searchAndRemoveWomen(Dancer dancer, int maleHeight) {
        if (dancer == null) {
            return res;
        }

        //find tallest woman, that is shorter than man from left
        if (dancer.getHeight() >= maleHeight) {
            return searchAndRemoveWomen(dancer.left, maleHeight);

            //find tallest woman, that is shorter than man from right
        } else if (dancer.getHeight() < maleHeight) {
            if (dancer.isMale()) {
                System.out.println("vaata tema listist naisi");
                try {
                    List<Dancer> dancers = dancer.getEqualHeightDancers();
                    for (Dancer d : dancers) {
                        int newDiff = Math.abs(d.getHeight() - maleHeight);
                        if (!d.isMale() && newDiff < diff && newDiff != 0) {
                            deletedElementRoot = dancer;
                            res = d;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                    //empty
                }


            } else {
                int newDiff = Math.abs(dancer.getHeight() - maleHeight);
                if (newDiff < diff && newDiff != 0) {
                    res = dancer;
                    diff = newDiff;
                }
            }
            return searchAndRemoveWomen(dancer.left, maleHeight);
        }

        return res;
    }

    public Dancer searchAndRemoveMen(int height) {
        res = null;
        diff = Integer.MAX_VALUE;
        Dancer back = searchAndRemoveMen(this.root, height);
        if (deletedElementRoot != null) {
            deletedElementRoot.getEqualHeightDancers().remove(back);
        }
        if (res != null) {
            //pane lapsed listist tagasi kustutamisel
            Dancer node = new Dancer();
            List<Dancer> dancers = res.getEqualHeightDancers();

            removeFoundNode(res);


            if (dancers != null) {
                for (int i = 0; i < dancers.size(); i++) {
                    Dancer d = dancers.get(i);
                    if (d.isMale()) {
                        node.replace(d);
                        dancers.remove(d);
                        break;
                    }

                    if (node.getHeight() == 0) {
                        Dancer dd = dancers.get(0);
                        node.replace(dd);
                        dancers.remove(dd);
                    }
                    List<Dancer> dancers1 = new ArrayList<>();
                    for (Dancer dancer : dancers) {
                        dancers1.add(dancer);
                    }
                    node.setEqualHeightDancers(dancers1);
                    insert(node);
                }


            } else {
                removeFoundNode(res);

            }
        }

        return back;
    }

    public Dancer searchAndRemoveMen(Dancer dancer, int femaleHeight) {
        if (dancer == null) {
            return res;
        }

        //find shortest man, that is taller than woman
        if (dancer.getHeight() > femaleHeight) {
            if (!dancer.isMale()) {
                System.out.println("vaata tema listist mehi");
                try {
                    List<Dancer> dancers = dancer.getEqualHeightDancers();
                    for (Dancer d : dancers) {
                        int newDiff = Math.abs(d.getHeight() - femaleHeight);
                        if (d.isMale() && newDiff < diff && newDiff != 0) {
                            deletedElementRoot = dancer;
                            res = d;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                    //empty
                }

            } else {
                int newDiff = Math.abs(dancer.getHeight() - femaleHeight);
                if (newDiff < diff && newDiff != 0) {
                    res = dancer;
                    diff = newDiff;
                }
            }


            return searchAndRemoveMen(dancer.left, femaleHeight);

            //find shortest man, that is taller than woman
        } else if (dancer.getHeight() <= femaleHeight) {

            return searchAndRemoveMen(dancer.right, femaleHeight);

        }

        return res;


    }

    /**
     * Removes a node from a AVL-Tree, while balancing will be done if necessary.
     *
     * @param dancer The node to be removed.
     */
    public void removeFoundNode(Dancer dancer) {
        Dancer r;
        // at least one child of q, q will be removed directly
        if (dancer.left == null || dancer.right == null) {
            // the root is deleted
            if (dancer.parent == null) {
                if (!dancer.isInList()) {
                    this.root = null;
                }
                dancer = null;
                return;
            }
            r = dancer;
        } else {
            // q has two children --> will be replaced by successor
            r = successor(dancer);
            dancer.height = r.height;
        }

        Dancer p;
        if (r.left != null) {
            p = r.left;
        } else {
            p = r.right;
        }

        if (p != null) {
            p.parent = r.parent;
        }

        if (r.parent == null) {
            this.root = p;
        } else {
            if (r == r.parent.left) {
                r.parent.left = p;
            } else {
                r.parent.right = p;
            }
            // balancing must be done until the root is reached.
            recursiveBalance(r.parent);
        }
        r = null;
    }

    /**
     * Left rotation using the given node.
     *
     * @param n The node for the rotation.
     * @return The root of the rotated tree.
     */
    public Dancer rotateLeft(Dancer n) {

        Dancer v = n.right;
        v.parent = n.parent;

        n.right = v.left;

        if (n.right != null) {
            n.right.parent = n;
        }

        v.left = n;
        n.parent = v;

        if (v.parent != null) {
            if (v.parent.right == n) {
                v.parent.right = v;
            } else if (v.parent.left == n) {
                v.parent.left = v;
            }
        }

        setBalance(n);
        setBalance(v);

        return v;
    }

    /**
     * Right rotation using the given node.
     *
     * @param n The node for the rotation
     * @return The root of the new rotated tree.
     */
    public Dancer rotateRight(Dancer n) {

        Dancer v = n.left;
        v.parent = n.parent;

        n.left = v.right;

        if (n.left != null) {
            n.left.parent = n;
        }

        v.right = n;
        n.parent = v;


        if (v.parent != null) {
            if (v.parent.right == n) {
                v.parent.right = v;
            } else if (v.parent.left == n) {
                v.parent.left = v;
            }
        }

        setBalance(n);
        setBalance(v);

        return v;
    }

    /**
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    public Dancer doubleRotateLeftRight(Dancer u) {
        u.left = rotateLeft(u.left);
        return rotateRight(u);
    }

    /**
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    public Dancer doubleRotateRightLeft(Dancer u) {
        u.right = rotateRight(u.right);
        return rotateLeft(u);
    }

    /**
     * Returns the successor of a given node in the tree (search recursivly).
     *
     * @param q The predecessor.
     * @return The successor of node q.
     */
    public Dancer successor(Dancer q) {
        if (q.right != null) {
            Dancer r = q.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else {
            Dancer p = q.parent;
            while (p != null && q == p.right) {
                q = p;
                p = q.parent;
            }
            return p;
        }
    }

    /**
     * Calculating the "height" of a node.
     *
     * @param cur
     * @return The height of a node (-1, if node is not existent eg. NULL).
     */
    private int height(Dancer cur) {
        if (cur == null) {
            return -1;
        }
        if (cur.left == null && cur.right == null) {
            return 0;
        } else if (cur.left == null) {
            return 1 + height(cur.right);
        } else if (cur.right == null) {
            return 1 + height(cur.left);
        } else {
            return 1 + maximum(height(cur.left), height(cur.right));
        }
    }

    /**
     * Return the maximum of two integers.
     */
    private int maximum(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Only for debugging purposes. Gives all information about a node.
     *
     * @param n The node to write information about.
     */
    public String debug(Dancer n) {
        int l = 0;
        int r = 0;
        int p = 0;
        if (n == null) return "";
        if (n.left != null) {
            l = n.left.height;
        }
        if (n.right != null) {
            r = n.right.height;
        }
        if (n.parent != null) {
            p = n.parent.height;
        }
        String sex;
        if (n.isMale()) {
            sex = "male";
        } else {
            sex = "female";
        }
        String res = sex + " Left: " + l + " Key: " + n.height + " Right: " + r + " Parent: " + p + " Balance: " + n.balance + "\n";

        if (n.left != null) {
            debug(n.left);
        }
        if (n.right != null) {
            debug(n.right);
        }
        return res;
    }

    private void setBalance(Dancer cur) {
        cur.balance = height(cur.right) - height(cur.left);
    }

    /**
     * Calculates the Inorder traversal of this tree.
     *
     * @return A Array-List of the tree in inorder traversal.
     */
    final protected List<Dancer> inorder() {
        ArrayList<Dancer> ret = new ArrayList<Dancer>();
        inorder(root, ret);
        return ret;
    }

    /**
     * Function to calculate inorder recursivly.
     *
     * @param n  The current node.
     * @param io The list to save the inorder traversal.
     */
    final protected void inorder(Dancer n, ArrayList<Dancer> io) {
        if (n == null) {
            return;
        }
        inorder(n.left, io);
        io.add(n);

        try {
            List<Dancer> dancers = n.getEqualHeightDancers();
            if (dancers.size() > 0) {
                for (Dancer d : dancers) {
                    io.add(d);
                }
            }

        } catch (Exception e) {
            //no left element
        }

        inorder(n.right, io);
    }
}

