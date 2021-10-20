package pal;

import java.util.ArrayList;

public class Component implements Comparable<Component>{
    int cost;
    ArrayList<TNode> nodes;
    ArrayList<Component> neigbours;
    ArrayList<Component> invertedNeigbors;
  //  int invertedNum;

    public Component(int cost)
    {
        this.cost=cost;
      //  this.invertedNum = 0;
        nodes = new ArrayList<>();
        neigbours = new ArrayList<>();
        invertedNeigbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Component o) {
        return this.cost-o.cost;
    }
}
