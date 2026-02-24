import java.io.*;
import java.util.*;

public class Main_1655{
	static int N = 0;
	
	// minpq(큰 값중 가장 작은값) maxpq (작은 값중 가장 큰 값) 사이의 균형
	// 수가 같다면 더 작은 값 -> maxpq를 peek
	static PriorityQueue<Integer> minpq = null;
	static PriorityQueue<Integer> maxpq = null;
	
	
	static void init() {
		minpq = new PriorityQueue<>();
		maxpq = new PriorityQueue<>((o1,o2)-> (-Integer.compare(o1,o2)));
	}
	
	//O(logN + logN)
	static void pushQ(int number) { 
		if(maxpq.isEmpty()) {maxpq.add(number); return;}
		if(maxpq.peek() <number) {
			minpq.add(number);
		}
		else {
			maxpq.add(number);
		}
		updateQ();
	}
	//O(logN) 
	static void updateQ() { // 균형잡기 
		if(minpq.size()+2 <= maxpq.size()) {
			minpq.add(maxpq.poll());
		}
		if(minpq.size() > maxpq.size()) {
			maxpq.add(minpq.poll());
		}
	}
	//O(1)
	static int peekQ() { // 중간 값 반환  
		return maxpq.peek(); // 홀수개면 minpq가 하나 더 많음
	}
	
	// Final => O(NlogN) 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		init();
		for(int i =0; i<N;i++) {  // O(N * 2logN)
			pushQ(Integer.parseInt(br.readLine()));
			sb.append(peekQ()).append("\n");
		}
		System.out.println(sb);
	}
}

// 1. print를 반복문안에서 찍어서 시간초과
// 2. 예외 빼먹는 실수