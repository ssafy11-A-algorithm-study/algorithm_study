package seungjun._0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 같은거 2개 => 머리, 같은거 3개, 1차이나는거 3개 => 몸통
// 완성패는 머리 7개, 머리 1개 몸총 4개

public class BOJ_14552_Mahjong {
	static int[] arr;
	static int head, body;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new int[14];

		head = 0;
		body = 0;

		for (int i = 0; i < 13; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] copy_arr = new int[14];
		boolean[] v = new boolean[14];

		for (int i = 1; i <= 9; i++) {
			arr[13] = i;
			for (int j = 0; j < 14; j++) {
				copy_arr[j] = arr[j];
				v[j] = false;
			}
			Arrays.sort(copy_arr);
			System.out.println(Arrays.toString(copy_arr));

			// i가 대기패인지 확인
			recursive(0, copy_arr, v);
		}

	}

	private static void recursive(int idx, int[] copy_arr, boolean[] v) {

	}

}
