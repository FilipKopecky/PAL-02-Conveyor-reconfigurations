package pal;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Alg {
     TNode stackTop;
     int index;
     int scc;
     TNode[] graph;
     ArrayList<Component> sccs;

    public Alg(TNode[] graph) {
        this.stackTop = null;
        index = 0;
        this.graph = graph;
        this.scc = 0;
        this.sccs = new ArrayList<>();
    }


    final void find_scc(TNode v) {
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

    final void createGraphComponent()
    {
        for (Component c: sccs) {
            for (TNode node:c.nodes) {
                for (TNode neigbor:graph[node.index].succ) {
                    if(node.scc!=neigbor.scc)
                    {
                        c.neigbours.add(neigbor.scc);
                        neigbor.scc.invertedNeigbors.add(node.scc);
                       // neigbor.scc.invertedNum++;
                    }
                }
            }
        }
    }

     final void calculate()
    {
        int reconfs = 0;
        for (Component c:sccs) {
            if(c.invertedNeigbors.size()==0)
                reconfs+=c.cost;
        }
        System.out.println(reconfs);

    }

     final void Dijkstra(int source) {
        graph[source].scc.cost = 0;
        PriorityQueue<Component> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(graph[source].scc);

        while (!priorityQueue.isEmpty()) {
            Component u = priorityQueue.poll();

            for (Component neighbour : u.neigbours) {
                int alt = u.cost;
                if (alt < neighbour.cost) {
                    neighbour.cost = alt;

                    priorityQueue.offer(neighbour);
                }
            }
            for (Component invertedNeigbour : u.invertedNeigbors) {
                int alt = u.cost + 1;
                if (alt < invertedNeigbour.cost) {
                    invertedNeigbour.cost = alt;
                    priorityQueue.offer(invertedNeigbour);
                }
            }
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
