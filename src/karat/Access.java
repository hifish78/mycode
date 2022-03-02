package karat;

// badge_records = [
//   ["Martha",   "exit"],
//   ["Paul",     "enter"],
//   ["Martha",   "enter"],
//   ["Martha",   "exit"],
//   ["Jennifer", "enter"],
//   ["Paul",     "enter"],
//   ["Curtis",   "enter"],
//   ["Paul",     "exit"],
//   ["Martha",   "enter"],
//   ["Martha",   "exit"],
//   ["Jennifer", "exit"],
// ]

//Given a list of people who enter and exit, find the people who entered without
//        their badge and who exited without their badge.

//Martha: exit, enter, exit ,enter, exit
//Paul enter, enter, exit
//Jennifer: enter, exit
//Curtis: enter

//
// Expected output: ["Martha"], ["Paul", "Curtis"],

import java.util.*;

public class Access {
    public void mismatch(String[][] records) {
        Map<String, Integer> map = new HashMap<>();

        Set<String> invalidEnter = new HashSet<>();
        Set<String> invalidExit = new HashSet<>();

        //Martha: exit, enter, exit ,enter, exit
//Paul enter, enter, exit
//Jennifer: enter, exit
//Curtis: enter
        // 0 for exit, 1 for enter
        for (String[] record : records) {
            Integer pre = map.get(record[0]);
            if (record[1].equals("enter")) {
                if (pre != null && pre == 1) {
                    invalidExit.add(record[0]);
                }
                pre = 1;
            } else {
                if (pre == null || pre == 0) {
                    invalidEnter.add(record[0]);
                }
                pre = 0;
            }
            map.put(record[0], pre);
        }

        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                invalidExit.add(key);
            }
        }


        System.out.println("Invalid Enter:");
        for (String item : invalidEnter) {
            System.out.println(item);
        }

        System.out.println("Invalid Exit:");
        for (String item : invalidExit) {
            System.out.println(item);
        }
    }

/*    2. 一小时内access多次
给 list of [name, time], time is string format: '1300' //下午一点
return: list of names and the times where their swipe badges within one hour.
if there are multiple intervals that satisfy the condition, return any one of them.
    name1: time1, time2, time3...
    name2: time1, time2, time3, time4, time5...
    example:
    input: [['James', '1300'], ['Martha', '1600'], ['Martha', '1620'], ['Martha', '1530']]
    output: {
        'Martha': ['1600', '1620', '1530']
    }
//    {{"James", "1300"}, {"Martha", "1700"}, {"Martha", "1640"}, {"Martha", "1530"}};
// */
    public Map<String, List<Integer>> getGroup(String[][] records) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String[] record : records) {
            map.putIfAbsent(record[0], new ArrayList<>());
            map.get(record[0]).add(Integer.parseInt(record[1]));
        }
//        {"Martha", "1700"}, {"Martha", "1640"}, {"Martha", "1530"
        Map<String, List<Integer>> res = new HashMap<>();
        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            if (list.size() <= 1) {
                continue;
            }

            Collections.sort(list);
            int i = 0;
            //1530 1640 1700

            res.put(key, new ArrayList<>());
            for (int j = 1; j < list.size(); j++) {
                List<Integer> tmp = res.get(key);
                int cur = list.get(i);
                if (timeDifference(cur, list.get(j)) < 60) {
                    tmp.add(cur);
                    tmp.add(list.get(j));
                } else {
                    i = j;
                }
            }
        }

        return res;
    }
    //['1600', '1620', '1530']
    public int timeDifference(int a, int b) {
        int aHr = a / 100;
        int bHr = b / 100;
        int aMin = a % 100;
        int bMin = b % 100;
        int diff = (bHr * 60 + bMin) - (aHr * 60 + aMin);
        return diff;

    }

    public static void main(String[] args) {
        Access access = new Access();
        String[][] records = {
                {"Martha", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Paul", "enter"},
                {"Curtis", "enter"},
                {"Paul", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
        };

        //[['James', '1300'], ['Martha', '1600'], ['Martha', '1620'], ['Martha', '1530']]
        String[][] records2 = {{"James", "1300"}, {"Martha", "1600"}, {"Martha", "1620"}, {"Martha", "1530"}};

//        String[][] records2 = {{"James", "1300"}, {"Martha", "1700"}, {"Martha", "1640"}, {"Martha", "1530"}};


        access.mismatch(records);
        Map<String, List<Integer>> res = access.getGroup(records2);
        for (String key : res.keySet()) {
            System.out.println(key + res.get(key).toString());
        }


    }

}
