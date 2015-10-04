package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;

public class Dancers implements IDancers {


    Tree avlTree = new Tree();


    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        if (searcher.isMale()) {
            Dancer woman = avlTree.searchAndRemoveWomen(searcher.getHeight());
            if (woman == null) {
                Dancer d = (Dancer) searcher;
                d.key = d.key + 10000;
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
        Collections.sort(list, new MyComparator());
        return list;

    }

}