package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Dancers implements IDancers {

    public Tree avlTree = new Tree();

    public Tree getAvlTree() {
        return avlTree;
    }

    public void addDancer(MyDancerNode d) {
        avlTree.insert(d);
    }

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        if (searcher == null) {
            throw new IllegalArgumentException();
        }
        MyDancerNode d = new MyDancerNode(searcher.getID(), searcher.getHeight(), searcher.isMale());
        d.setOriginalIDancer(searcher);

        if (searcher.isMale()) {
            MyDancerNode woman = avlTree.searchAndRemoveWomen(d.getHeight());
            if (woman == null) {
                avlTree.insert(d);
            } else {
                IDancer temp = ((MyDancerNode) searcher).getOriginalIDancer();

                return new SimpleEntry<>(temp, woman.getOriginalIDancer());
            }

        } else {
            MyDancerNode male = avlTree.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTree.insert(d);
            } else {
                IDancer temp = ((MyDancerNode) searcher).getOriginalIDancer();

                return new SimpleEntry<>(temp, male.getOriginalIDancer());
            }
        }

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        return avlTree.inorder();

    }

    @Override
    public String toString() {
        String res = "\n";
        for (IDancer d : returnWaitingList()) {
            res += avlTree.debug((MyDancerNode) d);
        }
        return res;
    }
}