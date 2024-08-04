import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * start: 2024-08-01 16:00
 * end: 2024-08-01
 * 시간복잡도:
 * 공간복잡도:
 * 풀이:
 * 1. 루트 노드는 0번째가 고정인거아님 문제 잘 읽자
 * 2. tree(자식 정보를가진)에서 제거할 노드찾아서 지워야하니 clear하면 안된다
 */
public class P_1068 {

	static List<Integer>[] tree; // 각 노드의 부모노드 정보를 저장할 배열
	static boolean[] delNodeArr;
	static int root = -1;
	static int delNode;

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.parseInt(br.readLine());

			tree = new ArrayList[n];
			delNodeArr = new boolean[n];

			for (int i = 0; i < n; i++) {
				tree[i] = new ArrayList<>();
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int parent = Integer.parseInt(st.nextToken());
				if (parent == -1) {
					root = i;
				}else {
					tree[parent].add(i);
				}
			}

			delNode = Integer.parseInt(br.readLine());

			// node 제거
			removeRecursiveDfs(delNode);
			if (delNode == root) {
				System.out.println(0); // 루트 노드가 삭제되면 0
			} else {
				System.out.println(countLeafNodeDfs(root));
			}

		}
	}

	static void removeRecursiveDfs(int node) {
		int size = tree.length;
		for (int i = 0; i < size; i++) {
			tree[i].remove(Integer.valueOf(node));
		}
		delNodeArr[node] = true;

		if (tree[node].isEmpty()) {
			return;
		}

		List<Integer> children = new ArrayList<>(tree[node]);
		for (int child : children) {
			removeRecursiveDfs(child);
		}
	}

	static int countLeafNodeDfs(int node) {
		// 삭제됐으면 리프 x
		if (delNodeArr[node]) {
			return 0;
		}
		// 자식 없으면 리프
		if (tree[node].isEmpty()) {
			return 1;
		}

		// 자식 있으면 자식들 타고 들어가서 count 더하기
		int count = 0;
		for (int childNode : tree[node]) {
			if (!delNodeArr[childNode]) {
				count += countLeafNodeDfs(childNode);
			}
		}
		return count;
	}
}
