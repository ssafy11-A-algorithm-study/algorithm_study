package seungjun._0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_17837_새로운게임2 {
	static class Horse {
		int num, r, c, dir;

		public Horse(int num, int r, int c, int dir) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static class Map {
		int color;
		Deque<Horse> horseDq;

		public Map(int color) {
			this.color = color;
			horseDq = new ArrayDeque<>();
		}

	}

	static int N, K;
	static Map[][] map;
	static Horse[] horses;

//	순서대로 →, ←, ↑, ↓의 의미
	static int dr[] = { 0, 0, 0, -1, 1 };
	static int dc[] = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Map[N + 2][N + 2];
		horses = new Horse[K + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = new Map(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			horses[i] = new Horse(i, r, c, dir);
			// 지도에 말 나타내기
			map[r][c].horseDq.offer(horses[i]);
		}

		int result = calculate();

		System.out.println(result);
	}

	private static int calculate() {
		int time = 1;

		while (time <= 1000) {
			// 말을 하나씩 이동
			for (int i = 1; i <= K; i++) {
				Horse h = horses[i];

				int nextR = h.r + dr[h.dir];
				int nextC = h.c + dc[h.dir];

				// 경계 밖이거나 파란 영역인 경우 방향 반대로
				if (map[nextR][nextC] == null || map[nextR][nextC].color == 2) {
					if (h.dir % 2 == 0) {
						h.dir--;
					} else {
						h.dir++;
					}
					nextR = h.r + dr[h.dir];
					nextC = h.c + dc[h.dir];
				}

				// 방향을 바꿔도 못 가는 경우 패스
				if (map[nextR][nextC] == null) {
					continue;
				}

				// 다음이 빈칸
				if (map[nextR][nextC].color == 0) {
					boolean isMove = false;
					Deque<Horse> dq = map[h.r][h.c].horseDq;
					int size = dq.size();

					Deque<Horse> nextDq = map[nextR][nextC].horseDq;
					for (int j = 0; j < size; j++) {
						Horse horse = dq.poll();

						if (horse == h) {
							isMove = true;
						}

						if (isMove) {
							horse.r = nextR;
							horse.c = nextC;

							nextDq.offer(horse);
						} else {
							dq.offer(horse);
						}
					}
					if (nextDq.size() >= 4) {
						return time;
					}
				}
				// 다음이 빨간
				else if (map[nextR][nextC].color == 1) {
					boolean isMove = true;
					Deque<Horse> dq = map[h.r][h.c].horseDq;
					int size = dq.size();

					Deque<Horse> nextDq = map[nextR][nextC].horseDq;
					for (int j = 0; j < size; j++) {
						Horse horse = dq.pollLast();

						if (isMove) {
							horse.r = nextR;
							horse.c = nextC;

							nextDq.offer(horse);
						} else {
							dq.offerFirst(horse);
						}

						if (horse == h) {
							isMove = false;
						}
					}

					if (nextDq.size() >= 4) {
						return time;
					}
				}
			}
			time++;
		}
		return -1;
	}

}
