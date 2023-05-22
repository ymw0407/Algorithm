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
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수

        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points.add(new Point(x, y));
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        for (int i = 0; i < points.size(); i++) {
            for (int ii = 0; ii < points.size(); ii++){
                if(i == ii) continue;
                double distance = points.get(i).distance(points.get(ii));
                minHeap.add(new Edge(i, ii, distance));
            }
        }

        double ans = kruskal(minHeap, V);

        System.out.printf("%.2f", ans);
    }

    public static double kruskal(PriorityQueue<Edge> minHeap, int V){
        double ans = 0;
        int[] parent = new int[V];
        for(int i=0; i< parent.length; i++){
            parent[i] = -1;
        }

        while(!minHeap.isEmpty()){
            Edge edge = minHeap.poll();
            int u = edge.getNodeA();
            int v = edge.getNodeB();
            double w = edge.getWeight();

            int ur = find(u, parent);
            int vr = find(v, parent);

            if(ur != vr){ // root가 다르다 -> 방문한적이 없다. cost 더해주기! -> union!
                parent[vr] = ur;
                ans += w;
            }
        }

        return ans;
    }

    public static int find(int u, int[] parent){
        if(parent[u] == -1) return u;
        int v = u;
        while(parent[u] != -1){
            u = parent[u];
        }
        while(parent[v] != -1){ // path compression
            int tmp = v;
            v = parent[v];
            parent[tmp] = u;
        }
        return u;
    }
}

class Point{
    private double X, Y;

    public Point(double x, double y){
        X = x;
        Y = y;
    }

    public double distance(Point other){
        return Math.sqrt(Math.pow(X - other.X, 2) + Math.pow(Y - other.Y, 2));
    }
}


class Edge implements Comparable<Edge> {
    private int nodeA;
    private int nodeB;
    private double weight;

    Edge(int a, int b, double w){
        nodeA = a;
        nodeB = b;
        weight = w;
    }

    public int getNodeA(){
        return nodeA;
    }

    public int getNodeB(){
        return nodeB;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if(weight < edge.weight) return -1;
        return 1;
    }
}