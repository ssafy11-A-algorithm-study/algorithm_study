package seungjun._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_다리만들기2 {
	static int N, M;
	static int[][] map;
	static int[] minEdge;
	static ArrayList<Node>[] graph;
	static boolean[] v;

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Node implements Comparable<Node> {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 넘버링
		int num = 2;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1) {
					bfs(r, c, num++);
				}
			}
		}


		// 인접 리스트 생성
		graph = new ArrayList[num];
		for (int i = 2; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		// 모든 섬 간의 최단 거리 구하기
		minEdge = new int[graph.length]; // 0, 1은 더미
		for (int n = 2; n < graph.length; n++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == n) {
						check(r, c, n);
					}
				}
			}
		}

		// 최소 스패닝 트리
		int result = 0;
		int cnt = 0;
		v = new boolean[graph.length];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(2, 0));

		while (!pq.isEmpty()) {
			Node minVertex = pq.poll();
			if (v[minVertex.vertex]) {
				continue;
			}
			result += minVertex.weight;
			v[minVertex.vertex] = true;
			cnt++;

			for (int i = 0; i < graph[minVertex.vertex].size(); i++) {
				if (!v[graph[minVertex.vertex].get(i).vertex]) {
					pq.offer(new Node(graph[minVertex.vertex].get(i).vertex, graph[minVertex.vertex].get(i).weight));
				}
			}
		}
		System.out.println(cnt != num - 2 ? -1 : result);
	}

	private static void check(int sr, int sc, int num) {
		for (int d = 0; d < 4; d++) {
			int nr = sr + dr[d];
			int nc = sc + dc[d];
			int leng = 0;
			
			while(true) {
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					break;
				}
				if (map[nr][nc] > 0) {
					if (map[nr][nc] > num && leng > 1) {
						graph[num].add(new Node(map[nr][nc], leng));
						graph[map[nr][nc]].add(new Node(num, leng));
					}
					break;
				}
				if (map[nr][nc] == 0) {
					nr += dr[d];
					nc += dc[d];
					leng++;
				}
			}
		}
	}

	private static void bfs(int sr, int sc, int num) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(sr, sc));
		map[sr][sc] = num;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				if (map[nr][nc] == 0 || map[nr][nc] == num) {
					continue;
				}
				map[nr][nc] = num;
				q.offer(new Point(nr, nc));
			}
		}
	}

}
