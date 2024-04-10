package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_색종이붙이기 {
	static int[][] map;
	static final int N = 10, SIZE = 5;
	static int[] papers = { 0, 5, 5, 5, 5, 5 };
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		recursive(0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void recursive(int cnt) {
		int sr = -1;
		int sc = -1;
		// 시작점 찾기
		L: for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) {
					sr = r;
					sc = c;
					break L;
				}
			}
		}
		// 더이상 1이 없으면 종료
		if (sr == -1 && sc == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 붙일 색종이 사이즈 구하기
		int size = getSize(sr, sc);

		// 색종이 붙이기
		for (int i = 1; i <= size; i++) {
			if (papers[i] > 0) {
				for (int r = sr; r < sr + i; r++) {
					for (int c = sc; c < sc + i; c++) {
						map[r][c] = 0;
					}
				}
				papers[i]--;
				recursive(cnt + 1);
				for (int r = sr; r < sr + i; r++) {
					for (int c = sc; c < sc + i; c++) {
						map[r][c] = 1;
					}
				}
				papers[i]++;
			}
		}
	}

	private static int getSize(int sr, int sc) {
		for (int i = SIZE; i > 0; i--) {
			boolean flag = true;
			L: for (int r = sr; r < sr + i; r++) {
				for (int c = sc; c < sc + i; c++) {
					if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] == 0) {
						flag = false;
						break L;
					}
				}
			}
			if (flag) {
				return i;
			}
		}
		return -1;
	}

}
