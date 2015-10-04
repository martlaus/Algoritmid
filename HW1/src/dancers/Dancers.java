package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dancers implements IDancers {


    Tree avlTreeMen = new Tree();
    Tree avlTreeWomen = new Tree();


    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer searcher)
            throws IllegalArgumentException {


        if (searcher.isMale()) {
            AvlNode woman = avlTreeWomen.searchAndRemoveWomen(searcher.getHeight());
            if (woman == null) {
                avlTreeMen.insert((Dancer) searcher);
            } else {
                return new SimpleEntry(searcher, woman);

            }

        } else {
            AvlNode male = avlTreeMen.searchAndRemoveMen(searcher.getHeight());
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
        // TODO Auto-generated method stub
        return null;
    }

}