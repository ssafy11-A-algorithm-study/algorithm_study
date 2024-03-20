package seungjun._0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {
	static int N, K, ans;
	static Integer[][] dp;
	static int[] w, v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		w = new int[N];
		v = new int[N];
		dp = new Integer[N][K + 1];

		ans = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		int result = knapsack(N - 1, K);

		System.out.println(result);
	}

	private static int knapsack(int i, int k) {
		if (i < 0) {
			return 0;
		}

		if (dp[i][k] == null) {
			if (w[i] > k) {
				dp[i][k] = knapsack(i - 1, k);
			} else if (w[i] <= K) {
				dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - w[i]) + v[i]);
			}
		}

		return dp[i][k];
	}

}
