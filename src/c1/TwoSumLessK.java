package c1;

import java.util.Arrays;

public class TwoSumLessK {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int res = -1;
        // 1 8 23 24 33 34 54 75     60
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum < k) {
                if (sum > res) {
                    res = sum;
                }
                start++;
            } else {
                end--;

            }
        }

        return res;

    }

    public static void main(String[] args) {
        TwoSumLessK twoSumLessK = new TwoSumLessK();
        int[] nums = {34,23,1,24,75,33,54,8};
        int k = 60;
        int res = twoSumLessK.twoSumLessThanK(nums, k);
        System.out.println(res);
    }

}
