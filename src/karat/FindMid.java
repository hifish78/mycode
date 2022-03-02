package karat;
/*
Students may decide to take different "tracks" or sequences of courses in the Computer Science curriculum.
There may be more than one track that includes the same course,
but each student follows a single linear track from a "root" node to a "leaf" node. In the graph below, their path always moves left to right.

Write a function that takes a list of (source, destination) pairs, and
returns the name of all of the courses that the students could be taking when they are halfway through their track of courses.

Sample input:
all_courses = [
    ["Logic", "COBOL"],
    ["Data Structures", "Algorithms"],
    ["Creative Writing", "Data Structures"],
    ["Algorithms", "COBOL"],
    ["Intro to Computer Science", "Data Structures"],
    ["Logic", "Compilers"],
    ["Data Structures", "Logic"],
    ["Creative Writing", "System Administration"],
    ["Databases", "System Administration"],
    ["Creative Writing", "Databases"],
    ["Intro to Computer Science", "Graphics"],
]

Sample output (in any order):
          ["Data Structures", "Creative Writing", "Databases", "Intro to Computer Science"]

All paths through the curriculum (midpoint *highlighted*):

*Intro to C.S.* -> Graphics
Intro to C.S. -> *Data Structures* -> Algorithms -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> Compiler
Creative Writing -> *Databases* -> System Administration
*Creative Writing* -> System Administration
Creative Writing -> *Data Structures* -> Algorithms -> COBOL
Creative Writing -> *Data Structures* -> Logic -> COBOL
Creative Writing -> *Data Structures* -> Logic -> Compilers

Visual representation:

                    ____________
                    |          |
                    | Graphics |
               ---->|__________|
               |                          ______________
____________   |                          |            |
|          |   |    ______________     -->| Algorithms |--\     _____________
| Intro to |   |    |            |    /   |____________|   \    |           |
| C.S.     |---+    | Data       |   /                      >-->| COBOL     |
|__________|    \   | Structures |--+     ______________   /    |___________|
                 >->|____________|   \    |            |  /
____________    /                     \-->| Logic      |-+      _____________
|          |   /    ______________        |____________|  \     |           |
| Creative |  /     |            |                         \--->| Compilers |
| Writing  |-+----->| Databases  |                              |___________|
|__________|  \     |____________|-\     _________________________
               \                    \    |                       |
                \--------------------+-->| System Administration |
                                         |_______________________|

Complexity analysis variables:

n: number of pairs in the input

*/

import java.util.*;

public class FindMid {
    public List<String> findMid(String[][] coursePairs) {
        // build graph & calculate indegree
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        for (String[] pair : coursePairs) {
            if (!graph.containsKey(pair[0])) {
                graph.put(pair[0], new HashSet<>());
            }
            graph.get(pair[0]).add(pair[1]);
            indegree.put(pair[0], indegree.getOrDefault(pair[0], 0));
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
        }

        //start with the node that indegree == 0
        List<List<String>> paths = new ArrayList<>();
        for (String key: indegree.keySet()) {
            if (indegree.get(key) == 0) {
                List<String> path = new ArrayList<>();
                path.add(key);
                dfs(graph, key, path, paths);
            }
        }

        //find the middle
        Set<String> res = new HashSet<>();
        for (List<String> path : paths) {
            res.add(path.get((path.size() - 1) / 2));
        }

        return new ArrayList<String>(res);
    }

    private void dfs(Map<String, Set<String>> graph, String start, List<String> path, List<List<String>> res) {

        if (!graph.containsKey(start)) {
            res.add(new ArrayList<String>(path));
            return;
        }

        for (String next : graph.get(start)) {
            path.add(next);
            dfs(graph, next, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        String[][] all_courses = {{"Logic", "COBOL"},
                {"Data Structures", "Algorithms"},
                {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"},
                {"Intro to Computer Science", "Data Structures"},
                {"Logic", "Compilers"},
                {"Data Structures", "Logic"},
                {"Creative Writing", "System Administration"},
                {"Databases", "System Administration"},
                {"Creative Writing", "Databases"},
                {"Intro to Computer Science", "Graphics"}};
        FindMid s5 = new FindMid();

        List<String> res = s5.findMid(all_courses);
        System.out.println(res.toString());
    }

}
