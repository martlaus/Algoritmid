package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dancers implements IDancers {


    Tree avlTreeMen = new Tree();
    Tree avlTreeWomen = new Tree();


    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher) {
        if (searcher.isMale()) {
            Dancer woman = avlTreeWomen.searchAndRemoveWomen(searcher.getHeight());
            if (woman == null) {
                avlTreeMen.insert((Dancer) searcher);
            } else {
                return new SimpleEntry(searcher, woman);

            }

        } else {
            Dancer male = avlTreeMen.searchAndRemoveMen(searcher.getHeight());
            if (male == null) {

                avlTreeWomen.insert((Dancer) searcher);
            } else {
                return new SimpleEntry(searcher, male);
            }
        }

        avlTreeMen.debug(avlTreeMen.root);
        avlTreeWomen.debug(avlTreeWomen.root);

        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        List<IDancer> listMen = (List<IDancer>) (List<?>) avlTreeMen.inorder();
        List<IDancer> listWomen = (List<IDancer>) (List<?>) avlTreeWomen.inorder();
        List<IDancer> newList = new ArrayList<>();
        newList.addAll(listMen);
        newList.addAll(listWomen);
        Collections.sort(newList, new MyComparator());

        return newList;
    }

}