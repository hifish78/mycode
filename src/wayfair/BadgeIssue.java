package wayfair;

import java.util.*;

/**
 * Given a list of people who enter and exit, find the people who entered without
 * their badge and who exited without their badge.
 *
 *
 */
public class BadgeIssue {
    public void mismatch(String[][] records) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> invalidEnter = new HashSet<>();
        Set<String> invalidExit = new HashSet<>();
        //Martha: exit, enter, exit ,enter, exit
        // Paul enter, enter, exit
        // Jennifer: enter, exit
        // Curtis: enter

        // 0 for exit, 1 for enter
        for (String[] record : records) {
            Integer pre = map.get(record[0]); //null
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

    public static void main(String[] args) {
        BadgeIssue badgeIssue = new BadgeIssue();
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


    }
}
