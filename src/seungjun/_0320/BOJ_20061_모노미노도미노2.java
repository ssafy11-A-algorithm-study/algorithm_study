package seungjun._0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20061_모노미노도미노2 {
	static int N, t, R, C, score, remainBlockCnt;
	static int[][] green, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		green = new int[12][4]; // 0-3: 생성존(빨간색) 4-5: 연한 칸 6-9: 게임존(초록색) 10-11: 소각장
		blue = new int[4][12]; // 0-3: 생성존(빨간색) 4-5: 연한 칸 6-9: 게임존(초록색) 10-11: 소각장

		score = 0;
		remainBlockCnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			t = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			// 블록 생성 건너뛰고 바로 이동
			moveblock();

			// 점수처리
			scoreCal();

			// green
			for (int r = 4; r <= 5; r++) {
				for (int c = 0; c <= 3; c++) {
					if (green[r][c] == 1) {
						// 연한칸 처리
						softGreenArea();
					}
				}
			}
			// blue
			for (int c = 4; c <= 5; c++) {
				for (int r = 0; r <= 3; r++) {
					if (blue[r][c] == 1) {
						// 연한칸 처리
						softBlueArea();
					}
				}
			}
		}

		// remain block counting
		remainBlock();

//		print();
		System.out.println(score);
		System.out.println(remainBlockCnt);
	}

	private static void remainBlock() {
		for (int r = 6; r <= 9; r++) {
			for (int c = 0; c <= 3; c++) {
				if (green[r][c] == 1) {
					remainBlockCnt++;
				}
			}
		}
		for (int r = 0; r <= 3; r++) {
			for (int c = 0; c <= 9; c++) {
				if (blue[r][c] == 1) {
					remainBlockCnt++;
				}
			}
		}
	}

	private static void softBlueArea() {
		// blue
		// 한칸씩 내리기
		for (int c = 9; c >= 4; c--) {
			for (int r = 0; r <= 3; r++) {
				if (blue[r][c] == 1) {
					blue[r][c] = 0;
					blue[r][c + 1] = 1;
				}
			}
		}
	}

	private static void softGreenArea() {
		// green
		// 한칸씩 내리기
		for (int r = 9; r >= 4; r--) {
			for (int c = 0; c <= 3; c++) {
				if (green[r][c] == 1) {
					green[r][c] = 0;
					green[r + 1][c] = 1;
				}
			}
		}
	}

	private static void scoreCal() {
		// green
		for (int r = 6; r <= 9; r++) {
			int blockcnt = 0;
			for (int c = 0; c <= 3; c++) {
				// 모두 1이면 1점 추가 및 0으로 변환 후 빈 공간만큼 내리기
				if (green[r][c] == 1) {
					blockcnt++;
				}
			}
			if (blockcnt == 4) {
				score++;
				for (int c = 0; c <= 3; c++) {
					// 모두 0으로 변환
					green[r][c] = 0;
				}
				// 한칸씩 내리기
				for (int nr = r - 1; nr >= 4; nr--) {
					for (int c = 0; c <= 3; c++) {
						if (green[nr][c] == 1) {
							green[nr][c] = 0;
							green[nr + 1][c] = 1;
						}
					}
				}
			}
		}

		// blue
		for (int c = 6; c <= 9; c++) {
			int blockcnt = 0;
			for (int r = 0; r <= 3; r++) {
				// 모두 1이면 1점 추가 및 0으로 변환 후 빈 공간만큼 내리기
				if (blue[r][c] == 1) {
					blockcnt++;
				}
			}
			if (blockcnt == 4) {
				score++;
				for (int r = 0; r <= 3; r++) {
					// 모두 0으로 변환
					blue[r][c] = 0;
				}
				// 한칸씩 내리기
				for (int nc = c - 1; nc >= 4; nc--) {
					for (int r = 0; r <= 3; r++) {
						if (blue[r][nc] == 1) {
							blue[r][nc] = 0;
							blue[r][nc + 1] = 1;
						}
					}
				}
			}
		}
	}

	private static void moveblock() {
		if (t == 1) {
			// green
			for (int r = R; r <= 9; r++) {
				if (green[r + 1][C] == 1 || r == 9) {
					green[r][C] = 1;
					break;
				}
			}
			// blue
			for (int c = C; c <= 9; c++) {
				if (blue[R][c + 1] == 1 || c == 9) {
					blue[R][c] = 1;
					break;
				}
			}
		} else if (t == 2) {
			// green
			for (int r = R; r <= 9; r++) {
				if (green[r + 1][C] == 1 || green[r + 1][C + 1] == 1 || r == 9) {
					green[r][C] = 1;
					green[r][C + 1] = 1;
					break;
				}
			}
			// blue
			for (int c = C; c <= 9; c++) {
				if (blue[R][c + 2] == 1 || c == 8) {
					blue[R][c] = 1;
					blue[R][c + 1] = 1;
					break;
				}
			}
		} else if (t == 3) {
			// green
			for (int r = R; r <= 9; r++) {
				if (green[r + 2][C] == 1 || r == 8) {
					green[r][C] = 1;
					green[r + 1][C] = 1;
					break;
				}
			}
			// blue
			for (int c = C; c <= 9; c++) {
				if (blue[R][c + 1] == 1 || blue[R + 1][c + 1] == 1 || c == 9) {
					blue[R][c] = 1;
					blue[R + 1][c] = 1;
					break;
				}
			}
		}
	}

	private static void print() {
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(green[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 12; c++) {
				System.out.print(blue[r][c] + " ");
			}
			System.out.println();
		}
	}

}
