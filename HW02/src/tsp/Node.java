package tsp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mart on 14.11.15.
 */
public class Node {
    private int value;
    private int rowIndex;
    private int roadLength;
    private int columnIndex;
    private Node parent;
    private int bound;
    private List<Integer> parents = null;

    public Node(int value, int rowIndex, int columnIndex, Node parent) {

        this.value = value;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.parent = parent;
        if (parent != null) {
            roadLength = value + parent.getRoadLength();
            if (parent.getParents() != null) {
                this.parents = new ArrayList<>(parent.getParents());
            } else {
                this.parents = new ArrayList<>();
            }

            parents.add(parent.getColumnIndex());
        } else {
            roadLength = value;
            this.parents = null;
        }
        this.bound = TSP.bound(getVisitedRows()) + roadLength;
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

    public Set<Integer> getVisitedRows() {
        Set<Integer> res = null;
        if (parents != null) {
            res = new HashSet<>(parents);
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
