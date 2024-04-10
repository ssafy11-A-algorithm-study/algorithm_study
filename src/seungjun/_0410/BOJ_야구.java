package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_야구 {
	static int N, result, ans;
	static int[][] players;
	static int[] sel;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		players = new int[N][9];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++) {
				players[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		sel = new int[9];
		v = new boolean[9];
		v[0] = true;
		
		ans = Integer.MIN_VALUE;
		permutation(0);
		
		System.out.println(ans);
	}

	private static void permutation(int k) {
		if (k == 9) {
			simulation();
			ans = Math.max(ans, result);
			return;
		}
		
		if (k == 3) {
			permutation(k+1);
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = i;
				permutation(k + 1);
				v[i] = false;
			}
		}
	}

	private static void simulation() {
		Queue<Integer> base = new ArrayDeque<>();
		result = 0;
		int seq = 0;
		// 선택된 타순으로 시뮬레이션
		for (int r = 0; r < N; r++) {
			base.clear();
			int out = 0;
			while (true) {
				int swing = players[r][sel[seq]];
				
				if (swing == 0) {
					out++;
					if (out == 3) {
						seq = (seq + 1) % 9;
						break;
					}
				} else if (swing == 4) {
					result += base.size() + 1;
					base.clear();
				} else {
					int size = base.size();
					for (int i = 0; i < size; i++) {
						int runner = base.poll();
						if (runner + swing >= 4) {
							result++;
						} else {
							base.offer(runner + swing);
						}
					}
					base.offer(swing);
				}
				

				seq = (seq + 1) % 9;
			}
		}
	}

}
