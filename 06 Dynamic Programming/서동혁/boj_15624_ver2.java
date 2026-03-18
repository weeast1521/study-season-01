package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_15624_ver2 {
	static long MOD = 1000000007;
	static long[] fiboList;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        fiboList = new long[N + 1];
        
        Arrays.fill(fiboList, -1);
        fiboList[0] = 0;
        fiboList[1] = 1;
        
        System.out.println(Fibo(N));
	}
	
	static long Fibo(int n) {
		if (fiboList[n] != -1) return fiboList[n];
		
		fiboList[n] = (Fibo(n - 1) + Fibo(n - 2)) % MOD;
		
		return fiboList[n];
	}
}