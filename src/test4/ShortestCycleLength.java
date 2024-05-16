package test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShortestCycleLength {
    // 定义一个表示坐标点的内部静态类
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 计算两点之间的距离
    public static double distance(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // 计算环的边长之和
    public static double calculatePerimeter(Point[] points) {
        double perimeter = 0.0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            perimeter += distance(points[i], points[(i + 1) % n]);
        }
        return perimeter;
    }

    public static void main(String[] args) {
        // 给定一组点的坐标
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1),
        };

        int n = points.length;
        double minPerimeter = Double.MAX_VALUE;

        List<Point> permutedPoints = new ArrayList<>(Arrays.asList(points));

        // 生成所有可能的排列组合
        for (List<Point> perm : permute(permutedPoints)) {
            double perimeter = calculatePerimeter(perm.toArray(new Point[0]));
            minPerimeter = Math.min(minPerimeter, perimeter);
        }

        System.out.println("最短的环的边长之和为：" + minPerimeter);
    }

    // 生成所有排列组合方法实现，循环首次调用初始index为0
    public static List<List<Point>> permute(List<Point> points) {
        List<List<Point>> result = new ArrayList<>();
        permuteHelper(result, points, 0);
        return result;
    }

    // 递归生成排列组合的辅助函数
    private static void permuteHelper(List<List<Point>> result, List<Point> points, int index) {
        if (index >= points.size()) {
            result.add(new ArrayList<>(points));
        } else {
            for (int i = index; i < points.size(); i++) {
                Collections.swap(points, index, i);
                permuteHelper(result, points, index + 1);
                Collections.swap(points, index, i);
            }
        }
    }
}
