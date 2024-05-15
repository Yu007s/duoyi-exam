package test4;

import java.util.ArrayList;

public class ShortestCycleLength {
    static int n; // 点的数量
    static int[][] points; // 存储点的坐标
    static boolean[] visited; // 记录节点是否已经访问过
    static int shortestLength = Integer.MAX_VALUE; // 最短环路的边长之和

    public static void main(String[] args) {
        n = 4;  // 点的数量，可以根据需要修改
        points = new int[n][2]; // 初始化点的坐标数组
        visited = new boolean[n]; // 初始化节点访问标记数组

        // 示例输入点坐标
        points[0] = new int[]{0, 1};
        points[1] = new int[]{1, 0};
        points[2] = new int[]{1, 1};
        points[3] = new int[]{0, 0};

        dfs(0, 0, new ArrayList<>());  // 从第一个点开始进行深度优先搜索
        System.out.println("最短环路的边长之和为：" + shortestLength);
    }

    /**
     * 深度优先搜索找出最短环路的边长之和
     * @param current 当前节点
     * @param length 当前路径长度
     * @param path 当前路径
     */
    public static void dfs(int current, int length, ArrayList<Integer> path) {
        visited[current] = true; // 标记当前节点已访问
        path.add(current); // 将当前节点加入路径

        if (path.size() == n) { // 如果路径长度等于点的数量，说明形成了一个环路
            int cycleLength = length + distance(points[current], points[path.get(0)]); // 计算环路长度
            shortestLength = Math.min(shortestLength, cycleLength); // 更新最短环路长度
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) { // 如果节点未访问过
                    int newLength = length + distance(points[current], points[i]); // 计算新路径长度
                    dfs(i, newLength, new ArrayList<>(path)); // 递归深度优先搜索
                }
            }
        }

        visited[current] = false; // 恢复当前节点未访问状态，回溯
    }

    /**
     * 计算两点之间的曼哈顿距离
     * @param p1 点1坐标
     * @param p2 点2坐标
     * @return 曼哈顿距离
     */
    public static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
