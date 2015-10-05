package dancers;

import java.util.ArrayList;
import java.util.List;


public class Tree {

    protected MyDancerNode root; // the root node
    protected int diff;
    protected MyDancerNode res;
    MyDancerNode deletedElementRoot = null;

    public void insert(MyDancerNode d) {
        // create new node
        // start recursive procedure for inserting the node
        insertAVL(this.root, d);
    }

    /**
     * Recursive method to insert a node into a tree.
     *
     * @param root      The node currently compared, usually you start with the root.
     * @param newMyDancerNode The node to be inserted.
     */
    public void insertAVL(MyDancerNode root, MyDancerNode newMyDancerNode) {
        // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
        if (root == null) {
            this.root = newMyDancerNode;
        } else {

            // If compare node is smaller, continue with the left node
            if (newMyDancerNode.height < root.height) {
                if (root.left == null) {
                    root.left = newMyDancerNode;
                    newMyDancerNode.parent = root;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(root);
                } else {

                    insertAVL(root.left, newMyDancerNode);
                }

                //if new node is greater add to right
            } else if (newMyDancerNode.height > root.height) {
                if (root.right == null) {
                    root.right = newMyDancerNode;
                    newMyDancerNode.parent = root;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(root);
                } else {
                    insertAVL(root.right, newMyDancerNode);
                }

                //if they are equal, the male should be first
            } else if (newMyDancerNode.height == root.height) {
                //change male to first
                if (!root.isMale() && newMyDancerNode.isMale()) {
                    MyDancerNode temp = new MyDancerNode();
                    temp.replace(root);
                    temp.setEqualHeightMyDancerNodes(root.getEqualHeightMyDancerNodes());
                    root.replace(newMyDancerNode);

                    List<MyDancerNode> myDancerNodes = temp.getEqualHeightMyDancerNodes();
                    if (myDancerNodes == null) myDancerNodes = new ArrayList<>();
                    temp.setInList(true);
                    myDancerNodes.add(temp);
                    root.setEqualHeightMyDancerNodes(myDancerNodes);
                } else {
                    List<MyDancerNode> myDancerNodes = root.getEqualHeightMyDancerNodes();
                    if (myDancerNodes == null) myDancerNodes = new ArrayList<>();
                    newMyDancerNode.setInList(true);
                    myDancerNodes.add(newMyDancerNode);
                    root.setEqualHeightMyDancerNodes(myDancerNodes);
                }

            }
        }
    }

    /**
     * Check the balance for each node recursivly and call required methods for balancing the tree until the root is reached.
     *
     * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
     */
    public void recursiveBalance(MyDancerNode cur) {

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
            //System.out.println(cur);
            this.root = cur;
            // System.out.println("------------ Balancing finished " + root.isMale() + " " + root.getHeight() + " ----------------");
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
    public void removeAVL(MyDancerNode p, int q) {
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

    public MyDancerNode searchAndRemoveWomen(int height) {
        MyDancerNode copydResult;
        diff = Integer.MAX_VALUE;
        res = null;
        MyDancerNode back = searchAndRemoveWomen(this.root, height);
        if (back != null) {
            copydResult = new MyDancerNode(back);

        } else {
            copydResult = back;
        }
        if (deletedElementRoot != null) {
            deletedElementRoot.getEqualHeightMyDancerNodes().remove(back);
        }
        if (res != null) {
            //pane lapsed listist tagasi kustutamisel
            MyDancerNode node = new MyDancerNode();
            List<MyDancerNode> myDancerNodes = res.getEqualHeightMyDancerNodes();
            if (myDancerNodes != null) {
                for (MyDancerNode d : myDancerNodes) {
                    if (d.isMale()) {
                        node.replace(d);
                        myDancerNodes.remove(d);
                        break;
                    }


                    if (node.getHeight() == 0) {
                        MyDancerNode dd = myDancerNodes.get(0);
                        node.replace(dd);
                        myDancerNodes.remove(dd);
                    }
                    List<MyDancerNode> dancers1 = new ArrayList<>();
                    for (MyDancerNode myDancerNode : myDancerNodes) {
                        dancers1.add(myDancerNode);
                    }
                    node.setEqualHeightMyDancerNodes(dancers1);
                    removeFoundNode(res);
                    insert(node);
                }


            } else {
                removeFoundNode(res);
            }
        }
        return copydResult;
    }

    public MyDancerNode searchAndRemoveWomen(MyDancerNode myDancerNode, int maleHeight) {
        if (myDancerNode == null) {
            return res;
        }

        //find tallest woman, that is shorter than man from left
        if (myDancerNode.getHeight() >= maleHeight) {
            return searchAndRemoveWomen(myDancerNode.left, maleHeight);

            //find tallest woman, that is shorter than man from right
        } else if (myDancerNode.getHeight() < maleHeight) {
            if (myDancerNode.isMale()) {
                //System.out.println("vaata tema listist naisi");
                try {
                    List<MyDancerNode> myDancerNodes = myDancerNode.getEqualHeightMyDancerNodes();
                    for (MyDancerNode d : myDancerNodes) {
                        int newDiff = Math.abs(d.getHeight() - maleHeight);
                        if (!d.isMale() && newDiff < diff && newDiff != 0) {
                            deletedElementRoot = myDancerNode;
                            res = d;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                    MyDancerNode temp = searchAndRemoveWomen(myDancerNode.right, maleHeight);
                    if (temp == null) {
                        return searchAndRemoveWomen(myDancerNode.left, maleHeight);
                    } else {
                        return temp;
                    }

                }


            } else {
                int newDiff = Math.abs(myDancerNode.getHeight() - maleHeight);
                if (newDiff < diff && newDiff != 0) {
                    res = myDancerNode;
                    diff = newDiff;
                }
            }
            return searchAndRemoveWomen(myDancerNode.right, maleHeight);
        }

        return res;
    }

    public MyDancerNode searchAndRemoveMen(int height) {
        MyDancerNode copydResult;
        res = null;
        diff = Integer.MAX_VALUE;
        MyDancerNode back = searchAndRemoveMen(this.root, height);
        if (back != null) {
            copydResult = new MyDancerNode(back);

        } else {
            copydResult = back;
        }
        if (deletedElementRoot != null) {
            deletedElementRoot.getEqualHeightMyDancerNodes().remove(back);
        }
        if (res != null) {
            //pane lapsed listist tagasi kustutamisel
            MyDancerNode node = new MyDancerNode();
            List<MyDancerNode> myDancerNodes = res.getEqualHeightMyDancerNodes();

            if (myDancerNodes != null) {
                removeFoundNode(res);
                for (int i = 0; i < myDancerNodes.size(); i++) {
                    MyDancerNode d = myDancerNodes.get(i);
                    if (d.isMale()) {
                        node.replace(d);
                        myDancerNodes.remove(d);
                        break;
                    }

                    if (node.getHeight() == 0) {
                        MyDancerNode dd = myDancerNodes.get(0);
                        node.replace(dd);
                        myDancerNodes.remove(dd);
                    }
                    List<MyDancerNode> dancers1 = new ArrayList<>();
                    for (MyDancerNode myDancerNode : myDancerNodes) {
                        dancers1.add(myDancerNode);
                    }
                    node.setEqualHeightMyDancerNodes(dancers1);
                    insert(node);
                }


            } else {
                removeFoundNode(res);

            }
        }

        return copydResult;
    }

    public MyDancerNode searchAndRemoveMen(MyDancerNode myDancerNode, int femaleHeight) {
        if (myDancerNode == null) {
            return res;
        }

        //find shortest man, that is taller than woman
        if (myDancerNode.getHeight() > femaleHeight) {
            if (!myDancerNode.isMale()) {
                //System.out.println("vaata tema listist mehi");
                try {
                    List<MyDancerNode> myDancerNodes = myDancerNode.getEqualHeightMyDancerNodes();
                    for (MyDancerNode d : myDancerNodes) {
                        int newDiff = Math.abs(d.getHeight() - femaleHeight);
                        if (d.isMale() && newDiff < diff && newDiff != 0) {
                            deletedElementRoot = myDancerNode;
                            res = d;
                            diff = newDiff;
                        }
                    }
                } catch (Exception e) {
                    //return searchAndRemoveWomen(myDancerNode.right, femaleHeight); //?? meibi
                }

            } else {
                int newDiff = Math.abs(myDancerNode.getHeight() - femaleHeight);
                if (newDiff < diff && newDiff != 0) {
                    res = myDancerNode;
                    diff = newDiff;
                }
            }


            return searchAndRemoveMen(myDancerNode.left, femaleHeight);

            //find shortest man, that is taller than woman
        } else if (myDancerNode.getHeight() <= femaleHeight) {

            return searchAndRemoveMen(myDancerNode.right, femaleHeight);

        }

        return res;

    }

    /**
     * Removes a node from a AVL-Tree, while balancing will be done if necessary.
     *
     * @param myDancerNode The node to be removed.
     */
    public void removeFoundNode(MyDancerNode myDancerNode) {
        MyDancerNode lastElement;
        // at least one child of q, q will be removed directly
        if (myDancerNode.left == null || myDancerNode.right == null) {
            // the root is deleted
            if (myDancerNode.parent == null) {
                if (!myDancerNode.isInList()) {
                    if (myDancerNode.left != null) {
                        this.root = myDancerNode.left;
                    } else if (myDancerNode.right != null) {
                        this.root = myDancerNode.right;
                    } else {
                        this.root = null;

                    }
                }
                myDancerNode = null;
                return;
            }
            lastElement = myDancerNode;
        } else {
            // q has two children --> will be replaced by successor
            lastElement = successor(myDancerNode);
            myDancerNode.height = lastElement.height;
        }

        MyDancerNode p;
        if (lastElement.left != null) {
            p = lastElement.left;
        } else {
            p = lastElement.right;
        }

        if (p != null) {
            p.parent = lastElement.parent;
        }

        if (lastElement.parent == null) {
            this.root = p;
        } else {
            if (lastElement == lastElement.parent.left) {
                lastElement.parent.left = p;
            } else {
                lastElement.parent.right = p;
            }
            // balancing must be done until the root is reached.
            recursiveBalance(lastElement.parent);
        }
        lastElement = null;
    }

    /**
     * Left rotation using the given node.
     *
     * @param n The node for the rotation.
     * @return The root of the rotated tree.
     */
    public MyDancerNode rotateLeft(MyDancerNode n) {

        MyDancerNode v = n.right;
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
    public MyDancerNode rotateRight(MyDancerNode n) {

        MyDancerNode v = n.left;
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
    public MyDancerNode doubleRotateLeftRight(MyDancerNode u) {
        u.left = rotateLeft(u.left);
        return rotateRight(u);
    }

    /**
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    public MyDancerNode doubleRotateRightLeft(MyDancerNode u) {
        u.right = rotateRight(u.right);
        return rotateLeft(u);
    }

    /**
     * Returns the successor of a given node in the tree (search recursivly).
     *
     * @param q The predecessor.
     * @return The successor of node q.
     */
    public MyDancerNode successor(MyDancerNode q) {
        if (q.right != null) {
            MyDancerNode r = q.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else {
            MyDancerNode p = q.parent;
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
    private int height(MyDancerNode cur) {
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
    public String debug(MyDancerNode n) {
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

    private void setBalance(MyDancerNode cur) {
        cur.balance = height(cur.right) - height(cur.left);
    }

    /**
     * Calculates the Inorder traversal of this tree.
     *
     * @return A Array-List of the tree in inorder traversal.
     */
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
    final protected void inorder(MyDancerNode n, ArrayList<IDancer> io) {
        if (n == null) {
            return;
        }
        inorder(n.left, io);
        io.add(n.getOriginalIDancer());

        try {
            List<MyDancerNode> myDancerNodes = n.getEqualHeightMyDancerNodes();
            if (myDancerNodes.size() > 0) {
                for (MyDancerNode d : myDancerNodes) {
                    io.add(d.getOriginalIDancer());
                }
            }

        } catch (Exception e) {
            //no left element
        }

        inorder(n.right, io);
    }
}

