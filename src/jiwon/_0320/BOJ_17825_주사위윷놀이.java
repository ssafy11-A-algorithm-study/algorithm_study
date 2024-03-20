/*
 * 주사위 결과 10개에 따라 4개의 말을 움직이는 것이므로
 * 중복 순열로 각각의 주사위 결과에 어떤 말을 움직일 것인지 결정한 후,
 * 모든 경우를 살펴보며 점수의 최댓값을 구하면 된다고 생각했습니다.
 * 하지만, 중복 순열로 각각의 주사위 결과에 어떤 말을 움직일 것인지 결정하는 것이 어려워서 이 방법은 포기했습니다ㅠㅠ
 * 
 * 주사위 10개의 결과에 4개의 말을 모두 시도해보는 완전 탐색을 생각했습니다.
 * 주사위는 10개이고 말은 4개 이므로 모든 경우의 수인 4의 10제곱을 시도해도 시간 초과는 나지 않기 때문에 이 방법을 선택했습니다.
 * 따라서, 주사위 결과마다 말 4개를 다 시도해보며 점수의 최댓값을 계산했습니다.
 * 말 4개를 저장하기 위해서 말을 나타내는 Horse 객체의 배열을 생성했습니다.
 * 또한, 조건을 맞추기 위해서 아래와 같이 추가했습니다.
 * 1. 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 합니다.
 *    : 이를 위해서 Horse라는 객체에 isBlue라는 멤버 변수를 만들었습니다.
 *      해당 변수를 사용하여, 파란색 칸에 도착하면 다음에 움직일 때에는 파란색 루트를 타야한다는 것을 표시했습니다.
 * 1-1. 파란색 루트에서 30이라는 숫자가 두 가지 있습니다.
 *      하나는 30 → 35로 이동하고, 다른 하나는 30 → 28로 이동합니다.
 *      이러한 경우를 구분하기 위해서 Horse라는 객체에 isUp이라는 멤버 변수를 만들었습니다.
 *      해당 변수의 값이 true이면 30 → 35로 이동하고, false이면 30 → 28로 이동합니다.
 * 2. 이동을 마치는 칸에 다른 말이 있으면 그 말은 고르지 않습니다.
 *    : 이를 위해서 Horse라는 객체에 equals(Object) 메소드를 오버라이딩하여
 *      현재 칸 숫자(num)랑 파란 루트인지 아닌지(isBlue)에 따라 객체를 비교하도록 하였습니다.
 * 
 * 코드 실행 결과를 살펴보니, 모든 경우에 말 정보를 나타내는 배열을 복사하기 때문에 메모리(103,100KB)가 상당히 높게 나왔습니다...
 */

package jiwon._0320;

import java.util.*;
import java.io.*;

public class BOJ_17825_주사위윷놀이 {
	
	static class Horse {
		int num;
		boolean isBlue;
		boolean isUp;
		
		Horse copy() {
			Horse h = new Horse();
			h.num = this.num;
			h.isBlue = this.isBlue;
			h.isUp = this.isUp;
			
			return h;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Horse) {
				Horse h = (Horse)obj;

				if (this.num == h.num && this.isBlue == h.isBlue && this.isUp == h.isUp) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	static int[] dices;
	static int maxResult;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 주사위 숫자 입력받기
		dices = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < dices.length; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		
		// 점수의 최댓값 구하기
		maxResult = Integer.MIN_VALUE;
		Horse[] horses = new Horse[4];
		Arrays.fill(horses, new Horse());
		calculateMaxResult(0, 0, horses);
		
		System.out.println(maxResult);
	}
	
	public static void calculateMaxResult(int result, int diceNum, Horse[] horses) {
		if (diceNum == dices.length) {
			maxResult = Math.max(maxResult, result);
			return;
		}
		
		for (int i =0; i < horses.length; i++) {
			Horse[] copiedHorses = copyHorses(horses);
			
			// 이미 도착한 말인 경우
			if (horses[i].num > 40) {
				continue;
			}
			
			// 말 이동시키기
			if (copiedHorses[i].isBlue) {
				for (int c = 0; c < dices[diceNum]; c++) {
					// 말이 이미 도착한 경우
					if (copiedHorses[i].num > 40) {
						break;
					}
					
					if (copiedHorses[i].num == 10 || copiedHorses[i].num == 13 || copiedHorses[i].num == 16) {
						copiedHorses[i].num += 3;
					} else if (copiedHorses[i].num == 19) {
						copiedHorses[i].num += 6;
					} else if (copiedHorses[i].num == 20 || copiedHorses[i].num == 22) {
						copiedHorses[i].num += 2;
					} else if (copiedHorses[i].num == 24) {
						copiedHorses[i].num++;
					} else if (copiedHorses[i].num == 26 || copiedHorses[i].num == 27 || copiedHorses[i].num == 28) {
						copiedHorses[i].num--;
					} else if (copiedHorses[i].num == 30) {
						if (copiedHorses[i].isUp) {
							copiedHorses[i].num += 5;
							copiedHorses[i].isUp = false;
						} else {
							copiedHorses[i].num -= 2;
						}
					} else if (copiedHorses[i].num == 25 || copiedHorses[i].num == 35) {
						if (copiedHorses[i].num == 25) {
							copiedHorses[i].isUp = true;
						}
						
						copiedHorses[i].num += 5;
					} else {
						copiedHorses[i].num++;
					}
				}
			} else {
				copiedHorses[i].num += 2 * dices[diceNum];
				
				// 파란색 지점인지 확인하기
				if (copiedHorses[i].num % 10 == 0) {
					copiedHorses[i].isBlue = true;
				}
			}
			
			// 다음으로 넘어가기
			if (copiedHorses[i].num > 40) {
				calculateMaxResult(result, diceNum + 1, copiedHorses);
			} else {
				if (checkDuplicatedHorse(copiedHorses, i)) {  // 도착한 칸에 이미 말이 있지 않은 경우
					calculateMaxResult(result + copiedHorses[i].num, diceNum + 1, copiedHorses);
				}
				
			}
		}
	}
	
	public static Horse[] copyHorses(Horse[] horses) {
		Horse[] copiedHorses = new Horse[horses.length];
		
		for (int i = 0; i < horses.length; i++) {
			copiedHorses[i] = horses[i].copy();
		}
		
		return copiedHorses;
	}
	
	public static boolean checkDuplicatedHorse(Horse[] horses, int num) {
		for (int i = 0; i < horses.length; i++) {
			if (i == num) {
				continue;
			}
			
			if (horses[num].equals(horses[i])) {
				return false;
			}
		}
		
		return true;
	}

}
