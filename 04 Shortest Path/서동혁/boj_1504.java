package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// O(ElogN)
public class boj_1504 {
	
	static ArrayList<Node>[] graph;
	static int N;
	static int E;
	static int INF = 200000002;
	
	static class Node implements Comparable<Node> {
        int target, cost;
        
        public Node(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순
        }
    }
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        // O(N)
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        // O(E)
        for (int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            // 양방향 그래프 추가
            graph[s].add(new Node(e, cost));
            graph[e].add(new Node(s, cost));
        }
        
    	st = new StringTokenizer(br.readLine());
        
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());
        
        int result1 = dijkstra(1, V1) + dijkstra(V1, V2) + dijkstra(V2, N);
        int result2 = dijkstra(1, V2) + dijkstra(V2, V1) + dijkstra(V1, N);
        
        if (result1 >= INF && result2 >= INF) System.out.println(-1);
        else {
        	System.out.println(Math.min(result1, result2));
        }
	}
	
	// dist[시작노드] vs cost + dist[다음노드]
	// O(ElogN)
	static int dijkstra(int start, int goal) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			// O(NlogN) -> 최대 N번 꺼내고 꺼낼때마다 logN
			Node current = pq.poll();
			int now = current.target;
			int cost = current.cost;
			
			// cost 값이 이미 dist[now] 보다 작은 경우는 보는 의미가 없음
			if (dist[now] < cost) continue;
			
			// O(ElogN) -> 모든 인접 리스트 체크 && offer 할때마다 logN
			for (Node next : graph[now]) {
				if (dist[next.target] > next.cost + dist[now]) {
					dist[next.target] = next.cost + dist[now];
					pq.offer(new Node(next.target, dist[next.target]));
				}
			}
		}
		
		return dist[goal];
	}
}
