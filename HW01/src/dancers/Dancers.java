package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Dancers implements IDancers {

    public Tree avlTree = new Tree();

    public Tree getAvlTree() {
        return avlTree;
    }

    public void addDancer(Dancer d) {
        avlTree.insert(d);
    }

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        Dancer d = (Dancer) searcher;
        d.setOriginalIDancer(searcher);

        if (searcher.isMale()) {
            Dancer woman = avlTree.searchAndRemoveWomen(d.getHeight());
            if (woman == null) {
                avlTree.insert(d);
            } else {
                IDancer temp = ((Dancer) searcher).getOriginalIDancer();

                return new SimpleEntry<>(temp, woman.getOriginalIDancer());
            }

        } else {
            Dancer male = avlTree.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTree.insert(d);
            } else {
                IDancer temp = ((Dancer) searcher).getOriginalIDancer();

                return new SimpleEntry<>(temp, male.getOriginalIDancer());
            }
        }

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        return (List<IDancer>) (List<?>) avlTree.inorder();

    }

    @Override
    public String toString() {
        String res = "\n";
        for (IDancer d : returnWaitingList()) {
            res += avlTree.debug((Dancer) d);
        }
        return res;
    }
}