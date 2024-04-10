package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 창용마을_서로소 {
	static int T, V, E;
	static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// make-set
			set = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				set[i] = i;
			}
			
			// union
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			int ans = 0;
			for (int i = 1; i <= V; i++) {
				if (i == set[i]) {
					ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			set[aRoot] = bRoot;
		}
	}

	private static int find(int num) {
		if (set[num] == num) {
			return num;
		}
		return set[num] = find(set[num]);
	}

}
