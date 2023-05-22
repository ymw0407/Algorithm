import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        for(int i=0; i<E; i++){ // 모든 간선들의 개수만큼 반복하여 graph를 만든다. + undirected graph이기 때문에 (첫번째 -> 두번째)와 (두번째 -> 첫번째)를 모두 기록해주어야함
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 첫번째 정점
            int B = Integer.parseInt(st.nextToken()); // 두번째 정점
            int C = Integer.parseInt(st.nextToken()); // 첫번째와 두번째 정점 사이의 가중치
            minHeap.add(new Edge(A, B, C));
        }
        int ans = kruskal(minHeap, V);
        System.out.println(ans);
    }

    public static int kruskal(PriorityQueue<Edge> minHeap, int V){
        int ans = 0;
        int[] parent = new int[V+1];
        for(int i=0; i< parent.length; i++){
            parent[i] = -1;
        }

        while(!minHeap.isEmpty()){
            Edge edge = minHeap.poll();
            int u = edge.getNodeA();
            int v = edge.getNodeB();
            int w = edge.getWeight();

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

class Edge implements Comparable<Edge> {
    private int nodeA;
    private int nodeB;
    private int weight;

    Edge(int a, int b, int w){
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

    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if(weight < edge.weight) return -1;
        return 1;
    }
}