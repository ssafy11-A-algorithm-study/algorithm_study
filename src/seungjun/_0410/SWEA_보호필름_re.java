package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_보호필름_re {
	static int T, D, W, K, ans;
	static int[][] cell, copyCell;
	static int[] sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cell = new int[D][W];
			copyCell = new int[D][W];

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					cell[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			sel = new int[D];
			ans = Integer.MAX_VALUE;
			recursive(0, 0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void recursive(int idx, int cnt) {
		if (cnt >= ans) {
			return;
		}
		if (idx == D) {
			copy();
			for (int i = 0; i < D; i++) {
				if (sel[i] == 0) {
					for (int j = 0; j < W; j++) {
						copyCell[i][j] = 0;
					}
				} else if (sel[i] == 1) {
					for (int j = 0; j < W; j++) {
						copyCell[i][j] = 1;
					}
				}
			}
			if (test()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}

		// 아무것도 안 넣는 경우
		sel[idx] = -1;
		recursive(idx + 1, cnt);

		// A 시약 넣는 경우
		sel[idx] = 0;
		recursive(idx + 1, cnt + 1);

		// B 시약 넣는 경우
		sel[idx] = 1;
		recursive(idx + 1, cnt + 1);
	}

	private static boolean test() {
		int cnt = 0;
		int res = 0;
		int tmp = 0;
		for (int c = 0; c < W; c++) {
			tmp = copyCell[0][c];
			cnt = 0;
			for (int r = 0; r < D; r++) {
				if (copyCell[r][c] == tmp) {
					cnt++;
					if (cnt == K) {
						res++;
						break;
					}
				} else {
					cnt = 1;
					tmp = copyCell[r][c];
				}
			}
			if (res != c + 1) {
				return false;
			}
		}
		return true;
	}

	private static void copy() {
		for (int r = 0; r < D; r++) {
			for (int c = 0; c < W; c++) {
				copyCell[r][c] = cell[r][c];
			}
		}
	}
}
