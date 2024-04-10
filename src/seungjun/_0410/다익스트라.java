package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 다익스트라 {
	static int V, E;
	static ArrayList<Node>[] graph;
	static boolean[] v;
	
	static class Node implements Comparable<Node> {
		int vertex, weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
	

	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, w));
		}
		
		v = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node minVertex = pq.poll();
			if(v[minVertex.vertex]) {
				continue;
			}
			v[minVertex.vertex] = true;
			if (++cnt == V) {
				break;
			}
			
			for (int i = 0; i < graph[minVertex.vertex].size(); i++) {
				if (!v[graph[minVertex.vertex].get(i).vertex]) {
					pq.offer(new Node(graph[minVertex.vertex].get(i).vertex, minVertex.weight + graph[minVertex.vertex].get(i).weight));
				}
			}
		}
	}

}
