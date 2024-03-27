/*
 * 문제가 사이클이 완성하는 순간 게임을 종료하고, 게임을 종료하는 순간을 구하는 문제이므로
 * 서로소 집합을 활용하여 선분 정보를 입력받으면서 선분으로 이어지는 정점를 같은 부분 집합에 넣었습니다.
 * 그리고 나서, 선분으로 이어지는 정점이 이미 같은 부분 집합에 있으면 사이클이 형성됩니다.
 * 
 * ※ 같은 부분 집합으로 만들어주기 위해서 부모를 갱신할 때
 *   parents[y] = xParent;로 해서 틀리는 실수를 했습니다...ㅠㅠ
 */

package jiwon._0327;

import java.util.*;
import java.io.*;

public class BOJ_20040_사이클게임 {
	
	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int result = 0;
		makeSet();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (unionSet(x, y)) {
				result = i;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static void makeSet() {
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	public static int findParent(int n) {
		if (n == parents[n]) {
			return parents[n];
		} else {
			return parents[n] = findParent(parents[n]);
		}
	}
	
	public static boolean unionSet(int x, int y) {
		int xParent = findParent(x);
		int yParent = findParent(y);
		
		if (xParent == yParent) {
			return true;
		} else {
			parents[yParent] = xParent;
			return false;
		}
	}

}
