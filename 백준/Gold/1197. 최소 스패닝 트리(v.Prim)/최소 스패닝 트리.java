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
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        /* graph 만들기 */
        ArrayList<ArrayList<NeighborNode>> graph = new ArrayList<>();
        for(int i=0; i<V+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){ // 모든 간선들의 개수만큼 반복하여 graph를 만든다. + undirected graph이기 때문에 (첫번째 -> 두번째)와 (두번째 -> 첫번째)를 모두 기록해주어야함
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 첫번째 정점
            int B = Integer.parseInt(st.nextToken()); // 두번째 정점
            int C = Integer.parseInt(st.nextToken()); // 첫번째와 두번째 정점 사이의 가중치
            graph.get(A).add(new NeighborNode(B, C));
            graph.get(B).add(new NeighborNode(A, C));
        }

        int ans = prim(graph, V);

        System.out.println(ans);
        /* graph 만들기 */
    }

    public static int prim(ArrayList<ArrayList<NeighborNode>> graph, int V){
        PriorityQueue<NeighborNode> MinHeap = new PriorityQueue<>();
        MinHeap.add(new NeighborNode(1, 0));

        int maxCost = 0;

        int[] visited = new int[V+1];
        for(int i=0; i<visited.length; i++){
            visited[i] = -1;
        }

        while(!MinHeap.isEmpty()){ // MinHeap이 빌때까지 반복
            NeighborNode curr = MinHeap.poll();
            int currNode = curr.getNode();
            int currWeight = curr.getWeight();
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
    private int weight; // 가중치

    NeighborNode(int n, int w){
        node = n;
        weight = w;
    }

    public int getNode(){
        return node;
    }
    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(NeighborNode neighborNode) {
        if(weight < neighborNode.weight) return -1;
        return 1;
    }
}