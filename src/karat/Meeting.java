package karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Meeting {
    /**
     * 第一题：类似meeting rooms，输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 =》 1300， 9：30 =》 18930， 看新的meeting
     * 能不能安排到meetings
     * ex: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;
     *
     * @return
     */

    public boolean canSchedule(int[][] meetings, int[] meeting) {
        for (int[] m : meetings) {
            //           meeting[0]-----------meeting[1]
            // m0 -- m1                                   m0 -- m1
            if (m[1] <= meeting[0] || m[0] >= meeting[1] ) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 类似merge interval，唯一的区别是输出，输出空闲的时间段，merge完后，再把两两个之间的空的输出就好，注意要加上0 - 第一个的start time
     * @param
     */
    public List<int[]> findFree(int[][] intervals) {
        //{[1300, 1500], [930, 1200],[830, 845]}
        List<int[]> merge = new ArrayList<>();
        Arrays.sort(intervals, (i1, i2) -> i1[0] == i2[0] ? i1[1] - i2[1] : i1[0] - i2[0]);
        //830 845 ; 930 1200; 1300 1500;

        // merge
        merge.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > merge.get(merge.size() - 1)[1]) {
                merge.add(intervals[i]);
            } else {
                merge.get(merge.size() -1)[1]= Math.max(merge.get(merge.size() -1)[1], intervals[i][1]);
            }
        }

        List<int[]> res = new ArrayList<>();
        if (merge.size() > 0 ) {
            res.add(new int[] {0, merge.get(0)[0]});
        }

        for (int i = 1; i < merge.size(); i++) {
            res.add(new int[] {merge.get(i-1)[1], merge.get(i)[0]});
        }
        return res;


    }

    public static void main(String[] args) {
        Meeting m = new Meeting();
        int[][] meetings = {{1300, 1500}, {930, 1200},{830, 845}};
        int[] meeting = {820, 830};
        boolean res =  m.canSchedule(meetings, meeting);
        System.out.println(res);
    }
}
