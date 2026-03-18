package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_15624 {
	static long MOD = 1000000007;
	static int N;
	static long[] fiboList = {0, 1, 1};
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        if (N < 3) {
        	System.out.println(fiboList[N]);
        }
        else {
        	for (int i = 3; i < N + 1; i++) {
        		fiboList[i % 3] = (fiboList[(i+1) % 3] + fiboList[(i+2) % 3]) % MOD;
        	}
        	
        	System.out.println(fiboList[N % 3]);
        }
	}
}