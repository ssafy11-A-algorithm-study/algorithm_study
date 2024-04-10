package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_숫자만들기 {
	static int T, N, ans, max, min;
	static int[] nums, ops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			ops = new int[4];
			nums = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			recursive(nums[0], 1);
			ans = max - min;
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void recursive(int res, int idx) {
		if (idx == N) {
			max = Math.max(res, max);
			min = Math.min(res, min);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (ops[i] > 0) {
				if (i == 0) {
					ops[i]--;
					recursive(res + nums[idx], idx + 1);
					ops[i]++;
				} else if (i == 1) {
					ops[i]--;
					recursive(res - nums[idx], idx + 1);
					ops[i]++;
				} else if (i == 2) {
					ops[i]--;
					recursive(res * nums[idx], idx + 1);
					ops[i]++;
				} else if (i == 3) {
					ops[i]--;
					recursive(res / nums[idx], idx + 1);
					ops[i]++;
				}
			}
		}
	}

}
