package tsp;

import java.util.UUID;

/**
 * Created by mart on 14.11.15.
 */
public class Node {
    public boolean visited;
    int value;
    String id;
    int rowIndex;
    int roadLength;
    int minLength;
    int columnIndex;

    public Node(int value, int rowIndex, int columnIndex) {
        this.value = value;
        this.id = UUID.randomUUID().toString();
        this.rowIndex = rowIndex;
        this.visited = false;
        this.columnIndex = columnIndex;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }


}
