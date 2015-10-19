package dancers;

import newTree.AVLTree;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Dancers implements IDancers {

    public Tree avlTree = new Tree();
    //public AVLTree avlTree = new AVLTree();

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        if (searcher == null) {
            throw new IllegalArgumentException();
        }
        MyDancerNode myDancerNode = new MyDancerNode(searcher.getID(), searcher.getHeight(), searcher.isMale());
        myDancerNode.setOriginalIDancer(searcher);

        if (searcher.isMale()) {
            MyDancerNode woman = avlTree.searchAndRemoveWomen(myDancerNode.getHeight());
            if (woman == null) {
                avlTree.insert(myDancerNode);
            } else {

                return new SimpleEntry<>(myDancerNode, woman.getOriginalIDancer());
            }

        } else {
            MyDancerNode male = avlTree.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTree.insert(myDancerNode);
            } else {

                return new SimpleEntry<>(myDancerNode, male.getOriginalIDancer());
            }
        }

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        return avlTree.inorder();
    }
}