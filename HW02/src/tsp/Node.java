package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mart on 14.11.15.
 */
public class Node {
    int value;
    String id;
    int rowIndex;
    int roadLength;
    int columnIndex;
    List<Node> children;
    Node parent;

    public Node(int value, int rowIndex, int columnIndex, Node parent) {
        this.value = value;
        this.id = UUID.randomUUID().toString();
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.parent = parent;
        children = new ArrayList<>();
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

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getLengthToTop(){
        int res = 0;
        if(parent != null) {
            int dist = getValue();
            int n = parent.getLengthToTop();
            res += (dist + n);
        }
        return res;
    }
}
