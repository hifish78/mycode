package karat;

import java.util.*;

public class FindOverlap {

    public static Map<String[] , Set<String>> findOverlap(String[][] studentCourses) {
        Map<String, Set<String>>  map = new HashMap<>();
        for (String[] studentCourse : studentCourses) {
            String id = studentCourse[0];
            String course = studentCourse[1];
            if (!map.containsKey(id)) {
                map.put(id, new HashSet<>());
            }
            map.get(id).add(course);
        }

        String[] students = map.keySet().toArray(new String[0]);
        Arrays.sort(students);
//        TreeMap<String[], Set<String>> res = new TreeMap<String[], Set<String>>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b [0]);

        TreeMap<String[], Set<String>> res = new TreeMap<String[], Set<String>>((a,b) -> (a[0].compareTo(b[0]) == 0? a[1].compareTo(b[1]) : a[0].compareTo(b[0])));
        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                Set<String> set = new HashSet<>();
                for (String course : map.get(students[i])) {
                    if (map.get(students[j]).contains(course)) {
                        set.add(course);
                    }
                }
                res.put(new String[]{students[i], students[j]},set);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String[][] studentCourses = {
                {"58", "Software Design"},
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
        };
        Map<String[] , Set<String>> res = FindOverlap.findOverlap(studentCourses);

        for (String[] key : res.keySet()){
            System.out.println("[" + key[0] + "," + key[1] +"]: " + res.get(key).toString());
//            res.get(key).forEach(c ->System.out.print(c + ','));
//            System.out.println("]");
        }

    }


}
