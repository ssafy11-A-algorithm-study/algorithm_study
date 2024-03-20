import java.util.*;
import java.io.*;

public class Main {
    static boolean[][]green=new boolean[6][4];
    static boolean[][]blue=new boolean[6][4];
    static int answer=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int type=Integer.parseInt(st.nextToken());//1,ㅡ, ㅣ
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            simul(type,x,y);
//            System.out.println(i+"번째 type: "+type);
//            System.out.println("green");
//            print(green);System.out.println();
//            System.out.println("blue");
//            print(blue);
        }
        
        int block=0;
        for (int i = 2; i <=5; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j])block++;
				if(blue[i][j])block++;
			}
		}
        System.out.println(answer);
        System.out.println(block);
    }
    private static void print(boolean[][]map) {
    	
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
    private static void simul(int type, int x, int y) {
        switch(type) {
        case 1: //1*1
        	moveOne(y,green);
        	moveOne(x,blue);
        	break;
        case 2: //1*2
        	moveHori(y,green);
        	moveVert(x,blue);
        	break;
        case 3: //2*1
        	moveVert(y,green);
        	moveHori(x,blue);
        	break;
        }
        
        //점수 계산        
        eraseFullLine(green);
        eraseFullLine(blue);
    }
    private static void eraseFullLine(boolean[][]map) {
    	int[]haveTogo=new int[6];//각 행마다 몇칸 밑으로 내려가야하는지
		for (int i = 5; i >=0 ; i--) {
			boolean isFullFlag=true;
			boolean isBlockFlag=false;
            for (int j = 0; j < 4; j++) {
            	if(!map[i][j])isFullFlag=false;// 비어있는 칸이 있으면 삭제안함
            	if(map[i][j])isBlockFlag=true;//0,1에 블록이있는지
            	
            }
            
            if(isFullFlag) { //i번째 줄 지워야함
            	answer++;
            	map[i]=new boolean[4];
            	for (int j = i-1; j >=0; j--) {
					haveTogo[j]++;
				}
            }else if(i<2&&isBlockFlag&&haveTogo[i]<2-i){
            	//0,1 번째가 블록이 있고 삭제되지 않으며,삭제될 애들을 땡겨도 자리를 차지하면
            	//그만큼 뒤 삭제후 땡기기
            	for (int j = i; j<6; j++) {
					haveTogo[j]+=2-i-haveTogo[j];
				}
            }            
        }
		for (int i = 5; i>=0; i--) {
			if(haveTogo[i]==0)continue;
			if(haveTogo[i]+i>5)continue;// 범위를 넘어섬-> 삭제되는 열
			map[i+haveTogo[i]]=Arrays.copyOf(map[i], 4);
		}
		map[0]=new boolean[4];
		map[1]=new boolean[4];
	}
    private static void moveOne(int y,boolean[][]map) {
        int i=2;
        for (; i <= 5; i++) {
            if(map[i][y]) break;
        }
        map[i-1][y]=true;
    }
    private static void moveHori(int y,boolean[][]map) {//blue ㅡ 처리, green ㅣ처리
    	
    	int i = 2;
    	outer:	
        for (; i <=5 ; i++) {
            for (int j = y; j < y+2; j++) {
                if(map[i][j])break outer;
            }
        }
    	map[i-1][y]=true;
    	map[i-1][y+1]=true;
        
    }
    private static void moveVert(int y,boolean[][]map) {//blue ㅣ처리, green ㅡ 처리
    	int i = 2;
    	outer:	
        for (; i <=5 ; i++) {
            if(map[i][y])break outer;
        }
    	map[i-1][y]=true;
    	map[i-2][y]=true;
    }
}
/* 
 * n<10000  1sec 512MB
 * 구현. 마지막 결과를 계산
 * 보드는 주어짐.
 * 초록: 행 노 상관, 파랑: 열 노 상관
 */
