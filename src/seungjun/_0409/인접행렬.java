package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class 인접행렬 {
	static int V, E, W;
	static int[][] adjMatrix;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjMatrix = new int[V][V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjMatrix[from][to] = weight;
//			adjMatrix[to][from] = weight;
		}

		v = new boolean[V];
		dfs(0);
		System.out.println();
		v = new boolean[V];
		bfs(0);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		v[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			System.out.println(cur);
			
			for (int i = 0; i < V; i++) {
				if(!v[i] && adjMatrix[cur][i] > 0) {
					v[i] = true;
					q.offer(i);
				}
			}
		}
	}

	private static void dfs(int current) {
		v[current] = true;
		System.out.println(current);

		for (int i = 0; i < V; i++) {
			if (!v[i] && adjMatrix[current][i] > 0) {
				v[i] = true;
				dfs(i);
			}
		}
	}

}
