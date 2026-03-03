import java.io.*;
import java.util.*;


class Solution {
    static int N ;
    static int[][] faresS ;
    
    static List<int[]>[] dist = null ;
    static List<int[]>[] init(){
        int n = N;
        int[][] fares = faresS;
        List<int[]>[] dist2 = new List[n];for(int i =0; i<n;i++)dist2[i] = new ArrayList<>();
        for(int i =0; i< fares.length;i++){
            int[] r = fares[i];
            dist2[r[0]].add(new int[]{r[0],r[1],r[2]});
            dist2[r[1]].add(new int[]{r[1],r[0],r[2]});
        }
        
        return dist2;
    }
    //O(logN
    public int[] dijkstra(int s){
        // if(s == e){return 0;}
        dist = init();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> (Integer.compare(o1[2],o2[2])));
        int[] arr = new int[N];
        Arrays.fill(arr,Integer.MAX_VALUE);
        arr[s] = 0;
        for(int i =0; i< dist[s].size();i++){
            int[] cur = dist[s].get(i);
            pq.add(new int[]{cur[0], cur[1],cur[2]});
            arr[cur[1]] = cur[2];
            // System.out.println(": " +Arrays.toString(new int[]{cur[0], cur[1],cur[2]}));
        }
        //O((V+E)log(V+E))
        while(!pq.isEmpty()){
            int[] cur = pq.poll(); // O(log(V+E))
            // if(cur[1] == e) return arr;
            if(cur[2] > arr[cur[1]]) continue;
            for(int[] ncur : dist[cur[1]]){ // O(V+E)
                if(arr[ncur[1]] > cur[2] + ncur[2]){
                    arr[ncur[1]] = cur[2] + ncur[2];
                    pq.add(new int[]{cur[0], ncur[1],cur[2] + ncur[2]});
                    // System.out.println(Arrays.toString(new int[]{cur[0], ncur[1],cur[2] + ncur[2]}));
                }
            }
            // 지금까지의 간선
            // 지금부터 다음 노선 까지 했을 때 갱신할 경우
            // queue에다가 넣는다 ㅇㅇ
        }
        return arr;
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 가장 간단하게 구현하기
        n= n+1;
        N = n;
        faresS = fares;
        int min = Integer.MAX_VALUE;

        //O((V+E)log(V+E)) // V ~= n^2
        int[] r1 = dijkstra(s);
        int[] r2 = dijkstra(a);
        int[] r3 = dijkstra(b);
        for(int i = 1; i< n;i++){
            min = Math.min(r1[i]+r2[i]+r3[i],min);
        }
        
        int answer = min;
        return answer;
    }
}