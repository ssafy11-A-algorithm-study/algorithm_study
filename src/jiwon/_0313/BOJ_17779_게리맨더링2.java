package jiwon._0313;

import java.util.*;
import java.io.*;

public class BOJ_17779_게리맨더링2 {
	
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static int[][] numbers;
	static int totalPeople;
	static int[] people;
	static int minDifference;
	static Position benchMark;
	static int d1, d2;
	// 상우좌하
	static int[] dx = {0, -1, 0, 0, 1};
	static int[] dy = {0, 0, 1, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 인구 수 입력받기
		map = new int[N + 2][N + 2];
		StringTokenizer st;
		for (int i = 1; i < map.length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map[0].length - 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalPeople += map[i][j];
			}
		}
		
		// 인구 차이의 최솟값 구하기
		minDifference = Integer.MAX_VALUE;
		benchMark = new Position(1, 1);
		calculateResult();
		
		System.out.println(minDifference);
	}
	
	public static void calculateResult() {
		d1 = 1;
		d2 = 1;
		while (benchMark.x + d1 + d2 <= N) {
			while (benchMark.x + d1 + d2 <= N && d1 < benchMark.y && d2 <= N - benchMark.y) {
				people = new int[6];
				
				// 선거구 나누기
				splitMap();
				
				// 선거구별 인구 수 계산하기
				calculatePeople();
				
				// 가장 많은 선거구와 가정 적은 선거구의 인구 차이 구하기
				calculateDifference();
				
				// 경계선 바꾸기
				d2++;
			}
			
			// 경계선 바꾸기
			d2 = 1;
			d1++;
		}
		
		if (benchMark.y < N) {
			benchMark.y++;
			
			calculateResult();
		} else if (benchMark.x < N) {
			benchMark.x++;
			benchMark.y = 1;

			calculateResult();
		}
	}
	
	public static void splitMap() {
		numbers = new int[map.length][map[0].length];
		
		// 5번 구역 경계션 표시하기
		int start = benchMark.y;
		int end = benchMark.y;
		
		for (int i = benchMark.x; i <= benchMark.x + d1 + d2; i++) {
			numbers[i][start] = 5;
			numbers[i][end] = 5;
			
			if (i < benchMark.x + d1) {
				start--;
			} else {
				start++;
			}
			
			if (i < benchMark.x + d2) {
				end++;
			} else {
				end--;
			}
		}
		
		// 1~4번 구역 경계선 표시하기
		for (int number = 1; number <= 4; number++) {
			// 시작점 구하기
			int x = 0;
			int y = 0;
			switch (number) {
				case 1:
					x = benchMark.x;
					y = benchMark.y;
					break;
				case 2:
					x = benchMark.x + d2;
					y = benchMark.y + d2;
					break;
				case 3:
					x = benchMark.x + d1;
					y = benchMark.y - d1;
					break;
				case 4:
					x = benchMark.x + d1 + d2;
					y = benchMark.y - d1 + d2;
					break;
			}
			x += dx[number];
			y += dy[number];
			
			while (map[x][y] != 0) {
				numbers[x][y] = number;
				people[number] += map[x][y];
				
				x += dx[number];
				y += dy[number];
			}
		}
	}
	
	public static void calculatePeople() {
		int number = 1;
		int otherPeople = 0;
		
		// 1
		int x = 1, y = 1;
		while (x < benchMark.x + d1) {
			people[number] += map[x][y];
			
			if (numbers[x][y + 1] != 0) {
				x++;
				y = 0;
			} else {
				y++;
			}
		}
		otherPeople += people[number];
		
		// 2
		number++;
		x = 1;
		y = N;
		while (x < benchMark.x + d2) {
			people[number] += map[x][y];
			
			if (numbers[x][y - 1] != 0) {
				x++;
				y = N;
			} else {
				y--;
			}
		}
		otherPeople += people[number];
		
		// 3
		number++;
		x = N;
		y = 1;
		while (x > benchMark.x + d1) {
			people[number] += map[x][y];
			
			if (numbers[x][y + 1] != 0) {
				x--;
				y = 1;
			} else {
				y++;
			}
		}
		otherPeople += people[number];
		
		// 4
		number++;
		x = N;
		y = N;
		while (x > benchMark.x + d2) {
			people[number] += map[x][y];
			
			if (numbers[x][y - 1] != 0) {
				x--;
				y = N;
			} else {
				y--;
			}
		}
		otherPeople += people[number];
		
		// 5
		people[5] = totalPeople - otherPeople;
	}
	
	public static void calculateDifference() {
		Arrays.sort(people);
		int difference = people[people.length - 1] - people[1];
		
		minDifference = Math.min(minDifference, difference);
	}

}
