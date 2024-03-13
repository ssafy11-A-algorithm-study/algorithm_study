package problemSolving_homework.boj;

import java.io.*;
import java.util.*;

public class BOJ_3190_뱀 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		int[][]arr=new int[n][n];
		for (int i = 0; i < k; i++) {
			StringTokenizer st=new  StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			arr[r][c]=-1;
		}
		int l=Integer.parseInt(br.readLine());
		Queue<int[]> rotate=new ArrayDeque<int[]>();
		for (int i = 0; i < l; i++) {
			StringTokenizer st=new  StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			char dir=st.nextToken().toCharArray()[0];
			if(dir=='L') rotate.offer(new int[] {x,-1});//왼쪽 -1
			else rotate.offer(new int[] {x,+1});
		}
		int answer=0;
		int[]headPos=new int[] {0,0};//x,y
		int[]tailPos=new int[] {0,0};
		arr[0][0]=2;
		int nextDir=2;//오른쪽
		
		int []dx= new int[]{-1,0,1,0};
		int []dy= new int[]{0,1,0,-1};
		//arr에 뱀 방향 표시 1~4, 사과이면 -1이 들어 있다.
		while(true) {
			//몰길이 늘려 다음칸 가기
			int nX=headPos[0]+dx[nextDir-1];
			int nY=headPos[1]+dy[nextDir-1];
			//벽이나 몸에 부딪히는지?
			if(nX<0||nX>=n||nY<0||nY>=n)break;//벽에 부딪히면
			if(arr[nX][nY]>0)break;//몸이랑 부딪히면
			//움직인 머리 자리에 사과가 없으면
			if(arr[nX][nY]!=-1) {
				int prevX=tailPos[0];
				int prevY=tailPos[1];
				int prevDir=arr[prevX][prevY];
				arr[prevX][prevY]=0;
				tailPos[0]+=dx[prevDir-1];
				tailPos[1]+=dy[prevDir-1];
				
			}
			answer++;
			//뱀 회전
			if(!rotate.isEmpty()) {
				int []currRotate=rotate.peek();
				if(currRotate[0]==answer) {
					rotate.poll();
					nextDir+=currRotate[1];
					if(nextDir==5) nextDir=1;
					else if(nextDir==0)nextDir=4;
				}
			}
			
			arr[nX][nY]=nextDir;
			headPos[0]=nX;
			headPos[1]=nY;
		}
		System.out.println(answer+1);
	}
	private static void print(int[][]arr) {
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}System.out.println();
		}
	}
}
/*
 * 1초 128mb n<100
 * 사과 먹으면 길이가 늘어남-> 머리를 먼저 움직이고 사과가 있으면 그대로, 없으면 몸을 한칸 삭제
 * x초후에 c방향으로 회전 
 * 최소 최대가 아님. 시뮬
 * 맵에 다음 이동 방향을 저장하면서 다음 꼬리를 계산
 */