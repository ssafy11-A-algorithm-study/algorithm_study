package problemSolving_homework.boj;
import java.io.*;
import java.util.*;
public class BOJ_17779_게리맨더링2 {
	static int n,answer=Integer.MAX_VALUE;
	static int[][]arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//for 문으로 조합
		for (int x = 1; x <= n-2; x++) {
			for (int y =2 ; y <=n-1 ; y++) {
				for (int d1 = 1; d1 <= y-1; d1++) {
					for (int d2 = 1; d1+d2<=n-x&&y+d2<=n ; d2++) {
						//System.out.println("x:"+x+" y:"+y+" d1:"+d1+" d2:"+d2);
						//다 나눔
						answer=Math.min(answer, getPopulationDiff(x,y,d1,d2));
					}
				}
			}
		}
		System.out.println(answer);
	}
	private static int getPopulationDiff(int x, int y, int d1, int d2) {
		int[]sum=new int[5];
		int [][]temp=new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				//5번 구역을 4개로 나눠서
				//_|은 1번 구역에서 제외할 부분,,
				//1번 구역의 경우 각 행마다 i-x만큼의 제외할 부분이 생긴다
				//j<=y-(i-x)
				if(i<x+d1&&j<=y&&!(x<=i&&j>=y-(i-x))) {
					//1번
					temp[i][j]=1;
					sum[0]+=arr[i][j];
				}
				else if(i<=x+d2&&j<=n&&!(x<=i&&j<=y+(i-x))) {
					//2번
					temp[i][j]=2;

					sum[1]+=arr[i][j];
				}
				else if(x+d1<=i&&j<y-d1+d2&&!(i<=x+d1+d2&&y-d1-x-d1+i<=j)) {
					//3번
					temp[i][j]=3;

					sum[2]+=arr[i][j];
				}
				else if(x+d2<i&&j>=y-d1+d2&&!(i<=x+d1+d2&&y-d1+d2+x+d1+d2-i>=j)) {
					//4번
					temp[i][j]=4;

					sum[3]+=arr[i][j];
				}
				else {
					temp[i][j]=5;
					sum[4]+=arr[i][j];
				}
				
			}
		}
//		for (int i = 1; i <=n; i++) {
//			for (int j = 1; j <=n; j++) {
//				System.out.print(temp[i][j]+" ");
//			}
//			System.out.println();
//		}
		Arrays.sort(sum);
		
		return sum[4]-sum[0];
	}
}
/*
 * 1sec 512mb n<20
 * 다섯개의 선거구로 나누고 하나에 포함되어야한다
 * 
 * 한 선거구에서는 연결어야 하고, 선거구 끼리 연력 . union find 필요없음
 * 
 * 
 * 인구가 가장 많은 선거구와 가장 적은 선거구의 인구차이 최솟값. => 고만고만하게 구분
 * 
 * 알 방법이 없음. 조합 부르트포쓰
 * x,y d1, d2를 정하는데 
 * 
 * 인덱싱 처리가 까다로웠다.
 * 
 */