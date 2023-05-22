import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    final static int Infinity = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken()); // 간선 개수


        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for(int i=0; i<V+1; i++){ // 1~V까지 사용하기 위해 graph 초기화
            graph.add(new ArrayList<>());
        }

        /* graph 만들기 - Adjacency list */
        for(int i=0; i<E; i++){ // 간선이 들어오기 때문에개 간선 수만큼 반복
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 가중치
            graph.get(u).add(new Edge(v, w));
        }
        /* graph 만들기 */

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 시작 정점의 번호
        int ans = Integer.parseInt(st.nextToken());


        /* 시작 지점은 0으로 설정하고 나머지는 Infinity로 설정*/
        int[] answer = new int[V+1];
        for(int i=0; i<V+1; i++){
            answer[i] = Infinity;
        }
        answer[K] = 0;
        /* 시작 지점은 0으로 설정하고 나머지는 Infinity로 설정*/

        answer = dijkstra(graph, answer, K);

        System.out.println(answer[ans]);

    }

    public static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int[] answer, int K){
        PriorityQueue<Node> minValue = new PriorityQueue<>();
        minValue.add(new Node(K, 0)); // 시작 값 처음에 넣어주기

        while(!minValue.isEmpty()){
            Node curr = minValue.poll();
            int cost = curr.getCost();
            int currNode = curr.getNode();

            if(cost > answer[currNode]) continue; // cost가 가장 작은 것으로 기록 되어있는거보다 크다면 continue

            for(int i=0; i<graph.get(currNode).size(); i++){
                Edge neighbor = graph.get(currNode).get(i);
                int neighborNode = neighbor.getNode();
                int neighborWeight = neighbor.getWeight();
                int checkCost = neighborWeight + answer[currNode]; // 자기 자신(가장 작은)의 가중치와 이웃 노드의 가중치를 더해서 비교하게 된다.

                if(checkCost < answer[neighborNode]){ // checkCost(현재)랑 answer[neighborNode]를 비교해본다.
                    answer[neighborNode] = checkCost;
                    minValue.add(new Node(neighborNode, checkCost));
                }
            }
        }
        return answer;
    }
}

class Edge {
    private int node; // 도착 정점
    private int weight; // 가중치

    public Edge(int v, int w){
        node = v;
        weight = w;
    }
    public int getNode(){
        return node;
    }
    public int getWeight(){
        return weight;
    }
}

class Node implements Comparable<Node> {
    private int node; // 도착 정점의 넘버
    private int cost; // source로부터의 가중치

    public Node(int node, int cost){
        this.node = node;
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }
    public int getNode(){
        return node;
    }

    @Override
    public int compareTo(Node node) { // PriorityQueue에서 정렬을 하려면 필요함
        if(cost < node.cost) return -1;
        return 1;
    }
}
