import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * start: 2024-07-30 12:54
 * end: 2024-07-31 10:14
 * 시간복잡도:
 * 공간복잡도:
 * 풀이:
 *
 */
public class P_16954 {

	static char[][] map = new char[8][8];
	// 왼쪽부터 시계방향으로
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1, 0};
	static LinkedList<int[]> linkedList = new LinkedList<>();

	public static void main(String[] args) throws IOException {



		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			for (int i = 0; i < 8; i++) {
				String line = br.readLine();
				for (int j = 0; j < 8; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			linkedList.add(new int[]{7, 0}); // 시작점

			if (bfs()) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	static boolean bfs() {

		while (!linkedList.isEmpty()) {
			boolean[][] vis = new boolean[8][8];
			int size = linkedList.size();
			for (int s =0; s <size; s++) {
				int[] current = linkedList.poll();
				int x = current[0];
				int y = current[1];

				// 돌있으면
				if (map[x][y] == '#') {
					continue;
				}

				// 도달했다면
				if (x == 0 && y == 7) {
					return true;
				}

				for (int i = 0; i < 9; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && map[nx][ny] != '#' && !vis[nx][ny]) {
						vis[nx][ny] = true;
						linkedList.add(new int[]{nx, ny});
					}
				}
			}

			// 돌 내려간다
			for (int i = 7; i > 0; i--) {
				for (int j = 0; j < 8; j++) {
					map[i][j] = map[i-1][j];
				}
			}
			for (int j = 0; j < 8; j++) {
				map[0][j] = '.';
			}
		}
		return false;
	}
}