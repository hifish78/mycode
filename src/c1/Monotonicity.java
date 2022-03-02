package c1;

import java.util.*;

public class Monotonicity {

    public boolean[] checkMonotonicity(int[] arr) {
        if (arr == null || arr.length < 3) {
            return new boolean[]{false};
        }

        boolean[] res = new boolean[5];
        int cnt = 0;
        //{1, 2, 3, 4, 3, 4, 5};

        for (int i = 0; i < arr.length - 2; i++) {
            if (increasing(arr, i)) {
                res[i] = true;
            } else {
                res[i] = false;
            }
        }

        return res;
    }

    private boolean increasing(int[] arr, int i) {
        int increase = 0;
        for (int k = i + 1; k < i + 3; k++){
            if (arr[k] > arr[k - 1]) {
                increase++;
            }
        }
        if (increase == 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Monotonicity monotonicity = new Monotonicity();
        int[] arr = {1, 2, 3, 4, 3, 4, 5};
        boolean res[] = monotonicity.checkMonotonicity(arr);
        System.out.println(Arrays.toString(res));
    }
}
