package test4;

import java.util.ArrayList;

public class ShortestCycleLength {
    static int n;
    static int[][] points;
    static boolean[] visited;
    static int shortestLength = Integer.MAX_VALUE;

    public static void main(String[] args) {
        n = 4;  // 更改n的值来适应不同的点数量
        points = new int[n][2];
        visited = new boolean[n];

        // 示例输入点坐标
        points[0] = new int[]{0, 1};
        points[1] = new int[]{1, 0};
        points[2] = new int[]{1, 1};
        points[3] = new int[]{0, 0};

        dfs(0, 0, new ArrayList<>());  // 从第一个点开始进行深度优先搜索
        System.out.println("最短环路的边长之和为：" + shortestLength);
    }

    public static void dfs(int current, int length, ArrayList<Integer> path) {
        visited[current] = true;
        path.add(current);

        if (path.size() == n) {
            int cycleLength = length + distance(points[current], points[path.get(0)]);
            shortestLength = Math.min(shortestLength, cycleLength);
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int newLength = length + distance(points[current], points[i]);
                    dfs(i, newLength, new ArrayList<>(path));
                }
            }
        }

        visited[current] = false;
    }

    public static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
