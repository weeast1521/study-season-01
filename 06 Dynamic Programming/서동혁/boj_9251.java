package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// memoization을 제대로 사용하는 것이 중요
public class boj_9251 {
	static String s1;
	static String s2;
	static int result;
	static int[][] memoization;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        s1 = br.readLine();
        s2 = br.readLine();
       
        memoization = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) Arrays.fill(memoization[i], -1);
        
        System.out.println(LCS(0, 0));
	}
	
	// O(N * M) -> memoization을 사용하기에 (i, j)쌍마다 한 번씩만 계산됨
	static int LCS(int i, int j) {
		if(i >= s1.length() || j >= s2.length()) return 0;
		
		if (memoization[i][j] == -1) {
			if (s1.charAt(i) == s2.charAt(j)) {
				memoization[i][j] = 1 + LCS(i+1, j+1);
			}
			else {
				memoization[i][j] = Math.max(LCS(i+1, j), LCS(i, j+1));
			}
		}
		
		return memoization[i][j];
	}
}