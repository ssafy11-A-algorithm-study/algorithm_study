package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class 인접리스트2 {
	static int V, E, W;
	static ArrayList<Node>[] adjList;
	static boolean[] v;

	static class Node {
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", w=" + w + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V];
		
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}
		
		for (ArrayList<Node> l : adjList) {
			System.out.println(l.toString());
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
			
			for (int i = 0; i < adjList[cur].size(); i++) {
				if(!v[adjList[cur].get(i).to]) {
					v[adjList[cur].get(i).to] = true;
					q.offer(adjList[cur].get(i).to);
				}
			}
		}
	}

	private static void dfs(int cur) {
		v[cur] = true;

		System.out.println(cur);

		for (int i = 0; i < adjList[cur].size(); i++) {
			if(!v[adjList[cur].get(i).to]) {
				dfs(adjList[cur].get(i).to);
			}
		}
	}

}
