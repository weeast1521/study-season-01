import java.io.*;
import java.util.*;

public class BOJ_1655_가운데를말해요 {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // O(1)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // N * O(log N)
        // -> O(N log N)
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            // O(1)
            int num = Integer.parseInt(br.readLine());
            // k -> 힙 원소
            // O(log k)
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // O(log k)
            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int max = maxHeap.poll();
                    int min = minHeap.poll();
                    maxHeap.add(min);
                    minHeap.add(max);
                }
            }

            // O(1)
            if (!maxHeap.isEmpty()) {
                System.out.println(maxHeap.peek());
            }
        }
    }
}