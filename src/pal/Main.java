package pal;


// Working program using Reader Class
import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }


        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            return ret;
        }


        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {

         //long start = System.nanoTime();

        Reader reader = new Reader();
       int numPoints = reader.nextInt();
        int numConveyor = reader.nextInt();
        int idCentral = reader.nextInt();

      /**  BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("datasets/pub10.in")));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numPoints = Integer.parseInt(tokenizer.nextToken());
        int numConveyor = Integer.parseInt(tokenizer.nextToken());
        int idCentral = Integer.parseInt(tokenizer.nextToken());

**/


        TNode[] graph = new TNode[numPoints];

        for (int i = 0; i < numPoints; i++) {
            graph[i] = new TNode(i, 0, null, false);
        }


        for (int i = 0; i < numConveyor; i++) {
            int source = reader.nextInt();
            int destination = reader.nextInt();
            TNode s = graph[source];
            TNode d = graph[destination];
            s.succ.add(d);
          // d.inverted.add(s);

        }


        Alg alg = new Alg(graph);

        for (int i = 0; i < numPoints; i++) {
            if (graph[i].tIndex == 0) {
                alg.find_scc(graph[i]);
            }
        }

        alg.createGraphComponent();
        alg.Dijkstra(idCentral);
        alg.calculate();

     /**   long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed/1000000);**/
        // write your code here
    }
}
