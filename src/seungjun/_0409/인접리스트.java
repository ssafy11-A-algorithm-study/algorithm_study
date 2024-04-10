package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class 인접리스트 {
	static int V, E, W;
	static Node[] adjList;
	static boolean[] v;

	static class Node {
		int to, w;
		Node next;

		public Node(int to, int w, Node next) {
			this.to = to;
			this.w = w;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", w=" + w + ", next=" + next + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
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
			
			for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
				if (!v[tmp.to]) {
					v[tmp.to] = true;
					q.offer(tmp.to);
				}
			}
		}
	}

	private static void dfs(int current) {
		v[current] = true;

		System.out.println(current);

		for (Node tmp = adjList[current]; tmp != null; tmp = tmp.next) {
			if (!v[tmp.to]) {
				dfs(tmp.to);
			}
		}
	}

}
