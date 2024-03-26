package seungjun._0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	static int N, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N + 2][N + 2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i <= N + 1; i++) {
			map[i][0] = 1;
			map[0][i] = 1;
			map[N + 1][i] = 1;
			map[i][N + 1] = 1;
		}

//		print();

		recursive(1, 2, 1);

		System.out.println(ans);
	}

	private static void recursive(int r, int c, int dir) {
//		System.out.println(r + " " + c);s

		if (map[r][c] == 1) {
			return;
		}

		if (r == N && c == N) {
			ans++;
			return;
		}

		// 현재 가로로 위치하는 경우
		if (dir == 1) {
			if (routeCheck01(r, c)) { // →
				recursive(r, c + 1, 1);
			}
			if (routeCheck03(r, c)) { // ↘
				recursive(r + 1, c + 1, 3);
			}
		}
		if (dir == 2) {
			if (routeCheck02(r, c)) { // ↓
				recursive(r + 1, c, 2);
			}
			if (routeCheck03(r, c)) { // ↘
				recursive(r + 1, c + 1, 3);
			}
		}
		if (dir == 3) {
			if (routeCheck01(r, c)) { // →
				recursive(r, c + 1, 1);
			}
			if (routeCheck02(r, c)) { // ↓
				recursive(r + 1, c, 2);
			}
			if (routeCheck03(r, c)) { // ↘
				recursive(r + 1, c + 1, 3);
			}
		}
	}

	private static boolean routeCheck01(int r, int c) {
		if (map[r][c + 1] == 1) {
			return false;
		}
		return true;
	}

	private static boolean routeCheck02(int r, int c) {
		if (map[r + 1][c] == 1) {
			return false;
		}
		return true;
	}

	private static boolean routeCheck03(int r, int c) {
		if (map[r][c + 1] == 1) {
			return false;
		}
		if (map[r + 1][c] == 1) {
			return false;
		}
		if (map[r + 1][c + 1] == 1) {
			return false;
		}
		return true;
	}

	private static void print() {
		for (int r = 0; r <= N + 1; r++) {
			for (int c = 0; c <= N + 1; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
