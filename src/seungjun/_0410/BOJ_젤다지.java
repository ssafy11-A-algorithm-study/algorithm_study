package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_젤다지 {
	static int N;
	static int[][] map, ans;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point implements Comparable<Point> {
		int r, c, w;

		public Point(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cnt = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				break;
			}

			map = new int[N][N];
			ans = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					ans[r][c] = Integer.MAX_VALUE;
				}
			}
			ans[0][0] = map[0][0];
			bfs(0, 0);
			System.out.println("Problem "+ cnt++ + ": " + ans[N-1][N-1]);
		}
	}

	private static void bfs(int sr, int sc) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(sr, sc, map[sr][sc]));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				if (map[nr][nc] + w < ans[nr][nc]) {
					ans[nr][nc] = map[nr][nc] + w;
					pq.offer(new Point(nr, nc, ans[nr][nc]));
				}
			}
		}
	}

}
