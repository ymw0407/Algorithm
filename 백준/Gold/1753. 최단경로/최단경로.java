import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class minNode implements Comparable<minNode> {
    private int key; // 도착지점의 번호
    private int weight; // 도착지점까지의 가중치

    public minNode(int k, int w){
        key = k;
        weight = w;
    }

    public int getKey(){
        return this.key;
    }

    public int getWeight(){
        return this.weight;
    }

    @Override
    public int compareTo(minNode minNode) {
        if (weight < minNode.weight) return -1;
        return 1;
    } // 가장 짧은 거리 목록을 뽑을때 minHeap을 사용하게 되는데...

}


class Main {
    public static final int Infinity = 100_1000_100; // v = 1 ~ 20000까지의 값이 들어오기 때문에 20001이 가장 크다가 봐도 된다.
    private static int[] dijkstra(int K, ArrayList<ArrayList<minNode>> graph, int[] answer){
        PriorityQueue<minNode> minHeap = new PriorityQueue<>();
        minHeap.add(new minNode(K, 0)); // 시작 지점 0으로 놓고 시작
        // minHeap에는 아직 사용하지 않은 데이터만 들어있어야한다.

        answer[K] = 0;

        while(!minHeap.isEmpty()){
            minNode node = minHeap.poll();
            int w = node.getWeight();
            int k = node.getKey();
            if (answer[k] < w) continue; // 이미 처리된 노드면 스킵

            for(int i=0; i<graph.get(k).size(); i++){
                ArrayList<minNode> arr = graph.get(k); // arr는 연결되어있는 이웃 노드들의 정보가 들어있다.
                minNode neighborNode = arr.get(i);
                int cost = answer[k] + neighborNode.getWeight();

                if(cost < answer[neighborNode.getKey()]){ // 기존에 있었던 것보다 가까운 것이면,
                    answer[neighborNode.getKey()] = cost;
                    minHeap.add(new minNode(neighborNode.getKey(), cost)); // minHeap에 넣어준
                }
            }

        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 시작 정점의 번호

        ArrayList<ArrayList<minNode>> graph = new ArrayList<>();

        for(int i=0; i<V+1; i++){ // get(u)로 접근하기 위해 V+1 갯수만큼 초기화
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){ // 그래프 만들기
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작지점
            int v = Integer.parseInt(st.nextToken()); // 도착지점
            int w = Integer.parseInt(st.nextToken()); // 가중치
            graph.get(u).add(new minNode(v, w));
        }

        int[] answer = new int[V+1];
        for(int i=0; i<answer.length; i++){
            answer[i] = Infinity;
        }

        answer = dijkstra(K, graph, answer);

        for(int i=1; i<answer.length; i++){
            if(answer[i] == Infinity) System.out.println("INF");
            else System.out.println(answer[i]);
        }

    }

}
