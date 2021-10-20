package pal;

import java.util.ArrayList;

public class Component implements Comparable<Component>{
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

    @Override
    public int compareTo(Component o) {
        return this.cost-o.cost;
    }
}
