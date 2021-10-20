package pal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        //TODO: add custom fast reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("datasets/pub01.in")));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numPoints = Integer.parseInt(tokenizer.nextToken());
        int numConveyor = Integer.parseInt(tokenizer.nextToken());
        int idCentral = Integer.parseInt(tokenizer.nextToken());

        TNode[] graph = new TNode[numPoints];

        for (int i = 0; i < numPoints; i++) {
            graph[i] = new TNode(i, 0, null, false);
        }


        for (int i = 0; i < numConveyor; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int source = Integer.parseInt(tokenizer.nextToken());
            int destination = Integer.parseInt(tokenizer.nextToken());
            graph[source].succ.add(graph[destination]);
            graph[destination].inverted.add(graph[source]);

        }


        Alg alg = new Alg(graph, numPoints);

        for (int i = 0; i < numPoints; i++) {
            if(graph[i].tIndex==0)
            {
                alg.find_scc(graph[i]);
            }
        }

      alg.createGraphComponent();
        //  alg.Dijkstra(idCentral);

        System.out.println("Conveyor");
        // write your code here
    }
}
