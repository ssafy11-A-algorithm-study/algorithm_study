package homework0217;

import java.io.*;
import java.util.*;

public class 어른_상어{
    static int n,m,k;
    static int sharkCount;
    static Shark[] shark;
    static int [][]map;// 향기 표시.[i][j]=상어인덱스 
    //생각해보니 상어는 담을 필요 없음.
    //한번에 다 같이 움직이기 때문에 동시에 들어가는 경우 말고는 겹치지 않음
    static Queue <Shark>smell;
    static int[]dx=new int[]{-1,1,0,0};
    static int[]dy=new int[]{0,0,-1,1};
    public static void main(String[] args) throws Exception {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());
            //initialize
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
            shark= new Shark[m+1];//0은 비어있음
            sharkCount=m;
            map=new int[n][n];
            smell=new ArrayDeque<>();
            //input
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int tmp=Integer.parseInt(st.nextToken());    
                    if(tmp>0){
                      shark[tmp]=new Shark(i, j, 0,null);
                      map[i][j]=tmp;
                    }else map[i][j]=0;
                }
            }
            st=new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
              int tmp=Integer.parseInt(st.nextToken());    
              shark[i].currDir=tmp-1;
            }
            for (int s = 1; s <= m; s++) {
              int [][]prioirtyDir=new int[4][4];
              for (int i = 0; i < 4; i++) {
                st=new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                  prioirtyDir[i][j]=Integer.parseInt(st.nextToken())-1;
                }
              }
              shark[s].dir=prioirtyDir;
            }
            int count=0;
            sharkCount=m;
            while(count++<1000) {
                simul();
                if(sharkCount==1)break;
            }
            if(count==1001)System.out.println(-1);
            else System.out.println(count);
    }
    static void simul(){
      //상어 이동
      boolean [][]alreadyIn=new boolean[n][n];
      for (int i = 1; i <m; i++) {
        boolean nextSharkFlag=false;
        if(shark[i]==null)continue;
        int currX=shark[i].x;
        int currY=shark[i].y;
        //냄새 남기기
        smell.offer(new Shark(currX, currY, k));
        map[currX][currY]=i; //map에 향기를 남긴 상어 인덱스 표시
        //방향 잡기
        int j = 0;
        int dIdx=0,nX,nY;
        int mySmellDir=0;
        for (; j < 4; j++) {
          dIdx=shark[i].dir[shark[i].currDir][j];
          nX=currX+dx[dIdx];
          nY=currY+dy[dIdx];
          if(nX<0||nX>=n||nY<0||nY>=n)continue;
          if(map[nX][nY]==i&mySmellDir==0)mySmellDir=dIdx;//내 냄새 방향 기억하기
          if(alreadyIn[nX][nY]){//이미 더 큰 상어가 있다->먹힘
            sharkCount--;
            nextSharkFlag=true;
            shark[i]=null;
            break;
          }
          if(map[nX][nY]==0)break;
        }
        if(nextSharkFlag)continue;
        if(j==4){//갈 곳이 없음->내 냄새 가기
          dIdx=mySmellDir;
        }
        nX=currX+dx[dIdx];
        nY=currY+dy[dIdx];
        shark[i].x=nX;
        shark[i].y=nY;
        shark[i].currDir=dIdx;
        alreadyIn[nX][nY]=true;
      }
      //냄새 큐 돌면서 삭제하기
      int size=smell.size();
      while(size-->0){
        Shark s=smell.poll();
        s.time--;
        if(s.time==0){
          map[s.x][s.y]=0;
        }else {smell.add(s);
      }
  }
    
  }
  static class Shark{
        int x;
        int y;
        int currDir;
        int [][] dir;
        int time;
        public Shark(int x, int y, int currDir, int[][] dir) {
            super();
            this.x = x;
            this.y = y;
            this.currDir = currDir;
            this.dir = dir;
        }
        public Shark(int x, int y, int time) {
            super();
            this.x = x;
            this.y = y;
            this.time = time;
        }
        
    }
}
/*
 * 1초 512MB 2 ≤ N ≤ 20, 2 ≤ M ≤ N^2, 1 ≤ k ≤ 1,000
 * shark<m time<k
 * 가장 작은 번호==우선순위가 높은 상어를 제외하고 다 없어짐
 * 1. 아무 냄새가 없는 칸
 * 2. 모두 냄새가 있다면 자신의 냄새가 있는 칸
 * ...
 * 1번 상어만 남게 될때까지 몇초 걸리는지. => 구현
 * 1000초가 넘으면 -1 출력
 * 1 2 3 4 
 * 상 하 좌 우 
 * 
 * 상어 만큼 4*4 배열로 방향 저장, 맵에는 상어 인덱스 저장, 상어 클래스 배열 .
 * 굳이 우선순위 큐가 필요하진 않음.
 * 
 * 맵에 향기가 몇초 남았는지 표시하는데, 시간이 지나면 일일히 -1 해야함. 20*20이라 400. 순회 할만한가?
 * 순회가 아닌 다른 방법은? 향기가 있는 좌표 큐에 담아서 매 시간 큐만 돌기.-> 이방법 채택
 * 상어 이동-> 향기 생성 ->다음 이동 -> k번 후 없어짐
 * */
