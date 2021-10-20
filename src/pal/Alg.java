package pal;

import java.util.ArrayList;

public class Alg {
    public TNode stackTop;
    public int index;
    public int scc;
    public TNode[] graph;

    public Alg(TNode[] graph) {
        this.stackTop = null;
        index = 0;
        this.graph = graph;
        this.scc = 0;
    }


    public void find_scc(TNode v) {
        v.tIndex = v.lowlink = ++index;
        push(v);
        for (TNode w : v.succ) {
            if (w.tIndex == 0) {
                find_scc(w);
                v.lowlink = Math.min(v.lowlink, w.lowlink);
            } else if (w.instack) {
                v.lowlink = Math.min(v.lowlink, w.tIndex);
            }
        }

        if (v.lowlink == v.tIndex) {
            scc++;
            TNode x;
            ArrayList<TNode> component = new ArrayList<>();
            do {
                x = pop(stackTop);
                component.add(x);
            } while (x != v);
            System.out.println("Component created");
        }
    }

    private void push(TNode v) {
        v.pred = stackTop;
        v.instack = true;
        stackTop = v;
    }

    private TNode pop(TNode v) {
        stackTop = v.pred;
        v.pred = null;
        v.instack = false;
        return v;

    }
}
