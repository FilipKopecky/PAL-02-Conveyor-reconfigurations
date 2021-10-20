package pal;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Alg {
    public TNode stackTop;
    public int index;
    public int scc;
    public TNode[] graph;
    public ArrayList<Component> sccs;

    public Alg(TNode[] graph, int size) {
        this.stackTop = null;
        index = 0;
        this.graph = graph;
        this.scc = 0;
        this.sccs = new ArrayList<>();
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
            Component component = new Component(Integer.MAX_VALUE);
            do {
                x = pop(stackTop);
                component.nodes.add(x);
                x.scc = component;
            } while (x != v);
            sccs.add(component);
        }
    }

    public void createGraphComponent()
    {
        for (Component c: sccs) {
            for (TNode node:c.nodes) {
                for (TNode neigbor:graph[node.index].succ) {
                    if(node.scc!=neigbor.scc)
                    {
                        c.neigbours.add(neigbor.scc);
                        neigbor.scc.invertedNeigbors.add(node.scc);
                    }
                }
            }
        }
    }

    public final void Dijkstra(int source) {
        graph[source].cost = 0;
        PriorityQueue<TNode> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(graph[source]);

        while (!priorityQueue.isEmpty()) {
            TNode u = priorityQueue.poll();
            index = u.index;
            for (TNode neighbour : u.succ) {
                int alt = u.cost;
                if (alt < neighbour.cost) {
                    neighbour.cost = alt;

                    priorityQueue.offer(neighbour);
                }
            }
            for (TNode invertedNeigbour : u.inverted) {
                int alt = u.cost + 1;
                if (alt < invertedNeigbour.cost) {
                    invertedNeigbour.cost = alt;

                    priorityQueue.offer(invertedNeigbour);
                }
            }
        }
    }

    public void calc() {

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
