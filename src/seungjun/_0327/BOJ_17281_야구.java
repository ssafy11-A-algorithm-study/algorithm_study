package seungjun._0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	static int N, curScore, ans;
	static int[][] player;
	static int[] sel;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		player = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sel = new int[9];
		v = new boolean[9];
		ans = Integer.MIN_VALUE;
		permutation(0);
		System.out.println(ans);
	}

	private static void permutation(int k) {
		if (k == 9) {
			System.out.println(Arrays.toString(sel));
			// 해당 타순으로 시뮬레이션
//			simulation();
//			ans = Math.max(ans, curScore);
			return;
		}

		if (k == 3) {
			permutation(k + 1);
			return;
		}

		for (int i = 1; i < 9; i++) {
			if (!v[i]) {
				sel[k] = i;
				v[i] = true;
				permutation(k + 1);
				v[i] = false;
			}
		}
	}

	private static void simulation() {
		// 모든 이닝 해당 타순으로 시뮬레이션
		// 고려 요소
		// 3 아웃, 9번 타자 이후 1번 타자, 이닝 별로 마지막 타자 기억
		int j = 0;
		curScore = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int outCnt = 0;
			q.clear();

			while (true) {
				// 이번 타석의 선수 인덱스
				int hit = player[i][sel[j]];

				if (hit == 0) {
					outCnt++;
					if (outCnt == 3) {
						j++;
						if (j == 9) {
							j = 0;
						}
						break;
					}
				} else if (hit == 4) {
					curScore += q.size() + 1;
					q.clear();
				} else {
					int size = q.size();
					for (int k = 0; k < size; k++) {
						int el = q.poll();
						if (el + hit >= 4) {
							curScore++;
						} else {
							q.offer(el + hit);
						}
					}
					q.offer(hit);
				}

				j++;
				if (j == 9) {
					j = 0;
				}
			}

		}

	}

}
