package jiwon._0313;

import java.util.*;
import java.io.*;

// 0: 흰색, 1: 빨간색, 2: 파란색
public class BOJ_17837_새로운게임2 {
	
	static class Horse {
		int number;
		int x;
		int y;
		int direction;
		
		Horse(int number, int x, int y, int direction) {
			this.number = number;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	static class Map {
		int color;
		Deque<Horse> horses;
		
		Map(int color) {
			this.color = color;
			horses = new ArrayDeque<>();
		}
	}
	
	static Map[][] map;
	static Horse[] horses;
	// 우좌상하
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 지도 정보 입력받기
		map = new Map[N + 2][N + 2];
		for (int i = 1; i < map.length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = new Map(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 말 정보 입력받기
		horses = new Horse[K + 1];
		for (int i = 1; i < horses.length; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			
			horses[i] = new Horse(i, x, y, direction);
			
			// 지도에 말 나타내기
			map[x][y].horses.offer(horses[i]);
		}
		
		// 게임 종료되는 턴의 번호 구하기
		int result = calculateResult();
		
		System.out.println(result);
	}
	
	public static int calculateResult() {
		int time = 1;
		
		while (time <= 1000) {
			// 말 움직이기
			for (int number = 1; number < horses.length; number++) {
				Horse horse = horses[number];
				
				// 말 움직이기
				int nextX = horse.x + dx[horse.direction];
				int nextY = horse.y + dy[horse.direction];
				
				if (map[nextX][nextY] == null || map[nextX][nextY].color == 2) {
					// 현재 말 방향 바꾸기
					if (horse.direction % 2 == 0) {
						horse.direction--;
					} else {
						horse.direction++;
					}
					
					nextX = horse.x + dx[horse.direction];
					nextY = horse.y + dy[horse.direction];
				}
				
				// 영역 밖인 경우
				if (map[nextX][nextY] == null) {
					continue;
				}
				
				if (map[nextX][nextY].color == 0)  {
					boolean isMove = false;
					Deque<Horse> dq = map[horse.x][horse.y].horses;
					int size = dq.size();
					
					Deque<Horse> nextDq = map[nextX][nextY].horses;
					for (int c = 0; c < size; c++) {
						Horse h = dq.poll();
						
						if (h == horse) {
							isMove = true;
						}
						
						if (isMove) {
							h.x = nextX;
							h.y = nextY;
							
							nextDq.offer(h);
						} else {
							dq.offer(h);
						}
					}
					
					if (nextDq.size() >= 4) {
						return time;
					}
				} else if (map[nextX][nextY].color == 1) {
					boolean isMove = true;
					Deque<Horse> dq = map[horse.x][horse.y].horses;
					int size = dq.size();
					
					Deque<Horse> nextDq = map[nextX][nextY].horses;
					for (int c = 0; c < size; c++) {
						Horse h = dq.pollLast();
						
						if (isMove) {
							h.x = nextX;
							h.y = nextY;
							
							nextDq.offer(h);
						} else {
							dq.offerFirst(h);
						}
						
						if (h == horse) {
							isMove = false;
						}
					}
					
					if (nextDq.size() >= 4) {
						return time;
					}
				}
			}
			
			// 시간 증가하기
			time++;
		}
		
		return -1;
	}

}
