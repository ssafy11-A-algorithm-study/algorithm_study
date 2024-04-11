package seungjun._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs {
	static int[][] map;
	static boolean[][] v;
	static int n;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		v = new boolean[n][n];

		bfs(2, 2);
		
		for (int[] e : map) {
			System.out.println(Arrays.toString(e));
		}
	}

	private static void bfs(int i, int j) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(i, j));
		v[i][j] = true;

		int cnt = 1;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int k = 0; k < size; k++) {
				Point cur = q.poll();
				int r = cur.r;
				int c = cur.c;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
						continue;
					}
					if (v[nr][nc]) {
						continue;
					}
					map[nr][nc] = 1;
					v[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
			
			cnt++;
			if (cnt > 2) {
				break;
			}
		}
	}

}
