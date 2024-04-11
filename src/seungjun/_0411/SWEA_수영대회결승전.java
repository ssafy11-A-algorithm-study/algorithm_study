package seungjun._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_수영대회결승전 {
	static int T, N, lr, lc, ans;
	static int[][] map;
	static boolean[][] v;

	static class Player implements Comparable<Player> {
		int r, c, time;

		public Player(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public int compareTo(Player o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, o.time);
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			lr = Integer.parseInt(st.nextToken());
			lc = Integer.parseInt(st.nextToken());

			v = new boolean[N][N];
			ans = 0;
			bfs(sr, sc);
			System.out.println("#" + tc + " " + (ans == 0 ? -1 : ans));
		}
	}

	private static void bfs(int sr, int sc) {
		PriorityQueue<Player> q = new PriorityQueue<>();
		q.offer(new Player(sr, sc, 0));
		v[sr][sc] = true;

		while (!q.isEmpty()) {
			Player cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int time = cur.time;

			if (r == lr && c == lc) {
				ans = time;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				if (map[nr][nc] == 1) {
					continue;
				}
				if (v[nr][nc]) {
					continue;
				}
				if (map[nr][nc] == 2) {
					q.offer(new Player(nr, nc, time + 2 - time % 3 + 1));
					v[nr][nc] = true;
				}
				if (map[nr][nc] == 0) {
					q.offer(new Player(nr, nc, time + 1));
					v[nr][nc] = true;
				}
			}
		}
	}

}
