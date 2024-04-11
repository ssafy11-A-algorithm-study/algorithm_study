package seungjun._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NQueen {
	static int T, N, ans;
	static int[][] map;
	static int[] sel;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			sel = new int[N];
			v = new boolean[N];
			ans = 0;
			permutation(0);
			
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void permutation(int idx) {
		// 8방으로 겹치는 부분이 있으면 리턴
		if (idx == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!v[i] && !check(idx, i)) {
				v[i] = true;
				sel[idx] = i;
				map[idx][i] = 1;
				permutation(idx + 1);
				map[idx][i] = 0;
				v[i]=false;
			}
		}
	}

	private static boolean check(int idx, int k) {
		// 대각선
		for (int i = idx + 1, j = k + 1; i < N && j < N; i++, j++) {
			if (map[i][j] == 1) {
				return true;
			}
		}
		for (int i = idx - 1, j = k - 1; i >= 0 && j >= 0; i--, j--) {
			if (map[i][j] == 1) {
				return true;
			}
		}
		for (int i = idx - 1, j = k + 1; i >= 0 && j < N; i--, j++) {
			if (map[i][j] == 1) {
				return true;
			}
		}
		for (int i = idx + 1, j = k - 1; i < N && j >= 0; i++, j--) {
			if (map[i][j] == 1) {
				return true;
			}
		}
		return false;
	}

}
