import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 1000 * 200000; // 최대 c * 최대 E

    /* O(N**2) */
    static int[] dijkstra(List<int[]>[] G, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->  a[1] - b[1]);
        int[] dist = new int[G.length];

        /* O(N) */
        Arrays.fill(dist, MAX);
        dist[start] = 0;

        /* O(log N) */
        pq.add(new int[] {start, dist[start]});

        /* O(N**2) */
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] > dist[curr[0]]) { continue; }

            /* O(N) */
            for (int[] node : G[curr[0]]){
                if (dist[node[0]] > dist[curr[0]] + node[1]) {
                    dist[node[0]] = dist[curr[0]] + node[1];
                    pq.offer(new int[] {node[0], dist[node[0]]});
                }
            }
        }

        return dist;
    }
    public static void main(String[] args) throws Exception {
        /* 전체 시간복잡도 - O(N**2) */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<int[]>[] G = new List[N + 1];

        /* O(N) */
        for (int i = 0; i < N + 1; i++) {
            G[i] = new ArrayList<>();
        }
        int v1, v2;

        /* O(N**2) ..? */
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            G[a].add(new int[] {b, cost});
            G[b].add(new int[] {a, cost});
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        /* O(N**2) */
        int[] distStart = dijkstra(G, 1);
        int[] distV1 = dijkstra(G, v1);
        int[] distV2 = dijkstra(G, v2);

        int distV1ToV2 = distStart[v1] + distV1[v2] + distV2[N];
        int distV2ToV1 = distStart[v2] + distV2[v1] + distV1[N];

        if (distV1ToV2 >= MAX && distV2ToV1 >= MAX) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(distV1ToV2, distV2ToV1));
        }
    }
}