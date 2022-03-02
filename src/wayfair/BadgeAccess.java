package wayfair;

import java.util.*;

/**
 We are working on a security system for a badged-access room in our company"s building.

 We want to find employees who badged into our secured room unusually often. We have an unordered list of names and entry times over a single day.
 Access times are given as numbers up to four digits in length using 24-hour time, such as "800" or "2250".

 Write a function that finds anyone who badged into the room three or more times in a one-hour period.
 Your function should return each of the employees who fit that criteria, plus the times that they badged in during the one-hour period.
 If there are multiple one-hour periods where this was true for an employee, just return the first one.

 badge_times = [
 ["Paul", "1355"],
 ["Jennifer", "1910"],
 ["John", "835"],
 ["John", "830"],
 ["Paul", "1315"],
 ["John", "1615"],
 ["John", "1640"],
 ["Paul", "1405"],
 ["John", "855"],
 ["John", "930"],
 ["John", "915"],
 ["John", "730"],
 ["John", "940"],
 ["Jennifer", "1335"],
 ["Jennifer", "730"],
 ["John", "1630"],
 ["Jennifer", "5"]
 ]

 Expected output (in any order)
 John: 830 835 855 915 930
 Paul: 1315 1355 1405

 ====================================================================
 problem#2:
  We want to find employees who badged into our secured room together often.
 Given an unordered list of names and access times over a single day,
 find the largest group of people that were in the room together during two or more separate time periods,
 and the times when they were all present.
  badge_records = [
  ["Paul", "1214", "enter"],
  ["Paul", "830", "enter"],
  ["Curtis", "1100", "enter"],
  ["Paul", "903", "exit"],
  ["John", "908", "exit"],
  ["Paul", "1235", "exit"],
  ["Jennifer", "900", "exit"],
  ["Curtis", "1330", "exit"],
  ["John", "815", "enter"],
  ["Jennifer", "1217", "enter"],
  ["Curtis", "745", "enter"],
  ["John", "1230", "enter"],
  ["Jennifer", "800", "enter"],
  ["John", "1235", "exit"],
  ["Curtis", "810", "exit"],
  ["Jennifer", "1240", "exit"],
  ]

  Paul :    830-903  1214 - 1235
  Curtis:   745-810  1100--1330
  John:     815-908  1230 - 1235
  Jennifer: 800-900 1217-1240

  Expected output:
  John, Paul, Jennifer: 830 to 900, 1230 to 1235

  For this input data:
  From 830 til 900, the room contains Jennifer, John, and Paul.
  From 1230 til 1235, the room contains Curtis, Paul, Jennifer, and John.

  The group "Jennifer, John, Paul" exists at both of these times, and is the largest group that exists multiple times.

  You should note that the group is a subset of the people in the room from 1230 to 1235
*/

public class BadgeAccess {
    public Map<String, List<Integer>> findAccess(String[][] badgeTimes) {
//        Arrays.sort(badgeTimes, (a, b) -> (a[0].equals(b[0]) ? Integer.parseInt(a[1]) - Integer.parseInt(b[1]) : a[0].compareTo(b[0])));
          Map<String, List<Integer>> res = new HashMap<>();
          Map<String, List<Integer>> map = new HashMap<>();
          for (String[] item : badgeTimes) {
              String user = item[0];
              int time = Integer.parseInt(item[1]);
              if (!map.containsKey(user)) {
                  map.put(user, new ArrayList<>());
              }
              map.get(user).add(time);
          }

          for (String key : map.keySet()) {
              List<Integer> timeList = map.get(key);
              Collections.sort(timeList);
              for (int i = 0; i < timeList.size(); i++) {
                  boolean found = false;
                  for (int j = i + 2; j < timeList.size(); j++) {
                      if (isInHour(timeList.get(i), timeList.get(j))) {
                          if (!res.containsKey(key)) {
                              res.put(key, new ArrayList<>());
                              res.get(key).add(timeList.get(i));
                              res.get(key).add(timeList.get(i + 1));
                              res.get(key).add(timeList.get(i + 2));
                          } else {
                              res.get(key).add(timeList.get(j));
                          }
                          found = true;
                      }
                  }
                  if (found) break;
              }
          }

          return res;
    }

    private boolean isInHour(int t1, int t2) {
//        830 835 855 915 930
//        1315 1355 1405

        int h1 = t1 / 100;
        int m1 = t1 % 100;
        int h2 = t2 / 100;
        int m2 = t2 % 100;
        int diff = (h2 - h1) * 60 + (m2 - m1);
        if (diff <= 60) {
            return true;
        }
        return false;
    }

    public List<String> findGroup(String[][] records) {

        //Arrays.sort(records, (a,b) -> (a[0].equals(b[0]) ? (a[2].equals(b[2]) ? (Integer.parseInt(a[1]) - Integer.parseInt(b[1])) : a[2].compareTo(b[2])) : a[0].compareTo(b[0]) ));
        Arrays.sort(records, (a, b) -> (a[0].equals(b[0]) ? Integer.parseInt(a[1]) - Integer.parseInt(b[1]) : a[0].compareTo(b[0])));
        Map<String, int[]> map = new HashMap<>();
        for (int i = 0; i < records.length; i++) {
            String user = records[i][0];
            int time = Integer.parseInt(records[i][1]);
            map.putIfAbsent(user, new int[2]);
            if (i % 2 == 0) {
                map.get(user)[0] = time;
            } else {
                map.get(user)[1] = time;
            }
        }

//        Paul :    830-903  1214 - 1235
//        Curtis:   745-810  1100--1330
//        John:     815-908  1230 - 1235
//        Jennifer: 800-900  1217-1240
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (String key : map.keySet()) {
            if (map.get(key)[0] > max) {
                max = map.get(key)[0];
            }
            if (map.get(key)[1] < min) {
                min = map.get(key)[1];
            }

        }

        return null;


    }
    public static void main(String[] args) {
        BadgeAccess badgeAccess = new BadgeAccess();
//        String[][] badgeTimes = {
//                {"Paul", "1355"},
//                {"Jennifer", "1910"},
//                {"John", "835"},
//                {"John", "830"},
//                {"Paul", "1315"},
//                {"John", "1615"},
//                {"John", "1640"},
//                {"Paul", "1405"},
//                {"John", "855"},
//                {"John", "930"},
//                {"John", "915"},
//                {"John", "730"},
//                {"John", "940"},
//                {"Jennifer", "1335"},
//                {"Jennifer", "730"},
//                {"John", "1630"},
//                {"Jennifer", "5"}
//        };
//        Map<String, List<Integer>> res = badgeAccess.findAccess(badgeTimes);
//        for (String key : res.keySet()) {
//            System.out.println(key);
//            System.out.println(res.get(key));
//        }

         String[][] records = {
                 {"Paul", "1214", "enter"},
                 {"Paul", "830", "enter"},
                 {"Curtis", "1100", "enter"},
                 {"Paul", "903", "exit"},
                 {"John", "908", "exit"},
                 {"Paul", "1235", "exit"},
                 {"Jennifer", "900", "exit"},
                 {"Curtis", "1330", "exit"},
                 {"John", "815", "enter"},
                 {"Jennifer", "1217", "enter"},
                 {"Curtis", "745", "enter"},
                 {"John", "1230", "enter"},
                 {"Jennifer", "800", "enter"},
                 {"John", "1235", "exit"},
                 {"Curtis", "810", "exit"},
                 {"Jennifer", "1240", "exit"},
         };

         badgeAccess.findGroup(records);



    }
}
