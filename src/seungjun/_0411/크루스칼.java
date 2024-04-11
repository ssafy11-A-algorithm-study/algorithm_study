package seungjun._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 크루스칼 {
	static int V, E;
	static Edge[] edgeList;
	static int[] set;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edgeList = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			int w =  Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, w);
		}
		
		Arrays.sort(edgeList);
		
		set = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			set[i] = i;
		}
		int res = 0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if(!union(edge.from, edge.to)) {
				continue;
			}
			res += edge.weight;
			if (++cnt == V - 1) {
				break;
			}
		}
		
		System.out.println(res);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		set[aRoot] = bRoot;
		return true;
	}

	private static int find(int a) {
		if (set[a] == a) {
			return a;
		}
		return set[a] = find(set[a]);
	}

}
