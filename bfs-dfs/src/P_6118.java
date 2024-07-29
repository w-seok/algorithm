import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * start: 2024-07-29 18:15
 * end: 2024-07-29 19:05
 * 시간복잡도: O(nm)..?
 * 공간복잡도: O(n)
 * 풀이: 결국에는 전체 다 가봐야 각 헛간의 길이를 알수있기때문에 bfs로 탐색 이후 각 헛간별 거리가 정해지면 제일 큰 길이랑 index 구하기위해서 for문 돌면서 최대값, keep 값 비교하고 cnt 체크
 */
public class P_6118 {

	static int N;
	static int M;
	static ArrayList<Integer>[] arr;
	static int[] dist;
	static boolean[] vis;
	static LinkedList<Integer> queue = new LinkedList<>();

	static int max = 0; // 제인 긴 거리
	static int keep = 0; // 제일 마지막 index
	static int cnt = 0; // 가장긴게 몇개?

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());

			arr = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				arr[i] = new ArrayList<>();
			}

			dist = new int[N + 1];
			vis = new boolean[N + 1];

			for (int i = 0; i < M; i++) {
				tk = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tk.nextToken());
				int y = Integer.parseInt(tk.nextToken());
				arr[x].add(y);
				arr[y].add(x);
			}

			queue.add(1);
			vis[1] = true;

			while (!queue.isEmpty()) {
				int current = queue.poll();

				for (Integer i : arr[current]) {
					if (!vis[i]) {
						vis[i] = true;
						dist[i] = dist[current] + 1;
						queue.add(i);
					}
				}
			}

			for (int i = 1; i < dist.length; i++) {
				if (dist[i] > max) {
					max = dist[i];
					keep = i;
					cnt = 1;
				} else if (dist[i] == max) {
					if (i <= keep) {
						keep = i;
					}
					cnt +=1;
				}
			}

			System.out.println(keep + " " + max + " " + cnt);
		}
	}
}
