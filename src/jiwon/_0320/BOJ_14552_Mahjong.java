/*
 * 주어진 13개의 패에서 1개를 추가했을 때 완성되는 조합을 구하는 것이므로
 * 1~9까지의 패를 추가한 후, 14개의 패를 이용하여 완성되는 조합이 있는지 확인하는 완전 탐색이라고 생각했습니다.
 * <풀이>
 * 1. 패를 입력받아서, 패의 개수를 셉니다.
 * 2. 1~9까지 순서대로 살펴보며, 추가할 수 있으면 추가한 후 대기패가 되는지 아래 과정을 통해 확인합니다.
 * 2-1. 해당 숫자의 개수가 2개 이상이며 머리를 만든 적이 없으면, 머리를 만든 후 다음으로 넘깁니다.
 * 2-2. 해당 숫자의 개수가 3개 이상이면, 몸으로 만든 후 다음으로 넘깁니다.
 * 2-3. 해당 숫자가 7이하이며 해당 숫자를 시작으로 연속 3개가 있으면, 몸으로 만든 후 다음으로 넘깁니다.
 * 2-4. 2-1~3까지 해당 사항이 없으면, 숫자를 증가시킨 후 다음으로 넘어갑니다.
 * 2-5. 모든 숫자를 다 살펴보거나, 대기패가 되면 그만 살펴봅니다.
 */

package jiwon._0320;

import java.util.*;
import java.io.*;

public class BOJ_14552_Mahjong {
	
	static final int N = 10;
	static int[] counts;
	static boolean[] isHead;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 숫자 입력받기
		counts = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			counts[Integer.parseInt(st.nextToken())]++;
		}
		
		// 대기패 구하기
		sb = new StringBuilder();
		calculateResult();
		
		System.out.println(sb.toString() == "" ? -1 : sb);
	}
	
	public static void calculateResult() {
		for (int i = 1; i < N; i++) {
			if (counts[i] == 4) {
				continue;
			}
			
			counts[i]++;
			isHead = new boolean[N];
			
			// 대기패인지 확인
			if (validateResult(1, 0, 0)) {
				sb.append(i + " ");
			}
			
			counts[i]--;
		}
	}
	
	public static boolean validateResult(int num, int head, int body) {
		// 모든 숫자를 둘러본 경우
		if (num == N) {
			// 머리 1 + 몸통 4 or 머리 7
			if ((head == 1 && body == 4) || (head == 7 && body == 0)) {
				return true;
			}
			return false;
		}
		
		// 머리 1 + 몸통 4 or 머리 7
		if ((head == 1 && body == 4) || (head == 7 && body == 0)) {
			return true;
		}
		
		// 해당 숫자 포함해서 머리 만들기
		if (!isHead[num] && counts[num] >= 2) {
			counts[num] -= 2;
			isHead[num] = true;
			
			boolean isResult = validateResult(num, head + 1, body);
			
			counts[num] += 2;
			isHead[num] = false;
			
			if (isResult) {
				return isResult;
			}
		}
		
		// 해당 숫자 포함해서 몸통 만들기
		if (body < 4) {
			if (counts[num] >= 3) {  // 같은 패 3개
				counts[num] -= 3;
				
				boolean isResult = validateResult(num, head, body + 1);
				
				counts[num] += 3;
				
				if (isResult) {
					return isResult;
				}
			} else if (num <= 7 && (counts[num] > 0 && counts[num + 1] > 0 && counts[num + 2] > 0)) {  // 연속된 숫자 3개
				counts[num]--;
				counts[num + 1]--;
				counts[num + 2]--;
				
				boolean isResult = validateResult(num, head, body + 1);
				
				counts[num]++;
				counts[num + 1]++;
				counts[num + 2]++;
				
				if (isResult) {
					return isResult;
				}
			}
		}
		
		return validateResult(num + 1, head, body);
	}

}
