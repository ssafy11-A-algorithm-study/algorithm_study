package seungjun._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 프림 {
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
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			int w =  Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, w));
			graph[to].add(new Node(from, w));
		}
		int res = 0;
		int cnt = 0;
		v = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node minVertex = pq.poll();
			
			if(v[minVertex.vertex]) {
				continue;
			}
			
			res += minVertex.weight;
			v[minVertex.vertex] = true;
			if (++cnt == V) {
				break;
			}
			
			for (int i = 0; i < graph[minVertex.vertex].size(); i++) {
				if (!v[graph[minVertex.vertex].get(i).vertex]) {
					pq.offer(new Node(graph[minVertex.vertex].get(i).vertex, graph[minVertex.vertex].get(i).weight));
				}
			}
		}
		
		System.out.println(res);
	}

}
