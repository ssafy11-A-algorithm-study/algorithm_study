package problemSolving_homework.boj;
import java.util.*;
import java.io.*;
public class BOJ_17837_새로운게임2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int [][][]arr=new int[n][n][5];//i,j 죄표 칸의 [색 몇번,말 배열]
		int[][]piece=new int[k][3];//행 열 방향
		for (int i = 0; i < n; i++) {
			//0은 흰색, 1은 빨간색, 2는 파란색
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j][0]=Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < k; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				piece[i][j]=Integer.parseInt(st.nextToken())-1;
			}
			arr[piece[i][0]][piece[i][1]][1]=i+1;
		}
		int count=0;
		int []dx=new int[] {0,0,-1,1};
		int []dy=new int[] {1,-1,0,0};
		
		boolean endFlag=false;//게임 종료 flag
		boolean cantMoveFlag=false;//가만히 있었는지 flag
		
		while(count<1000) {//1000이상이면 -1출력해야한다.
			
			for (int j = 0; j < piece.length; j++) {
				
				int currX=piece[j][0];
				int currY=piece[j][1];
				int nX=currX+dx[piece[j][2]];
				int nY=currY+dy[piece[j][2]];
				//만약 좌표를 넘어섰거나, 파랑 칸에 가려하거나
				if(nX<0||nX>=n||nY<0||nY>=n||arr[nX][nY][0]==2) {
					if(cantMoveFlag) {//그전에도 파랑칸이었다.=>가만히
						cantMoveFlag=false;
						continue;
					}
					//방향 전환 필요
					cantMoveFlag=true;
					piece[j][2]=piece[j][2]%2==0?piece[j][2]+1:piece[j][2]-1;
					j--;
					continue;
				}
				cantMoveFlag=false;
				
				//이동하기
				int prev=1;//현재 있는 칸에 밑에서 몇번째인지. 0은 색깔이기 때문에 1부터
				int moveCount=0;//내말 포함 몇개 말 옮겨야하는지
				int next=1;//다음 칸에 몇개의 말이 이미 있는지
				
				while(prev<5&&arr[currX][currY][prev]!=j+1) {
					prev++;
				}
				
				while(moveCount+prev<5&&arr[currX][currY][prev+moveCount]!=0) {
					moveCount++;
				}

				while(arr[nX][nY][next]!=0)next++;
				//next에 들어갈 수 있는거니까 next-1개 있음 
				
				
				if(next-1+moveCount>=4) {
					endFlag=true;//말 4개가 올라갈 예정=> 게임 종료
					break;
				}else {
					//옮겨주기
					switch(arr[nX][nY][0]) {
					case 0://흰
						for (int i = 0; i<moveCount; i++) {
							int idx=arr[currX][currY][prev+i];
							arr[nX][nY][next+i]=idx;
							arr[currX][currY][prev+i]=0;
							piece[idx-1][0]=nX;
							piece[idx-1][1]=nY;
							
						}
						break;
					case 1://빨				
//						int[] temp=Arrays.copyOf(arr[nX][nY], 5);
//						for (int i = 1; i <= 4; i++) {
//							temp[i]=arr[nX][nY][5-i];
//						}
//						arr[nX][nY]=temp;
						for (int i = 0; i<moveCount; i++) {
							int idx=arr[currX][currY][moveCount+prev-1-i];
							arr[nX][nY][next+i]=idx;
							arr[currX][currY][moveCount+prev-1-i]=0;
							piece[idx-1][0]=nX;
							piece[idx-1][1]=nY;
						}
						break;
					}
				}	
			}
			count++;
			if(endFlag)break;
			
		}
		if(count>=1000)System.out.println("-1");
		else System.out.println(count);
	}
}
/*
 * 0.5초 512mb n<12 백트래킹하면 dfs 가능
 * but 구현 문제. 말 4개 쌓이면 종료
 * 위에 있는 애들만 같이 가고 내 밑에 있는 말은 두고 감.
 * 체스판을 벗어나는 경우에는 파란색과 같은 경우이다
 * 즉 말마다 방향을 기억해야함.
 * 그럼 맵 좌표마다 길이 4의 
 * 턴이 1000보다 크면 -1 이걸 다 계산 해야겠지
 */