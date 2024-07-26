import java.util.*;
import java.io.*;

/**
 * start: 2024-07-26 08:10
 * end: 2024-07-26 09:10
 * 시간복잡도: O(nm)
 * 공간복잡도: O(nm)
 * 풀이: 지점이 2인 지점에서(거리 0으로 초기화) 1인(거리 -1로 초기화)지점까지의 거리 bfs로 구함
 * scanner를 쓰니 시간초과가 발생해서 BufferedReader로 바꿈
 */
public class P_14940 {

	public static void main(String[] args) throws IOException {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			int[][] map = new int[n][m];
			int[][] dist = new int[n][m];

			LinkedList<int[]> linkedList = new LinkedList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 2) {
						dist[i][j] = 0;
						linkedList.add(new int[]{i, j});
					} else if (map[i][j] == 0) {
						dist[i][j] = 0;
					} else if(map[i][j] == 1) {
						dist[i][j] = -1;
					}
				}
			}

			while (!linkedList.isEmpty()) {
				int[] current = linkedList.poll();
				int x = current[0];
				int y = current[1];

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx >= 0 && nx < n && ny >= 0 && ny < m && dist[nx][ny] == -1) {
						dist[nx][ny] = dist[x][y] + 1;
						linkedList.add(new int[]{nx, ny});
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(dist[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
