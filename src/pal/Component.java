package pal;

import java.util.ArrayList;

public class Component {
    int cost;
    //NOT NEEDED
    ArrayList<TNode> nodes;
    boolean toBeCalculated;
    public Component(int cost)
    {
        this.cost=cost;
        nodes= new ArrayList<>();
        this.toBeCalculated = false;
    }
}
