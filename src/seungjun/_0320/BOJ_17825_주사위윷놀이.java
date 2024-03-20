package seungjun._0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17825_주사위윷놀이 {
	static int[][] route, route10, route20, route25, route30;
	static int[] dice, copy_sel, sel;
	static int ans;
	static Scanner sc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sc = new Scanner(System.in);

		dice = new int[10];
		sel = new int[10];
		copy_sel = new int[10];

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		setting();

		ans = Integer.MIN_VALUE;
		permutaion(0);

		System.out.println(ans);
	}

	private static void permutaion(int k) {
		if (k == 10) {
			setting();
			simulation();
//			sc.nextLine();
//			System.out.println(Arrays.toString(sel));
//			System.out.println(ans);
			return;
		}

		for (int i = 1; i <= 4; i++) {
			sel[k] = i;
			permutaion(k + 1);
		}
	}
	// 0: 점수 1: 말의 유무
//			route = new int[22][2]; // 2~40 and 출발지, 도착지
//			route10 = new int[][] { { 10, 0 }, { 13, 0 }, { 16, 0 }, { 19, 0 }, { 25, 0 } }; // 10 13 16 19 25
//			route20 = new int[][] { { 20, 0 }, { 22, 0 }, { 24, 0 }, { 25, 0 } }; // 20 22 24 25
//			route25 = new int[][] { { 25, 0 }, { 30, 0 }, { 35, 0 }}; // 25 30 35 40
//			route30 = new int[][] { { 30, 0 }, { 28, 0 }, { 27, 0 }, { 26, 0 }, { 25, 0 } }; // 30 28 27 26 25

	private static void simulation() {
		int result1 = 0, result2 = 0, result3 = 0, result4 = 0;
		int[][] curPos1 = route, curPos2 = route, curPos3 = route, curPos4 = route;
		int total = 0;
		for (int i = 0; i < 10; i++) {
			// 만약 말이 겹치는 경우는 끝
			int horseNum = sel[i];
			int value = dice[i];

			if (horseNum == 1) {
				curPos1[result1][1] = 0;
				result1 += value;
				if (curPos1 == route && result1 >= 21) {
					result1 = 21;
					continue;
				} else if (curPos1 == route10 && result1 > 4) {
					curPos1 = route25;
					result1 -= 4;
				} else if (curPos1 == route20 && result1 > 3) {
					curPos1 = route25;
					result1 -= 3;
				} else if (curPos1 == route25 && result1 > 3) {
					curPos1 = route;
					result1 = 21;
					continue;
				} else if (curPos1 == route25 && result1 == 3) {
					curPos1 = route;
					result1 = 20;
				} else if (curPos1 == route30 && result1 > 4) {
					curPos1 = route25;
					result1 -= 4;
				}

				if (curPos1[result1][1] != 0) {
					total = 0;
					break;
				}

				if (curPos1 == route && result1 == 5) {
					curPos1 = route10;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else if (curPos1 == route && result1 == 10) {
					curPos1 = route20;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else if (curPos1 == route && result1 == 15) {
					curPos1 = route30;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else if (curPos1 == route10 && result1 == 4) {
					curPos1 = route25;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else if (curPos1 == route20 && result1 == 3) {
					curPos1 = route25;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else if (curPos1 == route30 && result1 == 4) {
					curPos1 = route25;
					result1 = 0;
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				} else {
					curPos1[result1][1] = 1;
					total += curPos1[result1][0];
				}
			} else if (horseNum == 2) {
				curPos2[result2][1] = 0;
				result2 += value;

				if (curPos2 == route && result2 >= 21) {
					result2 = 21;
					continue;
				} else if (curPos2 == route10 && result2 > 4) {
					curPos2 = route25;
					result2 -= 4;
				} else if (curPos2 == route20 && result2 > 3) {
					curPos2 = route25;
					result2 -= 3;
				} else if (curPos2 == route25 && result2 > 3) {
					curPos2 = route;
					result2 = 21;
					continue;
				} else if (curPos2 == route25 && result2 == 3) {
					curPos2 = route;
					result2 = 20;
				} else if (curPos2 == route30 && result2 > 4) {
					curPos2 = route25;
					result2 -= 4;
				}

				if (curPos2[result2][1] != 0) {
					total = 0;
					break;
				} else if (curPos2 == route && result2 == 5) {
					curPos2 = route10;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else if (curPos2 == route && result2 == 10) {
					curPos2 = route20;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else if (curPos2 == route && result2 == 15) {
					curPos2 = route30;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else if (curPos2 == route10 && result2 == 4) {
					curPos2 = route25;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else if (curPos2 == route20 && result2 == 3) {
					curPos2 = route25;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else if (curPos2 == route30 && result2 == 4) {
					curPos2 = route25;
					result2 = 0;
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				} else {
					curPos2[result2][1] = 1;
					total += curPos2[result2][0];
				}
			} else if (horseNum == 3) {
				curPos3[result3][1] = 0;
				result3 += value;

				if (curPos3 == route && result3 >= 21) {
					result3 = 21;
					continue;
				} else if (curPos3 == route10 && result3 > 4) {
					curPos3 = route25;
					result3 -= 4;
				} else if (curPos3 == route20 && result3 > 3) {
					curPos3 = route25;
					result3 -= 3;
				} else if (curPos3 == route25 && result3 > 3) {
					curPos3 = route;
					result3 = 21;
					continue;
				} else if (curPos3 == route25 && result3 == 3) {
					curPos3 = route;
					result3 = 20;
				} else if (curPos3 == route30 && result3 > 4) {
					curPos3 = route25;
					result3 -= 4;
				}

				if (curPos3[result3][1] != 0) {
					total = 0;
					break;
				} else if (curPos3 == route && result3 == 5) {
					curPos3 = route10;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else if (curPos3 == route && result3 == 10) {
					curPos3 = route20;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else if (curPos3 == route && result3 == 15) {
					curPos3 = route30;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else if (curPos3 == route10 && result3 == 4) {
					curPos3 = route25;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else if (curPos3 == route20 && result3 == 3) {
					curPos3 = route25;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else if (curPos3 == route30 && result3 == 4) {
					curPos3 = route25;
					result3 = 0;
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				} else {
					curPos3[result3][1] = 1;
					total += curPos3[result3][0];
				}
			} else if (horseNum == 4) {
				curPos4[result4][1] = 0;
				result4 += value;

				if (curPos4 == route && result4 >= 21) {
					result4 = 21;
					continue;
				} else if (curPos4 == route10 && result4 > 4) {
					curPos4 = route25;
					result4 -= 4;
				} else if (curPos4 == route20 && result4 > 3) {
					curPos4 = route25;
					result4 -= 3;
				} else if (curPos4 == route25 && result4 > 3) {
					curPos4 = route;
					result4 = 21;
					continue;
				} else if (curPos4 == route25 && result4 == 3) {
					curPos4 = route;
					result4 = 20;
				} else if (curPos4 == route30 && result4 > 4) {
					curPos4 = route25;
					result4 -= 4;
				}

				if (curPos4[result4][1] != 0) {
					total = 0;
					break;
				} else if (curPos4 == route && result4 == 5) {
					curPos4 = route10;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else if (curPos4 == route && result4 == 10) {
					curPos4 = route20;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else if (curPos4 == route && result4 == 15) {
					curPos4 = route30;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else if (curPos4 == route10 && result4 == 4) {
					curPos4 = route25;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else if (curPos4 == route20 && result4 == 3) {
					curPos4 = route25;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else if (curPos4 == route30 && result4 == 4) {
					curPos4 = route25;
					result4 = 0;
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				} else {
					curPos4[result4][1] = 1;
					total += curPos4[result4][0];
				}
			}
		}
		ans = Math.max(total, ans);
	}

	private static void setting() {
//		for (int i = 0; i < 10; i++) {
//			copy_sel[i] = sel[i];
//		}
		// 0: 점수 1: 말의 유무
		route = new int[22][2]; // 2~40 and 출발지, 도착지
		route10 = new int[][] { { 10, 0 }, { 13, 0 }, { 16, 0 }, { 19, 0 }, { 25, 0 } }; // 10 13 16 19 25
		route20 = new int[][] { { 20, 0 }, { 22, 0 }, { 24, 0 }, { 25, 0 } }; // 20 22 24 25
		route25 = new int[][] { { 25, 0 }, { 30, 0 }, { 35, 0 } }; // 25 30 35 40
		route30 = new int[][] { { 30, 0 }, { 28, 0 }, { 27, 0 }, { 26, 0 }, { 25, 0 } }; // 30 28 27 26 25

		for (int i = 1; i <= 20; i++) {
			route[i][0] = i * 2;
		}
	}
}
