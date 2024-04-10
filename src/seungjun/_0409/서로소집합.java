package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서로소집합 {
	static int T, N, M;
	static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			set = new int[N + 1];
			// make-set
			for (int i = 1; i <= N; i++) {
				set[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int order = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (order == 0) {
					union(a,b);
				} else {
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			System.out.println("#"+tc+" "+sb.toString());
		}
	}

	private static int find(int num) {
		if (set[num] == num) {
			return num;
		}
		return set[num] = find(set[num]);
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			set[aRoot] = bRoot; 
		}
		
	}

}
