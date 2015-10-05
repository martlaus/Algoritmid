package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
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
                if (temp != null) {
                    return new SimpleEntry<>(temp, woman.getOriginalIDancer());

                } else {
                    return new SimpleEntry<>(searcher, woman.getOriginalIDancer());
                }
            }

        } else {
            Dancer male = avlTree.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTree.insert(d);

            } else {
                IDancer temp = ((Dancer) searcher).getOriginalIDancer();
                if (temp != null) {
                    return new SimpleEntry<>(temp, male.getOriginalIDancer());

                } else {
                    return new SimpleEntry<>(searcher, male.getOriginalIDancer());

                }
            }
        }

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        List<IDancer> list = (List<IDancer>) (List<?>) avlTree.inorder();
        System.out.println("PRESORTED :");

        for (IDancer d : list) {
            System.out.println(d.toString());
        }
        Collections.sort(list, new MyComparator()); //saab ära v6tta vb
        System.out.println("SORTED :");
        for (IDancer d : list) {
            System.out.println(d.toString());
        }
        return list;

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