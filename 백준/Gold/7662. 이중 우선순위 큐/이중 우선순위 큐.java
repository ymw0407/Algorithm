import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int i_=0; i_<T; i_++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Map<Integer, Integer> map = new HashMap<>(); // priorityQueue에서 remove와 같은 경우 O(n) -> 시간 초과 발생 -> Map을 통해서 숫자 저장
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String calc = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                if (Objects.equals(calc, "I")) {
                    map.put(n, map.getOrDefault(n,0) + 1); // map에 n에 해당하는 값이 있으면 해당 값을 반환하고, 아니면 0을 반환
                    minHeap.add(n);
                    maxHeap.add(n);
                } else if (Objects.equals(calc, "D")) {
                    if (map.size() == 0) continue;
                    PriorityQueue<Integer> q = n == 1 ? maxHeap : minHeap;
                    removeMap(q, map);

//                    if (n == 1) {
//                        minHeap.remove(maxHeap.poll());
//                    } else if (n == -1) {
//                        maxHeap.remove(minHeap.poll());
//                    }
                }
            }
            if(map.size() == 0) System.out.println("EMPTY");
            else {
                int n = removeMap(maxHeap, map);
                System.out.println(n + " " + (map.size() > 0 ? removeMap(minHeap, map) : n));
            }
        }
    }
    static int removeMap(PriorityQueue<Integer> que, Map<Integer, Integer> map) {
        int num;
        while (true) {
            num = que.poll();
            int cnt = map.getOrDefault(num, 0);

            if (cnt == 0)
                continue;

            if (cnt == 1)
                map.remove(num);
            else
                map.put(num, cnt - 1);

            break;
        }

        return num;
    }
}
