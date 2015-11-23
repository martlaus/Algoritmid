package tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 14.11.15.
 */
public class Node {
    int value;
    String id;
    int rowIndex;
    int roadLength;
    int columnIndex;
    Node parent;
    int bound;
    List<Integer> parents = null;

    public Node(int value, int rowIndex, int columnIndex, Node parent) {

        this.value = value;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.parent = parent;
        if (parent != null) {
            if (parent.getParents() != null) {
                this.parents = new ArrayList<>(parent.getParents());
            } else {
                this.parents = new ArrayList<>();
            }

            //if (!parents.contains(parent.getColumnIndex())) {
            parents.add(parent.getColumnIndex());
            //}
        } else {
            this.parents = null;
        }
        this.bound = (TSP.bound(getVisitedRows()) + getLengthToTop());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getId() {
        return "value " + value + " rowIndex " + rowIndex + "columnIndex " + columnIndex + " " + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRoadLength() {
        return roadLength;
    }

    public void setRoadLength(int roadLength) {
        this.roadLength = roadLength;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getLengthToTop() {
        int res = 0;
        if (parent != null) {
            int dist = value;
            int n = parent.getLengthToTop();
            res += (dist + n);
        }
        return res;
    }

    public List<Integer> getVisitedRows() {
        List<Integer> res = getParents();
        if (res != null) {
            if (!res.contains(rowIndex)) {

                res.add(rowIndex);
            }
        }

        return res;
    }

    public void printPath() {
        if (parent != null) {
            System.out.print(rowIndex + " <- ");
            parent.printPath();

        }
    }

    public List<Integer> getParents() {
        return parents;
    }

    public void setParents(List<Integer> parents) {
        this.parents = parents;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }
}
