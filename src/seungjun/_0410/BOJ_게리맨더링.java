package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_게리맨더링 {
	static int N, res, ans;
	static int[] man_cnt, sel;
	static boolean[] v1, v2;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		man_cnt = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			man_cnt[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int adjCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt; j++) {
				int from = i;
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
		}

		// 지역구 조합 구하기
		sel = new int[N + 1];
		v1 = new boolean[N + 1];
		v2 = new boolean[N + 1];
		ans = Integer.MAX_VALUE;
		combination(1);
		
		System.out.println(ans);
	}

	private static void combination(int k) {
		if (k == N + 1) {
//			System.out.println(Arrays.toString(sel));
			// 다 같은 구역일 때
			if (check()) {
				simulation();
				ans = Math.min(ans, res);
			}
			return;
		}

		sel[k] = 1;
		combination(k + 1);
		sel[k] = 2;
		combination(k + 1);
	}

	private static boolean check() {
		int tmp = sel[1];
		for (int i = 1; i <= N; i++) {
			if (sel[i] != tmp) {
				return true;
			}
		}
		return false;
	}

	private static void simulation() {
		int res1 = 0;
		int res2 = 0;
		boolean flag = true;
		// 1 구역 확인
		v1 = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (sel[i] == 1) {
				bfs(i, v1);
				break;
			}
		}
		// 2 구역 확인
		v2 = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (sel[i] == 2) {
				bfs(i, v2);
				break;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (v1[i] && sel[i] == 1) {
				res1 += man_cnt[i];
			} else if (v1[i] && sel[i] == 1) {
				res2 += man_cnt[i];
			} else {
				flag = false;
			}
		}
		if (flag) {
			res = Math.abs(res2 - res1);
		}
	}

	private static void bfs(int start, boolean[] v) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		v[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < graph[cur].size(); i++) {
				if (!v[graph[cur].get(i)] && sel[graph[cur].get(i)] == sel[start]) {
					v[graph[cur].get(i)] = true;
					q.offer(graph[cur].get(i));
				}
			}
		}
	}

}
