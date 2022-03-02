package karat;

import java.util.*;

/**
 * 三道题用的都是同一个input。就是一个list，每个item有访问时间，访问用户，访问资源。大概像下面这样。时间是当天的秒数。
 * { "583", "user_1", "resource_1" },
 * { "2501", "user_1", "resource_2" },
 * { "53760", "user_3", "resource_3" },
 * { "53600", "user_3", "resource_3" }
 * 第一题
 * 返回每个用户的最初访问时间和最后访问时间。
 * 第二题
 * 返回在5分钟内被访问最多的资源，并给出次数。如上面例子resource_1和resource_2都是1次，resource_3是2次，所以返回（resource_3，2）
 */
public class ARecentVisit {
    public static String[] findMostVisits(String[][] input) {
        Map<String, Queue<Integer>> countmap = new HashMap<String, Queue<Integer>>();
        int maxCount = 0;
        String maxResName = null;
        //countmap: resource + time
        for (String[] entry : input) {
            Integer time = Integer.parseInt(entry[0]);
            String user = entry[1];
            String resource = entry[2];
            if (!countmap.containsKey(resource)) {
                countmap.put(resource, new LinkedList<Integer>());
            }
            Queue<Integer> timelist = countmap.get(resource);
            while (!timelist.isEmpty() && (time - timelist.peek() > 5 * 60)) {
                timelist.poll();
            }
            timelist.add(time);
            if (timelist.size() > maxCount) {
                maxCount = timelist.size();
                maxResName = resource;
            }
        }
        return new String[]{maxResName, ""+ maxCount};
    }

    public static void main(String[] args){
        String[][] input =  { { "583", "user_1", "resource_1" }, { "2501", "user_1", "resource_2" }, { "53760", "user_3"
                , "resource_3" },  { "53600", "user_3", "resource_3" }};

        String[] res =  findMostVisits(input);
        System.out.println("resource name:" + res[0] + ", count:" + res[1]);
    }
}
