package pal;

import java.util.ArrayList;

public class Component {
    int cost;
    ArrayList<TNode> nodes;
    ArrayList<Component> neigbours;
    ArrayList<Component> invertedNeigbors;

    public Component(int cost)
    {
        this.cost=cost;
        nodes = new ArrayList<>();
        neigbours = new ArrayList<>();
        invertedNeigbors = new ArrayList<>();
    }
}
