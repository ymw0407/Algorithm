import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<NeighborNode>> graph = new ArrayList<>();
        for(int i=0; i<V+1; i++){
            graph.add(new ArrayList<>());
        }

        ArrayList<Double> X = new ArrayList<>(), Y = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            X.add(x);
            Y.add(y);
        }

        for(int x=0; x<X.size(); x++){
            for(int y=0; y<X.size(); y++){
                if(x == y) continue;
                double distance = Math.sqrt(Math.pow(X.get(x) - X.get(y), 2) + Math.pow(Y.get(x) - Y.get(y), 2));
                graph.get(x).add(new NeighborNode(y, distance));
                graph.get(y).add(new NeighborNode(x, distance));
            }
        }

        double ans = prim(graph, V);

        System.out.printf("%.2f", ans);
    }
    public static double prim(ArrayList<ArrayList<NeighborNode>> graph, int V){
        PriorityQueue<NeighborNode> MinHeap = new PriorityQueue<>();
        MinHeap.add(new NeighborNode(1, 0));

        double maxCost = 0;

        int[] visited = new int[V+1];
        for(int i=0; i<visited.length; i++){
            visited[i] = -1;
        }

        while(!MinHeap.isEmpty()){ // MinHeap이 빌때까지 반복
            NeighborNode curr = MinHeap.poll();
            int currNode = curr.getNode();
            double currWeight = curr.getWeight();
            if(visited[currNode] == 0) continue; // 이전에 방문했던 노드면 continue
            visited[currNode] = 0;
            maxCost += currWeight;

            for(int i=0; i<graph.get(currNode).size(); i++){
                NeighborNode neighbor = graph.get(currNode).get(i);
                int neighborNode = neighbor.getNode();
                if(visited[neighborNode] == 0) continue;
                MinHeap.add(neighbor);
            }
        }
        return maxCost;
    }
}

class NeighborNode implements Comparable<NeighborNode> {
    private int node; // 도착 노드 번호
    private double weight; // 가중치

    NeighborNode(int n, double w){
        node = n;
        weight = w;
    }

    public int getNode(){
        return node;
    }
    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(NeighborNode neighborNode) {
        if(weight < neighborNode.weight) return -1;
        return 1;
    }
}
