package pal;

import java.util.ArrayList;

public class TNode {
    int index;
    int tIndex;
    int lowlink;
    TNode pred;
    boolean instack;
    ArrayList<TNode> succ;

    public TNode(int index, int lowlink, TNode pred, boolean instack) {
        this.tIndex = 0;
        this.index = index;
        this.lowlink = lowlink;
        this.pred = pred;
        this.instack = instack;
        this.succ = new ArrayList<>();
    }

}
