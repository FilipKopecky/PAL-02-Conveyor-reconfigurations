package pal;

import java.util.ArrayList;

public class TNode   {
    int index;
    int tIndex;
    int lowlink;
    TNode pred;
    boolean instack;
    ArrayList<TNode> succ;
//    ArrayList<TNode> inverted;
    Component scc;


    public TNode(int index, int lowlink, TNode pred, boolean instack) {
        this.tIndex = 0;
        this.index = index;
        this.lowlink = lowlink;
        this.pred = pred;
        this.instack = instack;
        this.succ = new ArrayList<>();
       // this.inverted = new ArrayList<>();

    }

}
