package jiwon;

import java.util.*;
import java.io.*;

public class SolutionBaekJoon3190 {
	
	static class Command {
		int time;
		String direction;
		
		Command(int time, String direction) {
			this.time = time;
			this.direction = direction;
		}
	}
	
	static class Snake {
		int headX;
		int headY;
		int tailX;
		int tailY;
		int direction;
		int length;
		
		Snake(int headX, int headY) {
			this.headX = headX;
			this.headY = headY;
			this.tailX = headX;
			this.tailY = headY;
			this.length = 1;
		}
	}
	
	static int[][] map;
	static Command[] commands;
	static Snake snake;
	static Queue<Integer> directions;  // 뱀의 이전 방향들
	static int time;
	// 우하좌상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		map = new int[N + 2][N + 2];
		Arrays.fill(map[0], -1);
		Arrays.fill(map[map.length - 1], -1);
		for (int i = 1; i < map.length - 1; i++) {
			map[i][0] = -1;
			map[i][map[0].length - 1] = -1;
		}
		
		// 사과 정보 입력받기
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		// 뱀의 방향 변환 정보 입력받기
		commands = new Command[L];
		for (int i = 0; i < commands.length; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			
			commands[i] = new Command(time, direction);
		}
		
		// 뱀 투입하기
		snake = new Snake(1, 1);
		map[snake.headX][snake.headY] = -1;
		
		// 뱀의 방향 기록하기
		directions = new ArrayDeque<>();
		directions.offer(snake.direction);
		
		// 게임이 언제 끝나는지 구하기
		time = 1;
		calculateResult();
		
		System.out.println(time);
	}
	
	public static void calculateResult() {
		int commandNumber = 0;
		Command command = commands[commandNumber];
		while (true) {
			int nextX = snake.headX + dx[snake.direction];
			int nextY = snake.headY + dy[snake.direction];
			if (map[nextX][nextY] == -1) {  // 벽이거나 자기 자신인 경우
				return;
			} else if (map[nextX][nextY] == 0) {  // 사과가 없는 경우
				map[snake.tailX][snake.tailY] = 0;
				
				int preDirection = directions.poll();
				snake.tailX += dx[preDirection];
				snake.tailY += dy[preDirection];
			}
			
			// 머리 늘리기
			snake.headX = nextX;
			snake.headY = nextY;
			map[snake.headX][snake.headY] = -1;
			snake.length++;
			
			// 방향 전환하기
			if (command != null && command.time == time) {
				if (command.direction.equals("L")) {  // 반시계 방향
					snake.direction = (snake.direction + dx.length - 1) % dx.length;
				} else {
					snake.direction = (snake.direction + 1) % dx.length;
				}
				
				commandNumber++;
				if (commandNumber == commands.length) {  // 더 이상 방향 전환 정보가 없는 경우
					command = null;
				} else {
					command = commands[commandNumber];
				}
			}
			directions.offer(snake.direction);  // 이전 방향 기록하기
			
			// 시간 늘리기
			time++;
		}
	}

}
