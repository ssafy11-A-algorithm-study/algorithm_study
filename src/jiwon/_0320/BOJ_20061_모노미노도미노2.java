/*
 * 블록이 내려오는 것에 따라서 보드에서 게임을 진행해야 해서 시뮬레이션이라고 생각했습니다.
 * 그래서 블록 입력이 들어오면 각각의 맵에 대해 시뮬레이션을 진행하고, 다시 다음 블록을 입력받고 각각 반복했습니다.
 * 시뮬레이션은 아래 과정과 같이 진행했습니다.
 * 1. 2개의 맵(초록색/파란색)에 블록을 놓습니다
 * 2. 2개의 맵에서 지울 수 있는 블록을 지우고, 옮깁니다.
 * 3. 2개의 맵에서 연한 부분에 블록이 있으면 맨 마지막 행/열의 블록을 지우고, 옮깁니다.
 * 
 * 맵의 크기가 4X6/6X4로 작고 시뮬레이션 반복 횟수가 최대 10,000이기 때문에 충분하다고 생각했습니다.
 * 
 * 풀이할 때 주의사항은 해당 행/열의 블록을 지우고 다른 블록을 옮긴 후에 다음 행을 넘어가는 것이 아닌
 * 해당 행을 다시 반복해서 살펴봐야 한다는 것입니다.
 * 또한, 연한 부분에 블록이 있어서, 블록을 지우는 경우는 점수를 획득하지 못한다는 것입니다.
 * (이것 때문에 거의 40분을... 문제를 자세히 읽어보자^-^)
 */

package jiwon._0320;

import java.util.*;
import java.io.*;

public class BOJ_20061_모노미노도미노2 {
    
    static int t, x, y;
    static boolean[][] greenMap;
    static boolean[][] blueMap;
    static int score;
    static int greenCount, blueCount;
    // 아오
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        greenMap = new boolean[6][4];
        blueMap = new boolean[4][6];
        greenCount = 0;
        blueCount = 0;
        score = 0;
        for (int i = 0; i < N; i++) {
            // 블록 정보 입력받기
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            
            calculateResult();
        }
        
        System.out.println(score);
        System.out.println(greenCount + blueCount);
    }
    
    public static void calculateResult() {
        for (int color = 0; color < 2; color++) {
            // 블록 쌓기
            createBlock(color);
            
            // 블록 지우기
            removeBlock(color);
            
            // 색이 연한 부분 처리하기
            special(color);
        }
    }
    
    // green: 0, blue: 1
    public static void createBlock(int color) {
        // 처음 위치 구하기
        boolean[][] map;
        int curX, curY;
        if (color == 0) {
            curX = 0;
            curY = y;
            map = greenMap;
            
            if (t == 1) {
            	greenCount++;
            } else {
            	greenCount += 2;
            }
        } else {
            curX = x;
            curY = 0;
            map = blueMap;
            
            if (t == 1) {
            	blueCount++;
            } else {
            	blueCount += 2;
            }
        }
        
        // 블록 위치 정하기
        aa: while (true) {
            if (curX + dx[color] == map.length || curY + dy[color] == map[0].length) {
                break;
            }
            
            switch (t) {
                case 1:
                    if (curX + dx[color] == map.length || curY + dy[color] == map[0].length || map[curX + dx[color]][curY + dy[color]]) {
                        break aa;
                    }
                    curX += dx[color];
                    curY += dy[color];
                    break;
                case 2:
                    if (curX + dx[color] == map.length || curY + dy[color] + 1 == map[0].length || map[curX + dx[color]][curY + dy[color]] || map[curX + dx[color]][curY + 1 + dy[color]]) {
                        break aa;
                    }
                    curX += dx[color];
                    curY += dy[color];
                    break;
                case 3:
                    if (curX + 1 + dx[color] == map.length || curY + dy[color] == map[0].length || map[curX + dx[color]][curY + dy[color]] || map[curX + 1 + dx[color]][curY + dy[color]]) {
                        break aa;
                    }
                    curX += dx[color];
                    curY += dy[color];
                    break;
            }
        }
        
        // 블록 표시하기
        switch (t) {
            case 1:
                map[curX][curY] = true;
                break;
            case 2:
                map[curX][curY] = true;
                map[curX][curY + 1] = true;
                break;
            case 3:
                map[curX][curY] = true;
                map[curX + 1][curY] = true;
                break;
        }
    }
    
    public static void removeBlock(int color) {
        boolean[][] map;
        if (color == 0) {
            map = greenMap;
        } else {
            map = blueMap;
        }
        
        if (color == 0) {
            aa: for (int x = 5; x > 1; x--) {
                for (int y = 0; y <= 3; y++) {
                    if (!map[x][y]) {
                        continue aa;
                    }
                    
                    if (y == 3) {
                        // 블록 지우기
                        for (int k = 0; k <= 3; k++) {
                        	if (map[x][k]) {
                        		map[x][k] = false;
                        		greenCount--;
                        	}
                        }
                        score++;
                        
                        // 블록 이동하기
                        moveBlock(0, x);
                        
                        x++;
                    }
                }
            }
        } else {
            aa: for (int y = 5; y > 1; y--) {
                for (int x = 0; x <= 3; x++) {
                    if (!map[x][y]) {
                        continue aa;
                    }
                    
                    if (x == 3) {
                        // 블록 지우기
                        for (int k = 0; k <= 3; k++) {
                        	if (map[k][y]) {
                        		map[k][y] = false;
                        		blueCount--;
                        	}
                        }
                        score++;
                        
                        // 블록 이동하기
                        moveBlock(1, y);
                        
                        y++;
                    }
                }
            }
        }
    }
    
    public static void moveBlock(int color, int start) {
        boolean[][] map;
        if (color == 0) {
            map = greenMap;
        } else {
            map = blueMap;
        }
        
        if (color == 0) {
            for (int x = start; x > 0; x--) {
                for (int y = 0; y <= 3; y++) {
                    map[x][y] = map[x - 1][y];
                    map[x - 1][y] = false;
                }
            }
        } else {
            for (int y = start; y > 0; y--) {
                for (int x = 0; x <= 3; x++) {
                    map[x][y] = map[x][y - 1];
                    map[x][y - 1] = false;
                }
            }
        }
    }
    
    public static void special(int color) {
        boolean[][] map;
        if (color == 0) {
            map = greenMap;
        } else {
            map = blueMap;
        }
        
        if (color == 0) {
            aa: for (int x = 1; x >= 0; x--) {
                for (int y = 0; y <= 3; y++) {
                    if (map[x][y]) {
                        // 블록 지우기
                        for (int k = 0; k <= 3; k++) {
                        	if (map[5][k]) {
                        		map[5][k] = false;
                        		greenCount--;
                        	}
                        }
                        
                        // 블록 이동하기
                        moveBlock(0, 5);
                        
                        x++;
                        continue aa;
                    }
                }
            }
        } else {
            aa: for (int y = 1; y >= 0; y--) {
                for (int x = 0; x <= 3; x++) {
                    if (map[x][y]) {
                        // 블록 지우기
                        for (int k = 0; k <= 3; k++) {
                        	if (map[k][5]) {
	                            map[k][5] = false;
	                            blueCount--;
                        	}
                        }
                        
                        // 블록 이동하기
                        moveBlock(1, 5);
                        
                        y++;
                        continue aa;
                    }
                }
            }
        }
    }

}