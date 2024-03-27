/*
 * N이 최대 100,000으로 100,000개의 요소를 정렬하면 0.1초라는 시간 제한을 지키기 못하기 때문에,
 * 우선순위 큐를 활용하여 가운데에 위치하는 요소를 구해야합니다.
 * 가운데에 위치한 요소를 찾기 위해서 우선순위 큐를 2개 사용했습니다.
 * 1개(less)는 가운데를 기준으로 왼쪽 즉, 가운데보다 작은 숫자를 저장하고,
 * 다른 1개(more)는 가운데를 기준으로 오른쪽 즉, 가운데보다 큰 숫자를 저장합니다.
 * less라는 우선순위 큐는 내림차순으로 하여 큐에서 요소를 가장 큰 것을 꺼내고,
 * more라는 우선순위 큐는 오름차순으로 하여 큐에서 요소를 가장 작은 것을 꺼냅니다.
 * 
 * <풀이 과정>
 * 1. more에 요소가 없거나, 입력받은 요소가 more에서 꺼낸 요소보다 크면 more에 넣고
 *    아니면 less에 넣습니다.
 * 2. 입력받은 요소가 홀수 번쨰이면 more와 less의 크기를 비교하여 더 많은 요소를 가진
 *    우선순위 큐의 요소가 가운데입니다.
 *    ∵ 홀수 번째에는 가운데 요소가 있는 쪽에 더 요소 1개가 많기 때문입니다.
 * 3. 입력받은 요소가 짝수 번째이면 more와 less의 크기를 비교하여 더 많은 요소를 가진
 *    우선순위 큐에서 요소를 꺼내서 적은 우선순위 큐에 넣습니다.
 *    ∵ 짝수 번째에는 more와 less의 크기가 같아야 하기 때문입니다.
 *    → more와 less에서 요소를 꺼내서 비교하여 더 작은 숫자가 가운데입니다.
 */

package jiwon._0327;

import java.util.*;
import java.io.*;

public class BOJ_1655_가운데를말해요 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> less = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> more = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		less.offer(n);
		sb.append(n + "\n");
		for (int i = 2; i <= N; i++) {
			n = Integer.parseInt(br.readLine());
			
			if (more.peek() != null && n > more.peek()) {
				more.offer(n);
			} else {
				less.offer(n);
			}
			
			if (i % 2 == 0) {
				if (less.size() > more.size()) {
					more.offer(less.poll());
				} else if (less.size() < more.size()) {
					less.offer(more.poll());
				}
				
				if (less.peek() < more.peek()) {
					sb.append(less.peek() + "\n");
				} else {
					sb.append(more.peek() + "\n");
				}
			} else {
				if (less.size() > more.size()) {
					sb.append(less.peek() + "\n");
				} else {
					sb.append(more.peek() + "\n");
				}
			}
		}
		
		System.out.print(sb);
	}

}
