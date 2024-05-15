package test2;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * 在给定整数数组中寻找两个数，使它们的和等于目标值
     * @param nums 给定的整数数组
     * @param target 目标值
     * @return 包含两个数索引的数组，如果不存在符合条件的配对则返回空数组
     */
    public int[] findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            numToIndex.put(nums[i], i);
        }

        // 如果不存在符合条件的配对，则返回空数组
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum.findTwoSum(nums, target);

        if (result.length == 2) {
            System.out.println("找到两个数的索引为：" + result[0] + " 和 " + result[1]);
            System.out.println("它们的和为 " + target);
        } else {
            System.out.println("未找到符合条件的两个数");
        }
    }
}
