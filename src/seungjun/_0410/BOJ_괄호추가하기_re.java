package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_괄호추가하기_re {
	static int N, ans;
	static String input_data;
	static int[] nums;
	static char[] ops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		nums = new int[N / 2 + 1];
		ops = new char[N / 2];

		st = new StringTokenizer(br.readLine());

		input_data = st.nextToken();

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				nums[i / 2] = input_data.charAt(i) - '0';
			} else {
				ops[i / 2] = input_data.charAt(i);
			}
		}
		ans = Integer.MIN_VALUE;
		recursive(nums[0], 0);

		System.out.println(ans);
	}

	private static void recursive(int res, int idx) {
		if (idx >= ops.length) {
			ans = Math.max(ans, res);
			return;
		}

		// 그냥 계산하는 경우
		int res1 = calc(ops[idx], res, nums[idx + 1]);
		recursive(res1, idx + 1);

		// 괄호 추가하는 경우
		if (idx + 1 < ops.length) {
			int res2 = calc(ops[idx + 1], nums[idx + 1], nums[idx + 2]);
			recursive(calc(ops[idx], res, res2), idx + 2);
		}
	}

	private static int calc(char op, int n1, int n2) {
		switch (op) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		case '*':
			return n1 * n2;
		}
		return -1;
	}

}
