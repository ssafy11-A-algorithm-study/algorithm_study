package seungjun._0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17825_주사위윷놀이 {
	static int[][] route, route10, route20, route25, route30;
	static int[] dice, sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dice = new int[10];
		sel = new int[10];
		
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		// 0: 점수 1: 말의 유무
		route = new int[22][2]; // 2~40 and 출발지, 도착지 
		route10 = new int[][] {{10,0},{13,0},{16,0},{19,0},{25,0}}; // 10 13 16 19 25
		route20 = new int[][] {{20,0},{22,0},{24,0},{25,0}}; // 20 22 24 25
		route25 = new int[][] {{25,0},{30,0},{35,0},{40,0}}; // 25 30 35 40
		route30 = new int[][] {{30,0},{28,0},{27,0},{26,0},{25,0}}; // 30 28 27 26 25
		
 		for (int i = 1; i <= 20; i++) {
			route[i][0] = i*2;
		}
 		
 		permutaion(0);
	}

	private static void permutaion(int k) {
		if (k == 10) {
			
			return;
		}
		
		
		for (int i = 1; i <= 4; i++) {
			sel[k] = i;
			permutaion(k+1);
		}
	}

}
