package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Dancers implements IDancers {

    //   public Tree avlTree = new Tree();

//    public Tree getAvlTree() {
//        return avlTree;
//    }

    //    public void addDancer(MyDancerNode d) {
//        avlTree.insert(d);
//    }
    public AVLTree avlTree = new AVLTree();

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        if (searcher == null) {
            throw new IllegalArgumentException();
        }

        MyNode partner = avlTree.insert(searcher);
        SimpleEntry res = null;
        if (partner != null) {
            res = new SimpleEntry<>(searcher, partner.getOriginalIDancer());
            avlTree.delete(partner);
        }
        return res;


//        MyDancerNode myDancerNode = new MyDancerNode(searcher.getID(), searcher.getHeight(), searcher.isMale());
//        myDancerNode.setOriginalIDancer(searcher);
//
//        if (searcher.isMale()) {
//            MyDancerNode woman = avlTree.searchAndRemoveWomen(myDancerNode.getHeight());
//            if (woman == null) {
//                avlTree.insert(myDancerNode);
//            } else {
//
//                return new SimpleEntry<>(myDancerNode, woman.getOriginalIDancer());
//            }
//
//        } else {
//            MyDancerNode male = avlTree.searchAndRemoveMen(searcher.getHeight());
//            if (male == null) {
//
//                avlTree.insert(myDancerNode);
//            } else {
//
//                return new SimpleEntry<>(myDancerNode, male.getOriginalIDancer());
//            }
//        }
//
//        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        return avlTree.inorder();
    }

//    @Override
//    public String toString() {
//        String res = "\n";
//        for (IDancer d : returnWaitingList()) {
//            res += avlTree.debug((MyDancerNode) d);
//        }
//        return res;
//    }
}