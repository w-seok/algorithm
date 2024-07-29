import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class P_24479 {

	static int N, M, R, cnt = 1;
	static int[] vis;
	static ArrayList<Integer>[] arr;
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		answer = new int[N + 1];

		arr = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		vis = new int[N + 1];

		// stack.add(R);
		for (int i = 0; i < M; i++) {
			tk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tk.nextToken());
			int y = Integer.parseInt(tk.nextToken());
			arr[x].add(y);
			arr[y].add(x);
		}
		Arrays.stream(arr).forEach(Collections::sort);

		recursive_dfs(R);
		// recursive_dfs(R);
		IntStream.range(1, N + 1).forEach(x -> System.out.println(answer[x]));
	}

	static void iterative_dfs() {
	}

	static void recursive_dfs(int cur) {
		if (vis[cur] == 1) {
			return;
		}
		vis[cur] = 1;

		answer[cur] = cnt++;
		arr[cur].forEach(P_24479::recursive_dfs);
	}

}
