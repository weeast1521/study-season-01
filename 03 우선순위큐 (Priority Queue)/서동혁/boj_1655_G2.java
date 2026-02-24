package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1655_G2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int num = Integer.parseInt(br.readLine());
        maxHeap.offer(num);
        sb.append(num).append("\n");

        // O(N * log(N/2))
        for (int i = 1; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            // O(logN)
            if (maxHeap.size() == minHeap.size()) maxHeap.offer(num);
            else minHeap.offer(num);

            // O(logN)
            if (maxHeap.element() > minHeap.element()) {
                int temp = minHeap.poll();
                maxHeap.offer(temp);
                minHeap.offer(maxHeap.poll());
            }

            sb.append(maxHeap.element()).append("\n");
        }
        System.out.println(sb);
    }
}
