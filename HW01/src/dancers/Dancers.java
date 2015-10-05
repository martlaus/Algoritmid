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
        if (searcher.isMale()) {
            Dancer d = (Dancer) searcher;

            Dancer woman = avlTree.searchAndRemoveWomen(d.getHeight());
            if (woman == null) {

                avlTree.insert(d);

            } else {
                return new SimpleEntry(searcher, woman);
            }

        } else {
            Dancer male = avlTree.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTree.insert((Dancer) searcher);

            } else {
                return new SimpleEntry(searcher, male);
            }
        }

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        List<IDancer> list = (List<IDancer>) (List<?>) avlTree.inorder();
//        for (IDancer d : list) {
//            System.out.println(d.toString());
//        }
//        Collections.sort(list, new MyComparator()); //saab ära v6tta vb
//        System.out.println("SORTED :");
//        for (IDancer d : list) {
//            System.out.println(d.toString());
//        }
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