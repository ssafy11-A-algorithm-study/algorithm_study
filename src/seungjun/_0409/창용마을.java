package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 창용마을 {
	static int T, V, E;
	static ArrayList<Integer>[] adjList;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from].add(to);
				adjList[to].add(from);
			}
			int ans = 0;
			v = new boolean[V + 1];
			for (int i = 1; i <= V; i++) {
				if (!v[i]) {
					dfs(i);
//					bfs(i);
					ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void dfs(int cur) {
		v[cur] = true;
		
		for (int i = 0; i < adjList[cur].size(); i++) {
			if (!v[adjList[cur].get(i)]) {
				dfs(adjList[cur].get(i));
			}
		}
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		v[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < adjList[cur].size(); i++) {
				if (!v[adjList[cur].get(i)]) {
					v[adjList[cur].get(i)] = true;
					q.offer(adjList[cur].get(i));
				}
			}
		}
	}

}
