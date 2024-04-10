package seungjun._0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 프림 {
	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int node, weight;

		public Vertex(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E;
	static Node[] adjList;
	static int[] minEdge;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, w, adjList[from]);
			adjList[to] = new Node(from, w, adjList[to]);
		}

		v = new boolean[V + 1];

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1, 0));
		int result = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {
			Vertex minVertex = pq.poll();
			if (v[minVertex.node]) {
				continue;
			}
			v[minVertex.node] = true;
			result += minVertex.weight;
			if (++cnt == V) {
				break;
			}
			for(Node tmp = adjList[minVertex.node]; tmp != null; tmp = tmp.next) {
				if (!v[tmp.vertex]) {
					pq.offer(new Vertex(tmp.vertex, tmp.weight));
				}
			}
		}
		
		System.out.println(result);
	}
}
